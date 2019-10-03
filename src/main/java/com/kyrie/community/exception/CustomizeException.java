package com.kyrie.community.exception;

/**
 * @author kyrie
 * @date 2019/9/30 - 9:13
 */
public class CustomizeException extends RuntimeException {
    Integer code;
    String message;

    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public CustomizeException(ICustomizeErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }
}
