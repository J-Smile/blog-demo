package com.smile.dao;

import com.smile.domain.Blog;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface BlogMapper extends Mapper<Blog> {

    @Select("select * from blog.blog b where b.status=1;")
    List<Blog> findAllBlog();

    @Select("select b.bid, b.image,b.title,b.create_time,b.click_count from blog.blog b where b.level=1;")
    List<Blog> recommend();

    @Select("SELECT * FROM blog.blog b ORDER BY b.create_time desc LIMIT 6")
    List<Blog> index();

    @Select("SELECT count(*) FROM blog.blog")
    Integer getBlogNum();
}