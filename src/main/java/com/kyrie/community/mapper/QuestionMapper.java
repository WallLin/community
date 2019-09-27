package com.kyrie.community.mapper;

import com.kyrie.community.entity.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author kyrie
 * @date 2019/9/21 - 16:42
 */
@Mapper
public interface QuestionMapper {

    @Insert("insert into question (title, description, gmt_created, gmt_modified, creator_id, tag) values (#{title}, #{description}, #{gmtCreated}, #{gmtModified}, #{creatorId}, #{tag})")
    void insert(Question question);

    @Select("select * from question limit #{offset}, #{size}")
    List<Question> list(@Param(value = "offset") Integer offset, @Param(value = "size") Integer size);

    @Select("select count(1) from question")
    Integer count();

    @Select("select * from question where creator_id = #{id} limit #{offset}, #{size}")
    List<Question> listByUserId(@Param(value = "id") Integer id, @Param(value = "offset") Integer offset, @Param(value = "size") Integer size);

    @Select("select count(1) from question where creator_id = #{id}")
    Integer countByUserId(@Param(value = "id") Integer id);

    @Select("select * from question where id = #{id}")
    Question findById(@Param(value = "id") Integer id);

    @Select("select count(1) from question where id = #{id}")
    Integer countById(@Param(value = "id") Integer id);

    @Update("update question set title=#{title}, description=#{description}, gmt_modified=#{gmtModified}, tag=#{tag} where id=#{id}")
    void update(Question question);
}
