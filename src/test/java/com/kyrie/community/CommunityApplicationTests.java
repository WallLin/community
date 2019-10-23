package com.kyrie.community;

import com.kyrie.community.dao.QuestionSearchDao;
import com.kyrie.community.pojo.QuestionElasticSearch;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommunityApplicationTests {
    @Autowired
    private QuestionSearchDao questionSearchDao;

    @Test
    public void createIndex() {
        QuestionElasticSearch question = new QuestionElasticSearch();
        question.setId(7L);
        question.setTitle("Spring 全家桶");
        question.setDescription("Spring Boot,Spring Cloud,Spring Data");
        question.setCreatorId(10L);
        question.setTag("java");
        question.setCommentCount(0L);
        question.setLikeCount(0L);
        question.setViewCount(0L);
        question.setGmtCreated(System.currentTimeMillis());
        question.setGmtModified(System.currentTimeMillis());
        questionSearchDao.index(question);
    }

    @Test
    public void findAll() {
        /*Iterable<QuestionElasticSearch> searchIterable = questionSearchDao.findByTitleOrDescriptionLikeOrderByGmtCreatedDesc("", "");
        List<QuestionElasticSearch> list = Lists.newArrayList();
        searchIterable.forEach(single -> {
            list.add(single);
        });

        list.forEach(question -> {
            System.out.println(question);
        });

        System.out.println("the total count is : " + questionSearchDao.countByTitleOrDescriptionLike("", ""));*/
    }
}
