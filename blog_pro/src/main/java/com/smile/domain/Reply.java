package com.smile.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author: Smile
 * @description: 回复评论/留言
 * @create: 2020-04-13 08:52
 */
@Data
public class Reply implements Serializable {
    private static final long serialVersionUID = 1339465892435825216L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 评论内容
     */
    private String text;

    private Integer parentId;

    /**
     * 时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime time;
    /**
     * 评论级别
     */
    private Integer level;

    private String uid;
    private String toUid;
    private List<Reply> replies;
    private User user;
}
