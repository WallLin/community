package com.kyrie.community.dto;

import lombok.Data;

import java.util.List;

/**
 * 标签传输对象
 * @author kyrie
 * @date 2019/10/7 - 15:51
 */
@Data
public class TagDTO {
    private String categoryName;
    private List<String> tags;
}
