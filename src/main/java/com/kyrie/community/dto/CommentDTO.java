package com.kyrie.community.dto;

import com.kyrie.community.entity.TbUser;
import lombok.Data;

/**
 * 评论传输对象
 * @author kyrie
 * @date 2019/10/4 - 15:23
 */
@Data
public class CommentDTO {
    private Long id;
    private Long parentId;
    private Integer type;
    private Long gmtCreated;
    private Long gmtModified;
    private Long likeCount;
    private String content;
    private Long commentator;
    private TbUser user;
}
