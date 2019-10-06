package com.kyrie.community.service;

import com.kyrie.community.dto.CommentDTO;
import com.kyrie.community.entity.*;
import com.kyrie.community.enums.CommentTypeEnum;
import com.kyrie.community.exception.CustomizeErrorCode;
import com.kyrie.community.exception.CustomizeException;
import com.kyrie.community.mapper.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author kyrie
 * @date 2019/10/2 - 16:31
 */
@Service
public class CommentService {
    @Autowired
    private TbQuestionMapper tbQuestionMapper;

    @Autowired
    private TbQuestionExtMapper tbQuestionExtMapper;

    @Autowired
    private TbCommentMapper tbCommentMapper;

    @Autowired
    private TbCommentExtMapper tbCommentExtMapper;

    @Autowired
    private TbUserMapper tbUserMapper;

    /**
     * 添加评论
     * @param tbComment
     */
    @Transactional
    public void insert(TbComment tbComment) {
        // 未选中任何问题或评论进行回复
        if (tbComment.getParentId() == null || tbComment.getParentId() == 0) {
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }

        // 评论类型错误或不存在
        if (tbComment.getType() == null || !CommentTypeEnum.isExist(tbComment.getType())) {
            throw new CustomizeException(CustomizeErrorCode.TYPE_PARAM_WRONG);
        }

        else {
            // 回复问题
            if (tbComment.getType().equals(CommentTypeEnum.QUESTION.getType())) {
                TbQuestion tbQuestion = tbQuestionMapper.selectByPrimaryKey(tbComment.getParentId());
                // 回复的问题不存在
                if (tbQuestion == null) {
                    throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
                }
                tbCommentMapper.insertSelective(tbComment);
                // 增加问题的回复数
                tbQuestion.setCommentCount(1L);
                tbQuestionExtMapper.incCommentCount(tbQuestion);
            }

            // 回复评论
            else if (tbComment.getType().equals(CommentTypeEnum.COMMENT.getType())) {
                TbComment comment = tbCommentMapper.selectByPrimaryKey(tbComment.getParentId());
                // 回复的评论不存在
                if (comment == null) {
                    throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
                }

                // 增加评论的回复数
                TbComment tbComment1 = new TbComment();
                tbComment1.setId(comment.getId());
                tbComment1.setCommentCount(1L);
                tbCommentExtMapper.incCommentCount(tbComment1);

                tbCommentMapper.insertSelective(tbComment);
            }
        }
    }

    /**
     * 根据目标 id 查找相关评论
     * @param id
     * @param type
     * @return
     */
    public List<CommentDTO> listByTargetId(Long id, CommentTypeEnum type) {
        // 获取该问题的所有评论
        TbCommentExample commentExample = new TbCommentExample();
        commentExample.createCriteria().andParentIdEqualTo(id).andTypeEqualTo(type.getType());
        // 按回复创建时间倒序排序
        commentExample.setOrderByClause("gmt_created desc");
        List<TbComment> comments = tbCommentMapper.selectByExample(commentExample);

        if (comments.size() == 0) {
            return new ArrayList<>();
        }

        // 获取去重的评论人
        // 这里使用了JDK8的新特性，stream 和 lambda 表达式
        Set<Long> commentators = comments.stream().map(comment -> comment.getCommentator()).collect(Collectors.toSet());
        List<Long> userIds = new ArrayList<>();
        userIds.addAll(commentators);

        // 获取评论人并转换为 Map，降低查询的时间复杂度
        TbUserExample userExample = new TbUserExample();
        userExample.createCriteria().andIdIn(userIds);
        List<TbUser> users = tbUserMapper.selectByExample(userExample);
        Map<Long, TbUser> userMap = users.stream().collect(Collectors.toMap(user -> user.getId(), user -> user));

        // 转换 comment 为 commentDTO
        List<CommentDTO> commentDTOS = comments.stream().map(comment -> {
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(comment, commentDTO);
            commentDTO.setUser(userMap.get(comment.getCommentator()));
            return commentDTO;
        }).collect(Collectors.toList());

        return commentDTOS;
    }
}
