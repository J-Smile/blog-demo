package com.smile.vo;


import lombok.Data;

@Data
public class BaseResult {
    private Integer code;
    private String msg;
    private Object data;


    public BaseResult(CodeMessage codeMessage) {
        this.code = codeMessage.getCode();
        this.msg = codeMessage.getMsg();
    }

    public BaseResult() {
    }

    public static BaseResult success(Object data) {
        BaseResult baseResult = new BaseResult(CodeMessage.SUCCESS);
        baseResult.setData(data);
        return baseResult;
    }

    public static BaseResult success() {
        return new BaseResult(CodeMessage.SUCCESS);
    }

    public static BaseResult error(CodeMessage codeMessage) {
        return new BaseResult(codeMessage);
    }


}
