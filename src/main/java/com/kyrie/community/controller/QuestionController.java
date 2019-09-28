package com.kyrie.community.controller;

import com.kyrie.community.dto.QuestionDTO;
import com.kyrie.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author kyrie
 * @date 2019/9/25 - 16:06
 */
@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Integer id,
                            Model model) {
        QuestionDTO questionDTO = questionService.findById(id);
        model.addAttribute("question", questionDTO);
        return "question";
    }
}