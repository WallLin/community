package com.kyrie.community.entity;

import lombok.Data;

/**
 * 用户实体类
 * @author kyrie
 * @date 2019/9/20 - 15:39
 */
@Data
public class User {
    private Integer id;
    private String accountId;
    private String name;
    private String token;
    private Long gmtCreated;
    private Long gmtModified;
    private String avatarUrl;  // 头像地址
}
