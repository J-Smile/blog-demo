package com.smile.config;

import com.smile.exception.BlogException;
import com.smile.exception.CommentException;
import com.smile.exception.TokenException;
import com.smile.vo.BaseResult;
import com.smile.vo.CodeMessage;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author: Smile
 * @description:
 * @create: 2020-04-06 19:04
 */
//@ControllerAdvice
public class RunTimeExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public BaseResult handle(Exception e) {
        System.out.println(e.getMessage());
        if (e instanceof BlogException) {
            return BaseResult.error(CodeMessage.BLOG_PARAMS_ERROR);
        }else if (e instanceof CommentException){
            return BaseResult.error(CodeMessage.COMMENT_ERROR);
        }else if (e instanceof TokenException){
            return BaseResult.error(CodeMessage.LOGIN_INVALID);
        }
        return BaseResult.error(CodeMessage.ERROR);
    }
}

