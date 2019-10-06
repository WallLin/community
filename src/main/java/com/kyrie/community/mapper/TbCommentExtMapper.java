package com.kyrie.community.mapper;

import com.kyrie.community.entity.TbComment;
import com.kyrie.community.entity.TbCommentExample;
import com.kyrie.community.entity.TbQuestion;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbCommentExtMapper {
    /**
     * 更新评论回复数
     * @param tbComment
     */
    void incCommentCount(TbComment tbComment);
}