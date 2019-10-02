package com.kyrie.community.exception;

/**
 * @author kyrie
 * @date 2019/9/30 - 16:12
 */
public enum CustomizeErrorCode implements ICustomizeErrorCode {
    QUESTION_NOT_FOUND("你找到问题不在了，要不要换个试试？"),
    SYS_ERROR("服务冒烟了，要不然你稍后再试试！！！");

    CustomizeErrorCode(String message) {
        this.message = message;
    }

    private String message;

    @Override
    public String getMessage() {
        return message;
    }


}
