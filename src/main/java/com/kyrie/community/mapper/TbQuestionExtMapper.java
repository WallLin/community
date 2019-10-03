package com.kyrie.community.mapper;

import com.kyrie.community.entity.TbQuestion;

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
}