package com.kyrie.community.service;

import com.kyrie.community.dao.QuestionSearchDao;
import com.kyrie.community.dto.PaginationDTO;
import com.kyrie.community.dto.QuestionDTO;
import com.kyrie.community.entity.TbUser;
import com.kyrie.community.mapper.TbUserMapper;
import com.kyrie.community.pojo.QuestionElasticSearch;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kyrie
 * @date 2019/10/20 - 19:33
 */
@Service
public class QuestionSearchService {
    @Autowired
    private QuestionSearchDao questionSearchDao;

    @Autowired
    private TbUserMapper tbUserMapper;

    /**
     * 增加问题
     *
     * @param questionElasticSearch
     */
    public void add(QuestionElasticSearch questionElasticSearch) {
        questionSearchDao.save(questionElasticSearch);
    }

    /**
     * 根据关键词搜索问题
     * 分页
     *
     * @param search 搜索关键词
     * @return
     */
    public List<QuestionElasticSearch> findByTitleLike(Integer page, Integer size, String search) {
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return questionSearchDao.findByTitleOrDescriptionLikeOrderByGmtCreatedDesc(search, search, pageRequest);
    }

    /**
     * 根据关键词搜索问题
     *
     * @param page
     * @param size
     * @param search 搜索关键词
     * @return
     */
    public PaginationDTO search(Integer page, Integer size, String search) {

        Integer offset = page < 1 ? 0 : size * (page - 1); // 设置起始页码
        // 按问题创建时间倒序排序
        // 获取结果
        List<QuestionElasticSearch> questionElasticSearchList = findByTitleLike(page, size, search);
        // 计算总笔数
        int count = (int) questionSearchDao.countByTitleOrDescriptionLike(search, search);

        List<QuestionDTO> questions = new ArrayList<>();

        for (QuestionElasticSearch questionElasticSearch : questionElasticSearchList) {
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(questionElasticSearch, questionDTO);
            questions.add(questionDTO);
        }

        if (questions != null) {
            for (QuestionDTO question : questions) {
                TbUser tbUser = tbUserMapper.selectByPrimaryKey(question.getCreatorId());
                question.setTbUser(tbUser);
            }
        }
        PaginationDTO<QuestionDTO> pagination = new PaginationDTO<>();
        pagination.setData(questions);
        pagination.setPagination(count, page, size);

        return pagination;
    }
}
