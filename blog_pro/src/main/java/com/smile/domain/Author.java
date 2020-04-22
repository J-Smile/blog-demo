package com.smile.domain;

import com.sun.javafx.beans.IDProperty;
import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author: Smile
 * @description: 作者信息编辑
 * @create: 2020-04-07 22:18
 */
@Data
public class Author implements Serializable {
    private static final long serialVersionUID = 2331999577046731054L;

    @Id
    private Integer id;

    private String name;

    private String image;
    //工作
    private String work;
    //描述
    private String userDescribe;

    private String about;
    private String html;
}
