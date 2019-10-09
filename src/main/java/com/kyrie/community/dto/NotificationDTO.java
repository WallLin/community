package com.kyrie.community.dto;

import com.kyrie.community.entity.TbUser;
import lombok.Data;

/**
 * 通知传输对象
 * @author kyrie
 * @date 2019/10/9 - 17:08
 */
@Data
public class NotificationDTO {
    private Long id;
    private String notifierName;
    private Long receiverId;
    private Long outerId;
    private String outerTitle;
    private Integer type;     // 通知类型，问题 / 评论
    private String typeName;  // 通知类型名称
    private Integer status;
    private Long gmtCreated;
    private TbUser user;
}
