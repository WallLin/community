package com.kyrie.community.entity;

import lombok.Data;

/**
 * @author kyrie
 * @date 2019/9/21 - 16:20
 */
@Data
public class Question {
    private Integer id;
    private String title;
    private String description;
    private Long gmtCreated;
    private Long gmtModified;
    private Integer creatorId;  // 创建者ID
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
    private String tag;
    private User user;
}
