package com.kyrie.community.dto;

import lombok.Data;

/**
 * 创建评论时的传输对象
 * @author kyrie
 * @date 2019/10/2 - 14:19
 */
@Data
public class CommentCreateDTO {
    private Long parentId;
    private Integer type;
    private String content;
}
