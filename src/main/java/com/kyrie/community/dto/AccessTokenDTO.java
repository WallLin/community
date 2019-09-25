package com.kyrie.community.dto;

import lombok.Data;

/**
 * @author kyrie
 * @date 2019/9/19 - 17:29
 */
@Data
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;
}
