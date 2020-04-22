package com.smile.domain;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "`user`")
public class User implements Serializable {
    private static final long serialVersionUID = 7938674712949466667L;
    @Id
    private String uid;
    private String username;
    private String avatar;
    private String nickname;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String password;
    private Boolean ifadmin;
    private String gender;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date birthday;
    private String email;
    private String qq;
    private String work;
    private String userDesc;


}