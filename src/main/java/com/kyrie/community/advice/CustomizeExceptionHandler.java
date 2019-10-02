package com.kyrie.community.advice;

import com.kyrie.community.exception.CustomizeErrorCode;
import com.kyrie.community.exception.CustomizeException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 异常处理类
 * @author kyrie
 * @date 2019/9/29 - 15:39
 */
@ControllerAdvice
public class CustomizeExceptionHandler {

    @ExceptionHandler(Exception.class)
    ModelAndView handle(Throwable e, Model model) {
        // 如果是我们能处理的异常
        if (e instanceof CustomizeException) {
            model.addAttribute("message", e.getMessage());
        }
        else {
            model.addAttribute("message", CustomizeErrorCode.SYS_ERROR);
        }
        return new ModelAndView("error");
    }
}
