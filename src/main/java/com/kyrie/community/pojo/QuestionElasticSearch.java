package com.kyrie.community.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.io.Serializable;

/**
 * ElasticSearch 实现搜索模块
 * 问题实体类
 * @author kyrie
 * @date 2019/10/20 - 12:34
 */
@Data
@Document(indexName = "community", type = "question")
public class QuestionElasticSearch implements Serializable {
    @Id
    private Long id;
    @Field(index = true, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String title;
    @Field(index = true, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String description;

    private Long creatorId;
    private Long commentCount;
    private Long viewCount;
    private Long likeCount;
    private String tag;
    private Long gmtCreated;
    private Long gmtModified;
}
