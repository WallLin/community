package com.kyrie.community.controller;

import com.kyrie.community.dto.PaginationDTO;
import com.kyrie.community.entity.TbUser;
import com.kyrie.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @author kyrie
 * @date 2019/9/24 - 17:34
 */
@Controller
public class ProfileController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action") String action,
                          @RequestParam(name = "page", defaultValue = "1") Integer page,
                          @RequestParam(name = "size", defaultValue = "2") Integer size,
                          Model model,
                          HttpServletRequest request) {
        if ("questions".equals(action)) {
            model.addAttribute("sessions", action);
            model.addAttribute("sessionName", "我的提问");
        } else if ("replies".equals(action)) {
            model.addAttribute("sessions", action);
            model.addAttribute("sessionName", "最新回复");
        }
        TbUser user = (TbUser) request.getSession().getAttribute("user");

        PaginationDTO paginationDTO = questionService.listByUserId(user.getId(), page, size);
        model.addAttribute("pagination", paginationDTO);
        return "profile";
    }
}
