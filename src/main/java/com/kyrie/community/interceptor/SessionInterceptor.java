package com.kyrie.community.interceptor;

import com.kyrie.community.entity.TbNotificationExample;
import com.kyrie.community.entity.TbUser;
import com.kyrie.community.entity.TbUserExample;
import com.kyrie.community.enums.NotificationStatusEnum;
import com.kyrie.community.mapper.TbNotificationMapper;
import com.kyrie.community.mapper.TbUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author kyrie
 * @date 2019/9/25 - 14:58
 */
@Service
public class SessionInterceptor implements HandlerInterceptor {

    @Autowired
    private TbUserMapper tbUserMapper;
    
    @Autowired
    private TbNotificationMapper tbNotificationMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    TbUserExample example = new TbUserExample();
                    example.createCriteria().andTokenEqualTo(token);
                    List<TbUser> tbUsers = tbUserMapper.selectByExample(example);
                    if (tbUsers.size() > 0) {
                        request.getSession().setAttribute("user", tbUsers.get(0));
                        // 查询该用户的未读通知总数
                        TbNotificationExample notificationExample = new TbNotificationExample();
                        notificationExample.createCriteria().andReceiverIdEqualTo(tbUsers.get(0).getId())
                                .andStatusEqualTo(NotificationStatusEnum.UNREAD.getStatus());
                        long unReadCount = tbNotificationMapper.countByExample(notificationExample);
                        request.getSession().setAttribute("unReadCount", unReadCount);
                    }
                    break;
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
