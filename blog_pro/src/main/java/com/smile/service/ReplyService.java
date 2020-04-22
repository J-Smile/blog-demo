package com.smile.service;

import com.smile.dao.ReplyMapper;
import com.smile.domain.Reply;
import com.smile.domain.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * @author: Smile
 * @description:
 * @create: 2020-04-13 09:10
 */
@Service
public class ReplyService {

    @Resource
    private ReplyMapper replyMapper;
    private final UserService userService;

    public ReplyService(UserService userService) {
        this.userService = userService;
    }

    /**
     * 评论
     *
     * @param cid 评论id
     * @return
     */
    public ArrayList<Reply> getReplyByCid(int cid) {
        int num = replyMapper.num(cid);
        ArrayList<Reply> replies = replyMapper.getReplyByLevel(1);
        replies.forEach(e -> {
            for (int i = 2; i <= num; i++) {
                e.setReplies(replyMapper.getReplyByLevel(i));
                e.setUser(userService.findUserById(e.getUid()));
            }
        });

        return replies;

    }

    public void replyAdd(Reply reply, HttpServletRequest request) {
        User nowUser = userService.getNowUser(request);
        reply.setUid(nowUser.getUid());
    }
}
