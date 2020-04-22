package com.smile.dao;


import com.smile.domain.Author;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;


/**
 * @author: Smile
 * @description:
 * @create: 2020-04-07 22:22
 */
public interface AuthorMapper extends Mapper<Author> {
    @Select("select * from blog.author a where a.id = 1 ;")
    Author author();
}
