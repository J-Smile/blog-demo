package com.smile.task;

import com.smile.domain.Record;
import com.smile.service.RecordService;
import com.smile.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author: Smile
 * @description: 定时任务
 * @create: 2020-04-11 14:40
 */
@Component
@EnableScheduling
public class TimingTask {

    private final RedisUtils redisUtils;
    private final RecordService recordService;

    public TimingTask(RedisUtils redisUtils, RecordService recordService) {
        this.redisUtils = redisUtils;
        this.recordService = recordService;
    }

    @Scheduled(cron = "0 0 23 * * ?")
    public void record() {
        int num = (int) redisUtils.getRecord();
        Record record = new Record();
        record.setNum(num);
        record.setTime(new Date());
        recordService.save(record);

    }
}
