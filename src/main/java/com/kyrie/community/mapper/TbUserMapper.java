package com.kyrie.community.mapper;

import com.kyrie.community.entity.TbUser;
import com.kyrie.community.entity.TbUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbUserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table community..tb_user
     *
     * @mbg.generated Sun Oct 06 09:34:41 CST 2019
     */
    long countByExample(TbUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table community..tb_user
     *
     * @mbg.generated Sun Oct 06 09:34:41 CST 2019
     */
    int deleteByExample(TbUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table community..tb_user
     *
     * @mbg.generated Sun Oct 06 09:34:41 CST 2019
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table community..tb_user
     *
     * @mbg.generated Sun Oct 06 09:34:41 CST 2019
     */
    int insert(TbUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table community..tb_user
     *
     * @mbg.generated Sun Oct 06 09:34:41 CST 2019
     */
    int insertSelective(TbUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table community..tb_user
     *
     * @mbg.generated Sun Oct 06 09:34:41 CST 2019
     */
    List<TbUser> selectByExample(TbUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table community..tb_user
     *
     * @mbg.generated Sun Oct 06 09:34:41 CST 2019
     */
    TbUser selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table community..tb_user
     *
     * @mbg.generated Sun Oct 06 09:34:41 CST 2019
     */
    int updateByExampleSelective(@Param("record") TbUser record, @Param("example") TbUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table community..tb_user
     *
     * @mbg.generated Sun Oct 06 09:34:41 CST 2019
     */
    int updateByExample(@Param("record") TbUser record, @Param("example") TbUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table community..tb_user
     *
     * @mbg.generated Sun Oct 06 09:34:41 CST 2019
     */
    int updateByPrimaryKeySelective(TbUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table community..tb_user
     *
     * @mbg.generated Sun Oct 06 09:34:41 CST 2019
     */
    int updateByPrimaryKey(TbUser record);
}