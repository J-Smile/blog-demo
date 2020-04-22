package com.smile.service;

import com.smile.dao.AuthorMapper;
import com.smile.domain.Author;
import com.smile.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: Smile
 * @description:
 * @create: 2020-04-07 22:23
 */
@Service
public class AuthorService {

    @Resource
    private AuthorMapper authorMapper;
    @Autowired
    private RedisUtils redisUtils;

    public Author author() {
        Author author = redisUtils.getAuthor();
        if (author != null) {
            return author;
        }
        author = authorMapper.author();
        return author;
    }

    public void editAuthor(Author author) {
        authorMapper.updateByPrimaryKey(author);
        redisUtils.saveAuthor(author);
    }
}
