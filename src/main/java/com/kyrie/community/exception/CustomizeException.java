package com.kyrie.community.exception;

/**
 * @author kyrie
 * @date 2019/9/30 - 9:13
 */
public class CustomizeException extends RuntimeException {
    String message;

    @Override
    public String getMessage() {
        return message;
    }

    public CustomizeException(ICustomizeErrorCode errorCode) {
        this.message = errorCode.getMessage();
    }
}
