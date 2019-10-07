package com.kyrie.community.mapper;

import com.kyrie.community.entity.TbQuestion;

import java.util.List;

public interface TbQuestionExtMapper {
    /**
     * 更新阅读数
     * @param tbQuestion
     */
    void incViewCount(TbQuestion tbQuestion);

    /**
     * 更新问题回复数
     * @param tbQuestion
     */
    void incCommentCount(TbQuestion tbQuestion);

    /**
     * 根据 tag 查找相关问题
     * @param tbQuestion
     * @return
     */
    List<TbQuestion> selectRelated(TbQuestion tbQuestion);
}