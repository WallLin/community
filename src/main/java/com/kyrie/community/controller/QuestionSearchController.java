package com.kyrie.community.controller;

import com.kyrie.community.dto.PaginationDTO;
import com.kyrie.community.service.QuestionSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author kyrie
 * @date 2019/10/20 - 19:38
 */
@Controller
public class QuestionSearchController {
    @Autowired
    private QuestionSearchService questionSearchService;

    @GetMapping(value = "question/elasticSearch")
    public String question(@RequestParam(name = "page", defaultValue = "1") Integer page,
                           @RequestParam(name = "size", defaultValue = "5") Integer size,
                           @RequestParam(name = "search", required = false) String search,
                           Model model) {
        String replace = search.replace(" ", ""); // 去掉空格
        PaginationDTO pagination = questionSearchService.search(page, size, replace);
        model.addAttribute("pagination", pagination);
        model.addAttribute("search", search);
        return "index";
    }
}
