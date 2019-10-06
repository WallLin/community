package com.kyrie.community.controller;

import com.kyrie.community.dto.CommentCreateDTO;
import com.kyrie.community.dto.CommentDTO;
import com.kyrie.community.dto.ResultDTO;
import com.kyrie.community.entity.TbComment;
import com.kyrie.community.entity.TbUser;
import com.kyrie.community.enums.CommentTypeEnum;
import com.kyrie.community.exception.CustomizeErrorCode;
import com.kyrie.community.service.CommentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    public Object comment(@RequestBody CommentCreateDTO commentCreateDTO,
                          HttpServletRequest request) {
        TbUser user = (TbUser) request.getSession().getAttribute("user");
        if (user == null) {
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        if (commentCreateDTO == null || StringUtils.isEmpty(commentCreateDTO.getContent())) {
            return ResultDTO.errorOf(CustomizeErrorCode.CONTENT_NO_EMPTY);
        }

        TbComment tbComment = new TbComment();
        tbComment.setParentId(commentCreateDTO.getParentId());
        tbComment.setContent(commentCreateDTO.getContent());
        tbComment.setType(commentCreateDTO.getType());
        tbComment.setCommentator(user.getId());
        tbComment.setGmtCreated(System.currentTimeMillis());
        tbComment.setGmtModified(System.currentTimeMillis());
        commentService.insert(tbComment);
        return ResultDTO.okOf();
    }

    @RequestMapping(value = "/comment/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResultDTO<List<CommentDTO>> subComment(@PathVariable(name = "id") Long id) {
        List<CommentDTO> comments = commentService.listByTargetId(id, CommentTypeEnum.COMMENT);
        return ResultDTO.okOf(comments);
    }
}
