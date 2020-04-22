package com.smile.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class Blog implements Serializable {
    private static final long serialVersionUID = 8374416560967088646L;
    /**
     * 唯一uid
     */
    @Id
    private String bid;

    /**
     * 博客标题
     */
    @Column(name = "title")
    private String title;


    /**
     * 博客内容
     */
    @Column(name = "content")
    private String content;

    private String html;


    /**
     * 博客点击数
     */
    @Column(name = "click_count")
    private Integer clickCount;
    // 评论次数
    @Column(name = "discuss_count")
    private Integer discussCount;

    /**
     * 标题图片
     */
    private String image;

    /**
     * 状态
     */
    @Column(name = "`status`")
    private Boolean status;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date updateTime;

    /**
     * 管理员uid
     */
    private String uid;

    /**
     * 是否原创（0:不是 1：是）
     */
    @Column(name = "is_original")
    private Boolean isOriginal;

    /**
     * 作者
     */
    @Column(name = "author")
    private String author;

    /**
     * 文章出处
     */
    @Column(name = "articles_part")
    private String articlesPart;

    /**
     * 博客分类UID
     */
    @Column(name = "`sort_id`")
    private Integer sortId;

    /**
     * 是否推荐
     */
    @Column(name = "`level`")
    private Boolean level;

    /**
     * 是否发布：0：否，1：是
     */
    @Column(name = "is_publish")
    private Boolean isPublish;

    private int[] tids;

    private List<Tag> tags;

    @Transient
    private String sortName;
}