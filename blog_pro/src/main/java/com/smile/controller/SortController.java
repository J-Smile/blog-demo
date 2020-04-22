package com.smile.controller;

import com.smile.domain.Sort;
import com.smile.service.SortService;
import com.smile.vo.BaseResult;
import org.springframework.web.bind.annotation.*;

/**
 * @author: Smile
 * @description: 分类
 * @create: 2020-03-25 09:56
 */
@RestController
@RequestMapping("/sort")
public class SortController {
    private final SortService sortService;

    public SortController(SortService sortService) {
        this.sortService = sortService;
    }

    @GetMapping
    public BaseResult getAll() {
        return BaseResult.success(sortService.getAll());
    }

    @GetMapping("status")
    public BaseResult getSortStatus() {
        return BaseResult.success(sortService.getSortStatus());
    }

    @GetMapping("/{sid}")
    public BaseResult getSort(@PathVariable Integer sid) {
        return BaseResult.success(sortService.getSort(sid));
    }

    /**
     * 添加和修改
     *
     * @param
     * @return
     */
    @PostMapping
    public BaseResult addSort(@RequestBody Sort sort) {
        sortService.saveSort(sort);
        return BaseResult.success();
    }

    @DeleteMapping("/{sid}")
    public BaseResult delSort(@PathVariable("sid") String sid) {
        sortService.delSort(sid);
        return BaseResult.success();
    }

}
