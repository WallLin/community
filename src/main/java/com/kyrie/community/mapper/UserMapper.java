package com.kyrie.community.mapper;

import com.kyrie.community.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author kyrie
 * @date 2019/9/20 - 16:20
 */
@Mapper
public interface UserMapper {
    @Insert("insert into user (account_id, name, token, gmt_created, gmt_modified) values (#{accountId}, #{name}, #{token}, #{gmtCreated}, #{gmtModified})")
    void insert(User user);

    @Select("select * from user where token = #{token}")
    User findByToken(String token);
}
