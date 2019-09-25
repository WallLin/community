package com.kyrie.community.dto;

import lombok.Data;

/**
 * @author kyrie
 * @date 2019/9/19 - 20:50
 */
@Data
public class UserDTO {
    private Long id;
    private String name;
    private String bio;        // 个性签名
    private String avatarUrl;  //头像地址
}
