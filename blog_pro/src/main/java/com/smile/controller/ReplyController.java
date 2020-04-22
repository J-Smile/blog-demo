package com.smile.controller;

import com.smile.domain.Reply;
import com.smile.service.ReplyService;
import com.smile.vo.BaseResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: Smile
 * @description: 回复
 * @create: 2020-04-21 11:59
 */
@RestController
@RequestMapping("reply")
public class ReplyController {
    private final ReplyService replyService;

    public ReplyController(ReplyService replyService) {
        this.replyService = replyService;
    }

    @PostMapping
    public BaseResult replyAdd(@RequestBody Reply reply, HttpServletRequest request) {
        replyService.replyAdd(reply,request);
        return BaseResult.success();
    }

}
