package com.kyrie.community.dto;

import com.kyrie.community.exception.CustomizeErrorCode;
import com.kyrie.community.exception.CustomizeException;
import lombok.Data;

import java.util.List;

/**
 * 接口结果传输对象
 * @author kyrie
 * @date 2019/10/2 - 16:22
 */
@Data
public class ResultDTO<T> {
    private Integer code;
    private String message;
    private T data;

    public static ResultDTO errorOf(Integer code, String message) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(code);
        resultDTO.setMessage(message);
        return resultDTO;
    }

    public static ResultDTO errorOf(CustomizeErrorCode errorCode) {
        return errorOf(errorCode.getCode(), errorCode.getMessage());
    }

    public static ResultDTO errorOf(CustomizeException e) {
        return errorOf(e.getCode(), e.getMessage());
    }

    public static ResultDTO okOf() {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(200);
        resultDTO.setMessage("请求成功！");
        return resultDTO;
    }

    public static ResultDTO okOf(List<CommentDTO> comments) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(200);
        resultDTO.setMessage("请求成功！");
        resultDTO.setData(comments);
        return resultDTO;
    }
}
