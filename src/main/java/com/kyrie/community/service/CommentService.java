package com.kyrie.community.service;

import com.kyrie.community.entity.TbComment;
import com.kyrie.community.entity.TbQuestion;
import com.kyrie.community.enums.CommentTypeEnum;
import com.kyrie.community.exception.CustomizeErrorCode;
import com.kyrie.community.exception.CustomizeException;
import com.kyrie.community.mapper.TbCommentMapper;
import com.kyrie.community.mapper.TbQuestionExtMapper;
import com.kyrie.community.mapper.TbQuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
                tbCommentMapper.insertSelective(tbComment);
            }
        }
    }
}
