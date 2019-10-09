package com.kyrie.community.mapper;

import com.kyrie.community.entity.TbComment;
import com.kyrie.community.entity.TbCommentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbCommentMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table community..tb_comment
     *
     * @mbg.generated Wed Oct 09 19:36:30 CST 2019
     */
    long countByExample(TbCommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table community..tb_comment
     *
     * @mbg.generated Wed Oct 09 19:36:30 CST 2019
     */
    int deleteByExample(TbCommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table community..tb_comment
     *
     * @mbg.generated Wed Oct 09 19:36:30 CST 2019
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table community..tb_comment
     *
     * @mbg.generated Wed Oct 09 19:36:30 CST 2019
     */
    int insert(TbComment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table community..tb_comment
     *
     * @mbg.generated Wed Oct 09 19:36:30 CST 2019
     */
    int insertSelective(TbComment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table community..tb_comment
     *
     * @mbg.generated Wed Oct 09 19:36:30 CST 2019
     */
    List<TbComment> selectByExample(TbCommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table community..tb_comment
     *
     * @mbg.generated Wed Oct 09 19:36:30 CST 2019
     */
    TbComment selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table community..tb_comment
     *
     * @mbg.generated Wed Oct 09 19:36:30 CST 2019
     */
    int updateByExampleSelective(@Param("record") TbComment record, @Param("example") TbCommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table community..tb_comment
     *
     * @mbg.generated Wed Oct 09 19:36:30 CST 2019
     */
    int updateByExample(@Param("record") TbComment record, @Param("example") TbCommentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table community..tb_comment
     *
     * @mbg.generated Wed Oct 09 19:36:30 CST 2019
     */
    int updateByPrimaryKeySelective(TbComment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table community..tb_comment
     *
     * @mbg.generated Wed Oct 09 19:36:30 CST 2019
     */
    int updateByPrimaryKey(TbComment record);
}