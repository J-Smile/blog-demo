package com.smile.domain;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Table(name = "tag")
public class Tag implements Serializable {

    private static final long serialVersionUID = -8842688697200102551L;

    @Id
    private Integer tid;

    private String content;

    private Boolean status;


}