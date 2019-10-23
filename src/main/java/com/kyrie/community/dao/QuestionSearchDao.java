package com.kyrie.community.dao;

import com.kyrie.community.pojo.QuestionElasticSearch;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import java.util.List;

/**
 * 问题数据访问层接口
 * @author kyrie
 * @date 2019/10/20 - 19:12
 */
public interface QuestionSearchDao extends ElasticsearchRepository<QuestionElasticSearch, Long> {
    /**
     * 根据关键字搜索标题和描述相关的问题, 带分页
     * @param title
     * @param description
     * @return
     */
    List<QuestionElasticSearch> findByTitleOrDescriptionLikeOrderByGmtCreatedDesc(String title, String description, Pageable pageable);

    /**
     * 根据关键字统计标题和描述相关的问题的总数
     * @param title
     * @param description
     * @return
     */
    long countByTitleOrDescriptionLike(String title, String description);
}
