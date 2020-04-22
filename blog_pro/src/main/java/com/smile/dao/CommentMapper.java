package com.smile.dao;


import com.smile.domain.Comment;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public interface CommentMapper extends Mapper<Comment> {

    @Select("select *  from blog.comment c where c.ismsg=0 order by c.time desc limit 0,10")
    List<Comment> getMessageAll();

    @Select("select count(*) from blog.comment c where c.ismsg=1")
    Integer getCommentNum();

    @Select("select *  from blog.comment c where c.bid = #{bid}  and c.ismsg=1 order by c.time asc")
    ArrayList<Comment> commentById(String bid);

    @Select("select c.bid from blog.comment c  WHERE c.ismsg = 1 ORDER  BY c.time DESC ")
    ArrayList<String> newComment();
}
