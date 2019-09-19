package com.kyrie.community.provider;

import com.alibaba.fastjson.JSON;
import com.kyrie.community.dto.AccessTokenDTO;
import com.kyrie.community.dto.UserDTO;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author kyrie
 * @date 2019/9/19 - 17:24
 */
@Component
public class GithubProvider {
    public String getAccessToken(AccessTokenDTO accessTokenDTO) {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(JSON.toJSONString(accessTokenDTO), mediaType);
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String token = response.body().string().split("&")[0].split("=")[1];
            return token;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public UserDTO getUserDTO(String accessToken) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token=" + accessToken)
                .build();

        try (Response response = client.newCall(request).execute()) {
            UserDTO userDTO = JSON.parseObject(response.body().string(), UserDTO.class);
            return userDTO;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
