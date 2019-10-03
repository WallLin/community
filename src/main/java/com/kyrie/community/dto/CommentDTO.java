package com.kyrie.community.dto;

import lombok.Data;

/**
 * @author kyrie
 * @date 2019/10/2 - 14:19
 */
@Data
public class CommentDTO {
    private Long parentId;
    private Integer type;
    private String content;
}
