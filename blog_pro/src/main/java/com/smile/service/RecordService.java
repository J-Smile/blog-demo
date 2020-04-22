package com.smile.service;

import com.smile.dao.RecordMapper;
import com.smile.domain.Record;
import com.smile.utils.RedisUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Smile
 * @description:
 * @create: 2020-04-11 13:44
 */
@Service
public class RecordService {
    private final RedisUtils redisUtils;
    private final RecordMapper recordMapper;

    public RecordService(RedisUtils redisUtils, RecordMapper recordMapper) {
        this.redisUtils = redisUtils;
        this.recordMapper = recordMapper;
    }

    public void add() {
        redisUtils.addRecord();
    }

    public void save(Record record) {
        recordMapper.insert(record);
    }

    public List<Record> getRecord() {
        return recordMapper.selectAll();
    }
}
