package com.kyrie.community.controller;

import com.kyrie.community.dto.CommentDTO;
import com.kyrie.community.dto.QuestionDTO;
import com.kyrie.community.service.CommentService;
import com.kyrie.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * 问题控制器
 * @author kyrie
 * @date 2019/9/25 - 16:06
 */
@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Long id, Model model) {
        questionService.incViewCount(id);
        QuestionDTO questionDTO = questionService.findById(id);
        List<CommentDTO> comments = commentService.listByTargetId(id);
        model.addAttribute("question", questionDTO);
        model.addAttribute("comments", comments);
        return "question";
    }
}
