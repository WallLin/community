package com.kyrie.community.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kyrie.community.dto.PaginationDTO;
import com.kyrie.community.dto.QuestionDTO;
import com.kyrie.community.entity.TbQuestion;
import com.kyrie.community.entity.TbQuestionExample;
import com.kyrie.community.entity.TbUser;
import com.kyrie.community.exception.CustomizeErrorCode;
import com.kyrie.community.exception.CustomizeException;
import com.kyrie.community.mapper.TbQuestionMapper;
import com.kyrie.community.mapper.TbUserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kyrie
 * @date 2019/9/23 - 9:58
 */
@Service
public class QuestionService {

    @Autowired
    private TbUserMapper tbUserMapper;

    @Autowired
    private TbQuestionMapper tbQuestionMapper;

    /**
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    public PaginationDTO list(Integer page, Integer size) {
        Integer offset = (page - 1) * size;
        // PageHelper 使用非常简单，只需要设置页码和每页显示笔数即可
        PageHelper.startPage(offset, size);
        // 设置分页查询条件
        PageInfo<TbQuestion> pageInfo = new PageInfo<>(tbQuestionMapper.selectByExample(new TbQuestionExample()));

        // 获取结果
        List<TbQuestion> tbQuestions = pageInfo.getList();

        List<QuestionDTO> questions = new ArrayList<>();

        for (TbQuestion tbQuestion : tbQuestions) {
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(tbQuestion, questionDTO);
            questions.add(questionDTO);
        }
        Integer totalCount = (int) tbQuestionMapper.countByExample(new TbQuestionExample());

        if (questions != null) {
            for (QuestionDTO question : questions) {
                TbUser tbUser = tbUserMapper.selectByPrimaryKey(question.getCreatorId());
                question.setTbUser(tbUser);
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
    public PaginationDTO listByUserId(Long id, Integer page, Integer size) {
        Integer offset = (page - 1) * size;
        // PageHelper 使用非常简单，只需要设置页码和每页显示笔数即可
        PageHelper.startPage(offset, size);
        // 设置分页查询条件
        TbQuestionExample example = new TbQuestionExample();
        example.createCriteria().andCreatorIdEqualTo(id);
        PageInfo<TbQuestion> pageInfo = new PageInfo<>(tbQuestionMapper.selectByExample(example));
        List<TbQuestion> tbQuestions = pageInfo.getList();
        List<QuestionDTO> questions = new ArrayList<>();
        for (TbQuestion tbQuestion : tbQuestions) {
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(questionDTO, tbQuestion);
            questions.add(questionDTO);
        }
        Integer totalCount = (int) tbQuestionMapper.countByExample(new TbQuestionExample());

        if (questions != null) {
            for (QuestionDTO question : questions) {
                TbUser tbUser = tbUserMapper.selectByPrimaryKey(question.getCreatorId());
                question.setTbUser(tbUser);
            }
        }
        PaginationDTO pagination = new PaginationDTO();
        pagination.setQuestions(questions);
        pagination.setPagination(totalCount, page, size);

        return pagination;
    }

    /**
     * 根据问题id查询问题详情
     * @param id
     * @return
     */
    public QuestionDTO findById(Long id) {
        TbQuestion tbQuestion = tbQuestionMapper.selectByPrimaryKey(id);
        QuestionDTO questionDTO = new QuestionDTO();
        if (tbQuestion == null) {
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        TbUser tbUser = tbUserMapper.selectByPrimaryKey(tbQuestion.getCreatorId());
        BeanUtils.copyProperties(tbQuestion, questionDTO);
        questionDTO.setTbUser(tbUser);
        return questionDTO;
    }

    /**
     * 创建或更新问题详情
     * @param id
     * @param title
     * @param description
     * @param tag
     */
    public void createOrUpdate(Long id, String title, String description, String tag, TbUser tbUser) {
        TbQuestion tbQuestion = new TbQuestion();
        tbQuestion.setTitle(title);
        tbQuestion.setDescription(description);
        tbQuestion.setTag(tag);
        tbQuestion.setGmtModified(System.currentTimeMillis());

        // 更新问题
        if (id != null) {
            tbQuestion.setId(id);
            int update = tbQuestionMapper.updateByPrimaryKeySelective(tbQuestion);
            if (update == 0) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
        }

        // 创建、发布新问题
        else {
            tbQuestion.setCreatorId(tbUser.getId());
            tbQuestion.setGmtCreated(System.currentTimeMillis());
            tbQuestionMapper.insertSelective(tbQuestion);
        }
    }
}
