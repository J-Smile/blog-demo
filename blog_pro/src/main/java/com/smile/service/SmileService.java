package com.smile.service;

import com.smile.utils.RedisUtils;
import org.springframework.stereotype.Service;

/**
 * @author: Smile
 * @description:
 * @create: 2020-04-11 15:13
 */
@Service
public class SmileService {
    private final RedisUtils redisUtils;
    private final UserService userService;
    private final CommentService commentService;
    private final BlogService blogService;
    public SmileService(RedisUtils redisUtils, UserService userService, CommentService commentService, BlogService blogService) {
        this.redisUtils = redisUtils;
        this.userService = userService;
        this.commentService = commentService;
        this.blogService = blogService;
    }

    public String index() {
        int ipNum = (int) redisUtils.getRecord();
        Integer userNum = userService.getUserNum();
        Integer commentNum = commentService.getCommentNum();
        Integer blogNum = blogService.getBlogNum();
        return ipNum+"-"+userNum+"-"+commentNum+"-"+blogNum;
    }
}
