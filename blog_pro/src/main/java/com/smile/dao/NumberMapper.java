package com.smile.dao;

import com.smile.domain.Number;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

public interface NumberMapper extends Mapper<Number> {

    @Select("select * from blog.number n where n.id =1")
    Number number();
}
