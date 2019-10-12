package com.kyrie.community.service;

import com.kyrie.community.entity.TbUser;
import com.kyrie.community.entity.TbUserExample;
import com.kyrie.community.mapper.TbUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author kyrie
 * @date 2019/9/26 - 15:15
 */
@Service
public class UserService {
    @Autowired
    private TbUserMapper tbUserMapper;

    /**
     * 注册或更新用户信息
     * @param tbUser
     */
    public void createOrUpdate(TbUser tbUser) {
        TbUserExample example = new TbUserExample();
        example.createCriteria().andAccountIdEqualTo(tbUser.getAccountId());
        long count = tbUserMapper.countByExample(example);

        // 更新用户的修改信息时间
        tbUser.setGmtModified(System.currentTimeMillis());

        // 创建新用户
        if (count == 0) {
            tbUser.setGmtCreated(System.currentTimeMillis());
            tbUserMapper.insert(tbUser);
        }
        // 更新用户信息
        else {
            TbUserExample tbUserExample = new TbUserExample();
            tbUserExample.createCriteria().andAccountIdEqualTo(tbUser.getAccountId());
            tbUserMapper.updateByExampleSelective(tbUser, tbUserExample);
        }
    }
}
