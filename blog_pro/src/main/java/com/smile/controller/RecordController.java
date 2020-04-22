package com.smile.controller;


import com.smile.service.RecordService;
import com.smile.vo.BaseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Smile
 * @description:
 * @create: 2020-04-11 13:44
 */
@RestController
@RequestMapping("record")
public class RecordController {
    private final RecordService recordService;

    public RecordController(RecordService recordService) {
        this.recordService = recordService;
    }

    @GetMapping
    public void add() {
        recordService.add();
    }

    @GetMapping("get")
    public BaseResult getRecord() {
        return BaseResult.success(recordService.getRecord());
    }
}
