package com.smile.service;

import com.smile.dao.BlogTagMapper;
import com.smile.domain.Blog;
import com.smile.domain.BlogTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: Smile
 * @description:
 * @create: 2020-04-06 16:35
 */
@Service
public class BlogTagService {

    @Resource
    private BlogTagMapper blogTagMapper;

    private final BlogService blogService;

    public BlogTagService(BlogService blogService) {
        this.blogService = blogService;
    }

    public List<Blog> getBlogByTid(Integer tid) {
        List<String> byBTid = blogTagMapper.findByBTid(tid);
        return blogService.getBlog(byBTid);
    }
}
