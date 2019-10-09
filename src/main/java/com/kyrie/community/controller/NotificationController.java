package com.kyrie.community.controller;

import com.kyrie.community.dto.NotificationDTO;
import com.kyrie.community.entity.TbUser;
import com.kyrie.community.enums.NotificationTypeEnum;
import com.kyrie.community.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

/**
 * @author kyrie
 * @date 2019/10/9 - 20:48
 */
@Controller
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    /**
     * 查看回复内容，将未读信息更新为已读
     * @param id
     * @param model
     * @param request
     * @return
     */
    @GetMapping("/notification/{id}")
    public String readReply(@PathVariable(name = "id") Long id, Model model,
                            HttpServletRequest request) {
        TbUser user = (TbUser) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }
        NotificationDTO notificationDTO = notificationService.readReply(id, user);

        // 判断通知类型是否正确
        if (NotificationTypeEnum.COMMENT.getType() == notificationDTO.getType()
                || NotificationTypeEnum.QUESTION.getType() == notificationDTO.getType()) {
            return "redirect:/question/" + notificationDTO.getOuterId();
        } else {
            return "redirect:/";
        }
    }
}
