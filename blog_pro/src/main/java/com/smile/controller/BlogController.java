package com.smile.controller;

import com.smile.domain.Blog;
import com.smile.exception.BlogException;
import com.smile.service.BlogService;
import com.smile.vo.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: Smile
 * @description:
 * @create: 2020-03-25 20:25
 */
@RestController
@RequestMapping("/blog")
public class BlogController {
    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping("/{page}/{size}")
    public BaseResult findBlog(@PathVariable int page, @PathVariable int size) {
        return BaseResult.success(blogService.findBlog(page, size));
    }

    @GetMapping("/index")
    public BaseResult index() {
        return BaseResult.success(blogService.index());
    }

    /**
     * 获取所有 推荐文章
     *
     * @return
     */
    @GetMapping("/recommend")
    public BaseResult recommendAll() {
        return BaseResult.success(blogService.recommend());
    }

    @GetMapping("/{id}")
    public BaseResult blog(@PathVariable String id) {
        Blog blog = null;
        try {
            blog = blogService.blog(id);
        } catch (Exception e) {
            throw new BlogException("查询参数异常"+id);
        }
        return BaseResult.success(blog);
    }


    @PostMapping
    public BaseResult addBlog(@RequestBody Blog blog, HttpServletRequest request) {
        blogService.addBlog(blog, request);
        return BaseResult.success();
    }

    @PostMapping("/{bid}")
    public BaseResult recommend(@PathVariable String bid) {
        Boolean recommend = blogService.recommend(bid);
        return BaseResult.success(recommend);
    }

    @PutMapping
    public BaseResult editBlog(@RequestBody Blog blog) {
        blogService.editBlog(blog);
        return BaseResult.success();
    }

    @DeleteMapping("/{bid}")
    public BaseResult del(@PathVariable String bid) {
        blogService.del(bid);
        return BaseResult.success();
    }
}
