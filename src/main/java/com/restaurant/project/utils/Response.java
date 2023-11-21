package com.restaurant.project.utils;

import javax.swing.plaf.PanelUI;

public class Response <T>{
    private int code;
    private String message;
    private T data = null;

    public Response(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Response(int code, String message) {
        this.code = code;
        this.message = message;
    }
    public static Response success(){
        return new Response(ErrorCode.SUCCESS.code, ErrorCode.SUCCESS.message);
    }

    public static <T> Response success(T data){
        return new Response(ErrorCode.SUCCESS.code,ErrorCode.SUCCESS.message,data);
    }
    public static Response error(ErrorCode errorCode){
        return new Response(errorCode.code, errorCode.message);
    }

    public static <T> Response error(ErrorCode errorCode, T data){
        return new Response(errorCode.code, errorCode.message, data);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
