package com.kyrie.community.controller;

import com.kyrie.community.entity.Question;
import com.kyrie.community.entity.User;
import com.kyrie.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @author kyrie
 * @date 2019/9/20 - 19:52
 */
@Controller
public class PublishController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable(value = "id") Integer id,
                       Model model
    ) {
        Question question = questionService.findById(id);
        if (question != null) {
            model.addAttribute("title", question.getTitle());
            model.addAttribute("description", question.getDescription());
            model.addAttribute("tag", question.getTag());
            model.addAttribute("id", id);
            return "publish";
        }
        return null;
    }

    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

    @PostMapping("/publish")
    public String publish(
            @RequestParam(name = "title", required = false) String title,
            @RequestParam(name = "description", required = false) String description,
            @RequestParam(name = "tag", required = false) String tag,
            @RequestParam(name = "id", required = false) Integer id,
            Model model,
            HttpServletRequest request
    ) {
        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);
        if (title == null || title == "") {
            model.addAttribute("error", "问题标题不能为空！");
            return "publish";
        } else if (description == null || description == "") {
            model.addAttribute("error", "问题补充不能为空！");
            return "publish";
        } else if (tag == null || tag == "") {
            model.addAttribute("error", "标签不能为空！");
            return "publish";
        }

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            model.addAttribute("error", "用户未登录，请先登录再发布问题");
            return "publish";
        }

        questionService.createOrUpdate(id, title, description, tag, user);

        return "redirect:/";
    }
}
