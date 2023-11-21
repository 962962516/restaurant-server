package com.restaurant.project.Exception;

/**
 * 用户已存在异常
 */
public class UserExistException extends Exception{
    public UserExistException(String message) {
        super(message);
    }
}
