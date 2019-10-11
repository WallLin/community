package com.kyrie.community.dto;

import lombok.Data;

/**
 * 文件上传传输对象
 * 图片上传
 * @author kyrie
 * @date 2019/10/11 - 11:23
 */
@Data
public class FileDTO {
    private Integer success;
    private String message;
    private String url;
}
