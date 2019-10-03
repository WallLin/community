package com.kyrie.community.dto;

import com.kyrie.community.entity.TbUser;
import lombok.Data;

/**
 * @author kyrie
 * @date 2019/9/28 - 11:32
 */
@Data
public class QuestionDTO {
    private Long id;
    private String title;
    private String description;
    private Long gmtCreated;
    private Long gmtModified;
    private Long creatorId;  // 创建者ID
    private Long commentCount;
    private Long viewCount;
    private Long likeCount;
    private String tag;
    private TbUser tbUser;
}
