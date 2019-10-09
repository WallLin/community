package com.kyrie.community.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kyrie.community.dto.NotificationDTO;
import com.kyrie.community.dto.PaginationDTO;
import com.kyrie.community.entity.TbNotification;
import com.kyrie.community.entity.TbNotificationExample;
import com.kyrie.community.entity.TbUser;
import com.kyrie.community.enums.NotificationStatusEnum;
import com.kyrie.community.enums.NotificationTypeEnum;
import com.kyrie.community.exception.CustomizeErrorCode;
import com.kyrie.community.exception.CustomizeException;
import com.kyrie.community.mapper.TbNotificationMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author kyrie
 * @date 2019/10/9 - 15:41
 */
@Service
public class NotificationService {

    @Autowired
    private TbNotificationMapper tbNotificationMapper;

    /**
     * 根据用户 id 查询回复内容
     * @param id
     * @param page
     * @param size
     * @return
     */
    public PaginationDTO listByUserId(Long id, Integer page, Integer size) {
        // PageHelper 使用非常简单，只需要设置页码和每页显示笔数即可
        // 起始页码从 1 开始
        PageHelper.startPage(page, size);  // startPage 紧接后面的第一个查询语句才有分页功能
        // 设置分页查询条件
        TbNotificationExample example = new TbNotificationExample();
        example.createCriteria().andReceiverIdEqualTo(id);
        // 按问题创建时间倒序排序
        example.setOrderByClause("gmt_created desc");
        PageInfo<TbNotification> pageInfo = new PageInfo<>(tbNotificationMapper.selectByExample(example));
        // 计算总笔数
        Integer totalCount = (int) tbNotificationMapper.countByExample(example);
        List<TbNotification> notifications = pageInfo.getList();

        List<NotificationDTO> notificationDTOS = notifications.stream().map(notification -> {
            NotificationDTO notificationDTO = new NotificationDTO();
            BeanUtils.copyProperties(notification, notificationDTO);
            notificationDTO.setTypeName(NotificationTypeEnum.nameOfType(notification.getType()));
            return notificationDTO;
        }).collect(Collectors.toList());

        PaginationDTO<NotificationDTO> pagination = new PaginationDTO<>();
        pagination.setData(notificationDTOS);
        pagination.setPagination(totalCount, page, size);

        return pagination;
    }

    /**
     * 更新回复的状态
     * @param id
     * @param user
     */
    public NotificationDTO readReply(Long id, TbUser user) {
        TbNotification tbNotification = tbNotificationMapper.selectByPrimaryKey(id);
        if (tbNotification == null) {
            throw new CustomizeException(CustomizeErrorCode.NOTIFICATION_NOT_FOUND);
        }

        if (tbNotification.getReceiverId() != user.getId()) {
            throw new CustomizeException(CustomizeErrorCode.NOTIFICATION_WRONG);
        }
        // 更新通知状态为已读
        tbNotification.setStatus(NotificationStatusEnum.READ.getStatus());
        tbNotificationMapper.updateByPrimaryKey(tbNotification);
        NotificationDTO notificationDTO = new NotificationDTO();
        BeanUtils.copyProperties(tbNotification, notificationDTO);
        return notificationDTO;
    }
}
