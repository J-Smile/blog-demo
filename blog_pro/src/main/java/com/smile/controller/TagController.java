package com.smile.controller;

import com.smile.domain.Tag;
import com.smile.service.BlogService;
import com.smile.service.BlogTagService;
import com.smile.service.TagService;
import com.smile.vo.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: 淳淳
 * @create: 2020-03-25 11:23
 * @description: 标签
 **/
@RestController
@RequestMapping("/tag")
public class TagController {
    @Autowired
    private TagService tagService;
    @Autowired
    private BlogTagService blogTagService;

    @GetMapping
    public BaseResult getAll() {
        return BaseResult.success(tagService.getAll());
    }
    @GetMapping("status")
    public BaseResult getTagStatus() {
        return BaseResult.success(tagService.getSortStatus());
    }
    @GetMapping("/{tid}")
    public BaseResult getTag(@PathVariable Integer tid) {
        return BaseResult.success(tagService.getTag(tid));
    }

    /**
     * 根据标签获取 博客
     * @return
     */
    @GetMapping("/tags/{tid}")
    public BaseResult getTagByTid(@PathVariable Integer tid) {
        return BaseResult.success(blogTagService.getBlogByTid(tid));
    }

    /**
     * 添加和修改
     * @param tag
     * @return
     */
    @PostMapping
    public BaseResult addTag(@RequestBody Tag tag) {
        tagService.saveTag(tag);
        return BaseResult.success();
    }


    @DeleteMapping("/{tid}")
    public BaseResult delTag(@PathVariable("tid") Integer tid) {
        tagService.delTag(tid);
        return BaseResult.success();
    }

}
