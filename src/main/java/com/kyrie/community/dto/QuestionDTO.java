package com.kyrie.community.dto;

import com.kyrie.community.entity.TbUser;
import lombok.Data;

/**
 * @author kyrie
 * @date 2019/9/28 - 11:32
 */
@Data
public class QuestionDTO {
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
    private TbUser tbUser;
}
