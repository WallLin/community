package com.kyrie.community.mapper;

import com.kyrie.community.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author kyrie
 * @date 2019/9/20 - 16:20
 */
@Mapper
public interface UserMapper {
    @Insert("insert into user (account_id, name, token, gmt_created, gmt_modified, avatar_url) values (#{accountId}, #{name}, #{token}, #{gmtCreated}, #{gmtModified}, #{avatarUrl})")
    void insert(User user);

    @Select("select * from user where token = #{token}")
    User findByToken(String token);

    @Select("select * from user where id = #{id}")
    User findById(Integer creatorId);

    /**
     * 判断用户是否存在
     * @param accountId
     * @return
     */
    @Select("select count(1) from user where account_id = #{accountId}")
    Integer findByAccountId(String accountId);

    @Update("update user set name=#{name}, token=#{token}, gmt_modified=#{gmtModified}, avatar_url=#{avatarUrl} where account_id = #{accountId}")
    void update(User user);
}
