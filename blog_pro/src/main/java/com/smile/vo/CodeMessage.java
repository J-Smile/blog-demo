package com.smile.vo;


import lombok.Data;

@Data
public class CodeMessage {
    private Integer code;
    private String msg;

    public CodeMessage() {
    }

    public CodeMessage(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static final CodeMessage SUCCESS = new CodeMessage(0, "success");
    public static final CodeMessage ERROR = new CodeMessage(500, "非法操作！");
    public static final CodeMessage LOGIN_ERROR = new CodeMessage(1000, "用户名或密码不正确！");

    public static final CodeMessage LOGIN_INVALID = new CodeMessage(1100, "登录失效，请重新登录！");

    public static final CodeMessage SEARCH_ERROR = new CodeMessage(2000, "查询失败！");
    public static final CodeMessage UPDATE_ERROR = new CodeMessage(3000, "更新失败！");
    public static final CodeMessage DELETE_ERROR = new CodeMessage(4000, "删除失败！");
    public static final CodeMessage ADD_ERROR = new CodeMessage(5000, "添加失败！");
    public static final CodeMessage IO_ERROR = new CodeMessage(6000, "io异常！");
    public static final CodeMessage BLOG_PARAMS_ERROR = new CodeMessage(7000, "博客参数异常！");

    public static final CodeMessage COMMENT_ERROR = new CodeMessage(8000, "评论错误！");


}
