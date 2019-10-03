package com.kyrie.community.enums;

/**
 * 评论类型枚举类
 * @author kyrie
 * @date 2019/10/3 - 10:55
 */
public enum CommentTypeEnum {
    QUESTION(1), // 问题
    COMMENT(2),  // 评论
    ;
    private Integer type;

    public Integer getType() {
        return type;
    }

    CommentTypeEnum(Integer type) {
        this.type = type;
    }

    /**
     * 判断评论类型是否存在
     * @param type
     * @return
     */
    public static boolean isExist(Integer type) {
        for (CommentTypeEnum value : CommentTypeEnum.values()) {
            if (value.getType().equals(type)) {
                return true;
            }
        }
        return false;
    }
}
