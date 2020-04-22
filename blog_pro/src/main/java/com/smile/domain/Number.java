package com.smile.domain;

import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author: Smile
 * @description: 号码
 * @create: 2020-04-08 21:46
 */
@Data
public class Number implements Serializable {
    private static final long serialVersionUID = 8808839381179653845L;

    @Id
    private Integer id;
    private String qq;
    private String wx;
    private String mail;
    private String github;
    private String gitee;

}
