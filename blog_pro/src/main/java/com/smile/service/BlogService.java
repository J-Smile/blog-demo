package com.smile.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.smile.dao.BlogMapper;
import com.smile.dao.BlogTagMapper;
import com.smile.domain.Blog;
import com.smile.domain.BlogTag;
import com.smile.domain.Tag;
import com.smile.domain.User;
import com.smile.utils.RedisUtils;
import com.smile.utils.UUid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: 淳淳
 * @create: 2020-03-25 18:19
 * @description:
 **/
@Service
public class BlogService {

    @Resource
    private BlogMapper blogMapper;
    @Resource
    private BlogTagMapper BTMapper;
    private final RedisUtils redisUtils;
    private final SortService sortService;
    private final TagService tagService;

    public BlogService(RedisUtils redisUtils, SortService sortService, TagService tagService) {
        this.redisUtils = redisUtils;
        this.sortService = sortService;
        this.tagService = tagService;
    }

    public Blog getById(String bid) {
        return blogMapper.selectByPrimaryKey(bid);
    }

    @Transactional
    public void addBlog(Blog blog, HttpServletRequest request) {
        blog.setBid(UUid.getId());
        blog.setClickCount(0);
        blog.setStatus(true);
        blog.setCreateTime(new Date());
        String authorization = request.getHeader("authorization");
        User user = redisUtils.getUser(request.getHeader("authorization"));
        blog.setDiscussCount(0);
        blog.setUid(user.getUid());
        blog.setAuthor(user.getUsername());
        blog.setIsPublish(true);
        blogMapper.insert(blog);
        for (int tid : blog.getTids()) {
            BlogTag blogTag = new BlogTag();
            blogTag.setBid(blog.getBid());
            blogTag.setTid(tid);
            BTMapper.insert(blogTag);
        }
    }

    public PageInfo<Blog> findBlog(int page, int size) {
        PageHelper.startPage(page, size);
        return new PageInfo<Blog>(blogMapper.selectAll());
    }

    public void del(String bid) {
        blogMapper.deleteByPrimaryKey(bid);
    }

    @Transactional
    public Blog blog(String id) {
        Blog blog = redisUtils.getBlog(id);
        if (blog == null) {
            blog = blogMapper.selectByPrimaryKey(id);
            List<BlogTag> byBid = BTMapper.findByBid(id);
            ArrayList<Tag> tags = new ArrayList<>();
            byBid.forEach(e -> tags.add(tagService.getTag(e.getTid())));
            blog.setTags(tags);
            int[] ints = new int[tags.size()];
            for (int i = 0; i < tags.size(); i++) {
                ints[i] = tags.get(i).getTid();
            }
            blog.setTids(ints);
            blog.setSortName(sortService.getSort(blog.getSortId()).getContent());
            redisUtils.saveBlog(id, blog);
        }

        blog.setClickCount(blog.getClickCount() + 1);
        blogMapper.updateByPrimaryKey(blog);
        return blog;
    }

    public void editBlog(Blog blog) {
        blog.setUpdateTime(new Date());
        blogMapper.updateByPrimaryKey(blog);

        List<BlogTag> byBid = BTMapper.findByBid(blog.getBid());
        byBid.forEach(e -> BTMapper.delete(e));
        BlogTag blogTag = new BlogTag();
        blogTag.setBid(blog.getBid());
        for (int tid : blog.getTids()) {
            blogTag.setTid(tid);
            BTMapper.insert(blogTag);
        }
        redisUtils.saveBlog(blog.getBid(), blog);
    }

    @Transactional
    public Boolean recommend(String bid) {
        Blog blog = blogMapper.selectByPrimaryKey(bid);
        blog.setLevel(!blog.getLevel());
        blogMapper.updateByPrimaryKey(blog);
        return blog.getLevel();
    }

    public List<Blog> recommend() {
        return blogMapper.recommend();
    }

    public List<Blog> index() {
        List<Blog> index = blogMapper.index();
        index.forEach(e -> e.setSortName(sortService.getSort(e.getSortId()).getContent()));
        return index;
    }

    /**
     * 根据bid 分装blog
     */
    public List<Blog> getBlog(List<String> bids) {
        ArrayList<Blog> blogs = new ArrayList<>();
        bids.forEach(e -> {
            Blog blog = blogMapper.selectByPrimaryKey(e);
            blogs.add(blog);
            blogs.forEach(e1 -> e1.setSortName(sortService.getSort(e1.getSortId()).getContent()));
        });
        return blogs;
    }

    public Integer getBlogNum() {
        return blogMapper.getBlogNum();
    }

    public void addDiscussCount(String bid) {
        Blog blog = getById(bid);
        blog.setDiscussCount(blog.getDiscussCount()+1);
    }
}
