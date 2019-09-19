package com.kyrie.community.controller;

import com.kyrie.community.dto.AccessTokenDTO;
import com.kyrie.community.dto.UserDTO;
import com.kyrie.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * @author kyrie
 * @date 2019/9/19 - 17:12
 */
@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                            @RequestParam(name = "state") String state
                            ) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id("c047774e5c90ab0d8ce3");
        accessTokenDTO.setClient_secret("78def34915b4cdd5644a9df561b787eefdd3827b");
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setRedirect_uri("http://localhost:8887/callback");
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        UserDTO userDTO = githubProvider.getUserDTO(accessToken);
        System.out.println(userDTO.getName());
        return "index";
    }
}
