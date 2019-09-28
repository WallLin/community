package com.kyrie.community.controller;

import com.kyrie.community.dto.AccessTokenDTO;
import com.kyrie.community.dto.UserDTO;
import com.kyrie.community.entity.TbUser;
import com.kyrie.community.provider.GithubProvider;
import com.kyrie.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;


/**
 * @author kyrie
 * @date 2019/9/19 - 17:12
 */
@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirectUri;

    @Autowired
    private UserService userService;


    /**
     * 回调 github接口
     * 获取 github 上用户信息
     * @param code
     * @param state
     * @param response
     * @return
     */
    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code, @RequestParam(name = "state") String state, HttpServletResponse response) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setRedirect_uri(redirectUri);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        UserDTO userDTO = githubProvider.getUserDTO(accessToken);
        if (userDTO != null) {
            TbUser tbUser = new TbUser();
            tbUser.setToken(UUID.randomUUID().toString());
            tbUser.setName(userDTO.getName());
            tbUser.setAccountId(String.valueOf(userDTO.getId()));
            tbUser.setAvatarUrl(userDTO.getAvatarUrl());
            userService.createOrUpdate(tbUser);  // 创建或更新用户信息
            //将用户登录状态的信息放到Cookie中
            response.addCookie(new Cookie("token", tbUser.getToken()));
            return "redirect:/";
        }
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request,
                         HttpServletResponse response
    ) {
        // 移除 session
        request.getSession().removeAttribute("user");
        // 移除 Cookie
        Cookie cookie = new Cookie("token", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        return "redirect:/";
    }
}
