package com.kyrie.community.service;

import com.kyrie.community.entity.User;
import com.kyrie.community.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author kyrie
 * @date 2019/9/26 - 15:15
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 注册或更新用户信息
     * @param user
     */
    public void createOrUpdate(User user) {
        Integer count = userMapper.findByAccountId(user.getAccountId());

        // 更新用户的修改信息时间
        user.setGmtModified(System.currentTimeMillis());

        // 创建新用户
        if (count == 0) {
            user.setGmtCreated(System.currentTimeMillis());
            userMapper.insert(user);
        }
        // 更新用户信息
        else {
            userMapper.update(user);
        }
    }
}
