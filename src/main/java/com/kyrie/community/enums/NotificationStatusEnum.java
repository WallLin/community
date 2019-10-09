package com.kyrie.community.enums;

/**
 * @author kyrie
 * @date 2019/10/9 - 21:01
 */
public enum NotificationStatusEnum {
    UNREAD(1),
    READ(0),
    ;
    private Integer status;

    NotificationStatusEnum(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }
}
