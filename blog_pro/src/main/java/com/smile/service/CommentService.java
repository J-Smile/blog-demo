package com.smile.service;

import com.smile.dao.CommentMapper;
import com.smile.domain.Blog;
import com.smile.domain.Comment;
import com.smile.domain.User;
import com.smile.utils.RedisUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author: Smile
 * @description:
 * @create: 2020-04-07 20:13
 */
@Service
public class CommentService {

    @Resource
    private CommentMapper commentMapper;
    private final RedisUtils redisUtils;
    private final ReplyService replyService;
    private final UserService userService;
    private final BlogService blogService;

    public CommentService(ReplyService replyService, RedisUtils redisUtils, UserService userService, BlogService blogService) {
        this.replyService = replyService;
        this.redisUtils = redisUtils;
        this.userService = userService;
        this.blogService = blogService;
    }


    /**
     * 添加评论
     *
     * @return
     */
    public void saveContent(Comment comment, String token) {
        save(1, comment, token);
        String bid = comment.getBid();
        blogService.addDiscussCount(bid);
    }

    @Transactional
    public void save(int ismag, @NotNull Comment comment, String token) {
        comment.setIsmsg(ismag);
        User user = redisUtils.getUser(token);
        comment.setUid(user.getUid());
        comment.setTime(LocalDateTime.now());
        commentMapper.insert(comment);
    }

    public void saveMessage(Comment comment, String token) {
        save(0, comment, token);
    }

    public Integer getCommentNum() {
        return commentMapper.getCommentNum();
    }

    public ArrayList<Comment> commentById(String bid) {
        ArrayList<Comment> comments = commentMapper.commentById(bid);
        comments.forEach(e -> {
            e.setReplies(replyService.getReplyByCid(e.getId()));
            e.setUser(userService.findUserById(e.getUid()));
        });

        return comments;
    }

    public List<Comment> all() {
        List<Comment> messageAll = commentMapper.getMessageAll();
        return messageAll;
    }


    public ArrayList<Blog> newComment() {
        ArrayList<String> strings = commentMapper.newComment();
        ArrayList<Blog> blogs = new ArrayList<>();
        strings.stream().
                distinct().
                limit(2).
                forEach(e -> blogs.add(blogService.getById(e)));
        return blogs;
    }
}
