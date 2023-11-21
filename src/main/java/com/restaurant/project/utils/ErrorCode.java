package com.restaurant.project.utils;

public enum ErrorCode {
    SUCCESS(200,"success"),
    ERROR_SERVICE(504,"服务器内部错误，请尝试重新登录"),
    INVALID_EMAIL_OR_PASSWORD(100006,"无效的用户名或密码"),
    USER_HAS_EXIST(100007,"用户名已存在,请更换用户名"),
    USER_TOKEN_ISTIMEOUT(100008,"用户已过期，请从新登录"),
    ODER_INSERT_ERROR(100009,"订单失败，服务器内部错误"),
    USER_SET_VIP_ERROR(100010,"设置vip失败，服务器内部错误"),
    USER_ADD_INTEGRAL_ERROR(100011,"添加积分失败，服务器内部错误"),
    USER_INTEGRAL_NOT_ENOUGH(100012,"积分不够兑换此商品"),
    USER_CONSUME_COMBO_INTEGRAL_ERROR(100013,"兑换套餐优惠卷失败，服务器内部错误"),
    USER_HAS_THIS_COUPON(100014,"优惠卷已存在"),
    ADD_NEW_PRODUCT_ERROR(100015,"添加商品错误"),
    NO_FIND_FILE(100016,"没有选则文件"),
    NOT_DELETE_IMAGE(100017,"无法删除图片"),
    DELETE_IMAGE(100018,"删除照片错误"),
    NOT_HAVE_ADMIN_USER(100019,"目前没有管理员用户"),
    SELECT_USERNAME_ERROR(100020,"未找到用户名，服务器内部错误");
    int code;
    String message;

    ErrorCode(int code, String message){
        this.code = code;
        this.message = message;
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
}
