package com.smile.controller;

import com.smile.service.SmileService;
import com.smile.vo.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Smile
 * @description: 后台首页
 * @create: 2020-04-11 15:12
 */
@RestController
@RequestMapping("/smile")
public class SmileController {
    private final SmileService smileService;

    public SmileController(SmileService smileService) {
        this.smileService = smileService;
    }

    @GetMapping
    public BaseResult index() {
        return BaseResult.success(smileService.index());
    }
}
