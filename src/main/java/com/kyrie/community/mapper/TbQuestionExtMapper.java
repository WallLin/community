package com.kyrie.community.mapper;

import com.kyrie.community.dto.QuestionSearchDTO;
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

    /**
     * 根据 search 关键字搜索相关问题
     * @param questionSearchDTO
     * @return
     */
    List<TbQuestion> selectBySearch(QuestionSearchDTO questionSearchDTO);

    /**
     * 根据 search 关键字计算问题的总笔数
     * @param questionSearchDTO
     * @return
     */
    long countBySearch(QuestionSearchDTO questionSearchDTO);
}