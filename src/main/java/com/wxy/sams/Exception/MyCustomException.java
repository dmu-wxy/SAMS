package com.wxy.sams.Exception;

import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * ControllerAdvice作用：
 * 1.处理全局异常
 * 2.预设全局数据
 * 3.请求参数预处理
 */
@ControllerAdvice
public class MyCustomException {

    @ExceptionHandler(NotFoundException.class)
    public ModelAndView notFoundException(NotFoundException e){
        ModelAndView modelAndView = new ModelAndView("/notFound");
        return modelAndView;
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public void maxUploadSizeExceededException(MaxUploadSizeExceededException e, HttpServletResponse response){
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.write("上传文件大小超出限制");
            writer.flush();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } finally {
            if(writer != null)
                writer.close();
        }
    }
}
