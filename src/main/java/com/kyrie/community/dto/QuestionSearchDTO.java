package com.kyrie.community.dto;

import lombok.Data;

/**
 * @author kyrie
 * @date 2019/10/14 - 9:44
 */
@Data
public class QuestionSearchDTO {
    private String search;
    private Integer page;
    private Integer size;
}
