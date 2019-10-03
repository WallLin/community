package com.kyrie.community.controller;

import com.kyrie.community.dto.CommentDTO;
import com.kyrie.community.dto.ResultDTO;
import com.kyrie.community.entity.TbComment;
import com.kyrie.community.entity.TbUser;
import com.kyrie.community.exception.CustomizeErrorCode;
import com.kyrie.community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author kyrie
 * @date 2019/10/2 - 11:35
 */
@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;
    /**
     * 添加评论功能
     * @return
     */
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    @ResponseBody
    public Object comment(@RequestBody CommentDTO commentDTO,
                          HttpServletRequest request) {
        TbUser user = (TbUser) request.getSession().getAttribute("user");
        if (user == null) {
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        TbComment tbComment = new TbComment();
        tbComment.setParentId(commentDTO.getParentId());
        tbComment.setContent(commentDTO.getContent());
        tbComment.setType(commentDTO.getType());
        tbComment.setCommentator(user.getId());
        tbComment.setGmtCreated(System.currentTimeMillis());
        tbComment.setGmtModified(System.currentTimeMillis());
        commentService.insert(tbComment);
        return ResultDTO.okOf();
    }
}
