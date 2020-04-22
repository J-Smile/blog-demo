package com.smile.domain;

import lombok.Data;

import javax.persistence.Id;

/**
 * @author: 淳淳
 * @create: 2020-03-25 17:49
 * @description:
 **/
@Data
public class BlogTag {
    @Id
    private Integer id;
    private String bid;
    private Integer tid;

}
