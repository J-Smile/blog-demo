package com.smile.domain;

import lombok.Data;
import javax.persistence.Id;

/**
 * @author: Smile
 * @description:
 * @create: 2020-03-25 08:33
 * 分类
 */
@Data
public class Sort {

    @Id
    private Integer sid;
    private String content;
    private Boolean status;

}
