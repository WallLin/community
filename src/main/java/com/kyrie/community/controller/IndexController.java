package com.kyrie.community.controller;

import com.kyrie.community.dto.PaginationDTO;
import com.kyrie.community.mapper.UserMapper;
import com.kyrie.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author kyrie
 * @date 2019/9/18 - 17:11
 */
@Controller
public class IndexController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(@RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "10") Integer size,
                        Model model) {

        PaginationDTO pagination = questionService.list(page, size);

        model.addAttribute("pagination", pagination);

        return "index";
    }
}
