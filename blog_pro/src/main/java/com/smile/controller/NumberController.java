package com.smile.controller;

import com.smile.domain.Number;
import com.smile.service.NumberService;
import com.smile.vo.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: Smile
 * @description:
 * @create: 2020-04-08 21:47
 */
@RestController
@RequestMapping("num")
public class NumberController {
    @Autowired
    private NumberService numberService;

    @GetMapping
    public BaseResult number() {
        return BaseResult.success(numberService.number());
    }

    @PutMapping
    public BaseResult editNumber(@RequestBody Number number) {
        numberService.editNumber(number);
        return BaseResult.success();
    }

}
