package com.smile.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Smile
 * @description: 评论
 * @create: 2020-04-07 17:26
 */
@Data
public class Comment implements Serializable {
    private static final long serialVersionUID = 936039304457658425L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 评论内容
     */
    private String text;

    /**
     * 博客id
     */
    private String bid;

    /**
     * 时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime time;

    private String uid;
    private Integer ismsg;
    private User user;
    private ArrayList<Reply> replies;
}
