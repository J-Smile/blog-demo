package com.smile.controller;

import com.smile.domain.Comment;
import com.smile.exception.CommentException;
import com.smile.exception.TokenException;
import com.smile.service.CommentService;
import com.smile.service.ReplyService;
import com.smile.vo.BaseResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: Smile
 * @description:
 * @create: 2020-04-07 20:12
 */
@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("newComment")
    public BaseResult newComment (){
        return BaseResult.success(commentService.newComment());
    }
    /**
     * 评论
     * 获取所有评论
     */
    @GetMapping("/{bid}")
    public BaseResult commentById(@PathVariable String bid) {
        return BaseResult.success(commentService.commentById(bid));
    }

    /**
     * 获取所有留言
     */
    @GetMapping("message")
    public BaseResult all() {
        return BaseResult.success(commentService.all());
    }

    @PostMapping("message")
    public BaseResult addMessage(@RequestBody Comment comment, HttpServletRequest request) {
        String token = request.getHeader("authorization");
        try {
            commentService.saveMessage(comment,token);
        } catch (TokenException e) {
            throw new TokenException(e.getMessage());
        } catch (CommentException e) {
            throw new CommentException(e.getMessage());
        }
        return BaseResult.success();
    }



    /**
     * 添加评论
     *
     * @return
     */
    @PostMapping()
    public BaseResult saveMessage(@RequestBody Comment comment, HttpServletRequest request) {
        String token = request.getHeader("authorization");
        try {
            commentService.saveContent(comment,token);
        } catch (TokenException e) {
            throw new TokenException(e.getMessage());
        } catch (CommentException e) {
            throw new CommentException(e.getMessage());
        }
        return BaseResult.success();
    }
}
