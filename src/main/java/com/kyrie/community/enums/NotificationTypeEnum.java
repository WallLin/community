package com.kyrie.community.enums;

/**
 * @author kyrie
 * @date 2019/10/9 - 16:37
 */
public enum NotificationTypeEnum {
    QUESTION(1,"回复了问题"),
    COMMENT(2, "回复了评论"),
    ;

    private Integer type;
    private String typeName;

    NotificationTypeEnum(Integer type, String typeName) {
        this.type = type;
        this.typeName = typeName;
    }

    public Integer getType() {
        return type;
    }

    public String getTypeName() {
        return typeName;
    }

    /**
     * 根据通知类型获取类型名称
     * @param type
     * @return
     */
    public static String nameOfType(Integer type) {
        for (NotificationTypeEnum notificationTypeEnum : NotificationTypeEnum.values()) {
            if (notificationTypeEnum.getType().equals(type)) {
                return notificationTypeEnum.getTypeName();
            }
        }

        return "";
    }
}
