package com.smile.dao;

import com.smile.domain.BlogTag;
import jdk.nashorn.internal.runtime.linker.LinkerCallSite;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author: 淳淳
 * @create: 2020-03-25 17:50
 * @description:
 **/
public interface BlogTagMapper  extends Mapper<BlogTag> {
    @Select("select * from blog.blog_tag b where b.bid =#{bid} ")
    List<BlogTag> findByBid(String bid);
    @Select("select b.bid from blog.blog_tag b where b.tid =#{tid} ")
    List<String> findByBTid(Integer tid);
}
