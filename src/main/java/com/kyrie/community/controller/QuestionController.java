package com.kyrie.community.controller;

import com.kyrie.community.dto.QuestionDTO;
import com.kyrie.community.entity.TbQuestion;
import com.kyrie.community.mapper.TbQuestionExtMapper;
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

    @Autowired
    private TbQuestionExtMapper tbQuestionExtMapper;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Long id,
                            Model model) {
        TbQuestion tbQuestion = new TbQuestion();
        tbQuestion.setId(id);
        tbQuestion.setViewCount(1L);
        tbQuestionExtMapper.incViewCount(tbQuestion);
        QuestionDTO questionDTO = questionService.findById(id);
        model.addAttribute("question", questionDTO);
        return "question";
    }
}
