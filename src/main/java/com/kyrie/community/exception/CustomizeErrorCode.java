package com.kyrie.community.exception;

/**
 * @author kyrie
 * @date 2019/9/30 - 16:12
 */
public enum CustomizeErrorCode implements ICustomizeErrorCode {
    QUESTION_NOT_FOUND(2001, "问题不在了，要不要换个试试？"),
    SYS_ERROR(2002, "服务冒烟了，要不然你稍后再试试！！！"),
    NO_LOGIN(2003, "当前操作需要登录，请登陆后重试"),
    TARGET_PARAM_NOT_FOUND(2004, "未选中任何问题或评论进行回复"),
    TYPE_PARAM_WRONG(2005, "未选中任何问题或评论进行回复"),
    COMMENT_NOT_FOUND(2006, "回复的评论不存在了，要不要换个试试？"),
    CONTENT_NO_EMPTY(2007, "评论内容不能为空~~~"),
    ;

    CustomizeErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;
    private String message;

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }


}
