package com.smile.exception;

/**
 * @author: Smile
 * @description: 用户相关异常
 * @create: 2020-04-11 13:24
 */

public class UserException extends RuntimeException {

    public UserException(String message) {
        super(message);
    }
}
