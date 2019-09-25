package com.kyrie.community.service;

import com.kyrie.community.dto.PaginationDTO;
import com.kyrie.community.entity.Question;
import com.kyrie.community.entity.User;
import com.kyrie.community.mapper.QuestionMapper;
import com.kyrie.community.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author kyrie
 * @date 2019/9/23 - 9:58
 */
@Service
public class QuestionService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionMapper questionMapper;

    /**
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    public PaginationDTO list(Integer page, Integer size) {
        Integer offset = (page - 1) * size;
        List<Question> questions = questionMapper.list(offset, size);
        Integer totalCount = questionMapper.count();  // 总数
        if (questions != null) {
            for (Question question : questions) {
                User user = userMapper.findById(question.getCreatorId());
                question.setUser(user);
            }
        }
        PaginationDTO pagination = new PaginationDTO();
        pagination.setQuestions(questions);
        pagination.setPagination(totalCount, page, size);

        return pagination;
    }

    /**
     * 根据用户id查询用户的提问
     * @param id
     * @param page
     * @param size
     */
    public PaginationDTO listByUserId(Integer id, Integer page, Integer size) {
        Integer offset = (page - 1) * size;
        List<Question> questions = questionMapper.listByUserId(id, offset, size);
        Integer totalCount = questionMapper.countByUserId(id);  // 总数
        if (questions != null) {
            for (Question question : questions) {
                User user = userMapper.findById(question.getCreatorId());
                question.setUser(user);
            }
        }
        PaginationDTO pagination = new PaginationDTO();
        pagination.setQuestions(questions);
        pagination.setPagination(totalCount, page, size);

        return pagination;
    }
}
