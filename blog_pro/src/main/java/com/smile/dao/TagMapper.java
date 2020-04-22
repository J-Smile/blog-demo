package com.smile.dao;

import com.smile.domain.Sort;
import com.smile.domain.Tag;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TagMapper extends Mapper<Tag> {
    @Select("select * from blog.tag t where t.status = 1")
    List<Tag> tagStatus();
}