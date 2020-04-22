package com.smile.domain;

import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author: Smile
 * @description:
 * @create: 2020-04-01 16:08
 */
@Data
public class Image implements Serializable {
    private static final long serialVersionUID = 7481929734776730009L;
    @Id
    private Integer id;
    private String image;
}
