package com.kyrie.community.controller;

import com.kyrie.community.dto.AccessTokenDTO;
import com.kyrie.community.dto.UserDTO;
import com.kyrie.community.entity.User;
import com.kyrie.community.mapper.UserMapper;
import com.kyrie.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
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
    private UserMapper userMapper;

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
            User user = new User();
            user.setToken(UUID.randomUUID().toString());
            user.setName(userDTO.getName());
            user.setAccountId(String.valueOf(userDTO.getId()));
            user.setGmtCreated(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreated());
            user.setAvatarUrl(userDTO.getAvatarUrl());
            userMapper.insert(user);
            //将用户登录状态的信息放到Cookie中
            response.addCookie(new Cookie("token", user.getToken()));

            return "redirect:/";
        }
        return "redirect:/";
    }
}
