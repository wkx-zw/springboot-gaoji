package com.cs.springbootvue.exception;



import com.cs.springbootvue.utils.BaseResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author yantao
 */
@RestControllerAdvice
public class GlobalException {

    @ResponseBody
    @ExceptionHandler(value = RuntimeException.class)
    public BaseResult IllegalArgumentException(RuntimeException e){
        String msg = e.getMessage();
        if (msg == null || msg.equals("")) {
            msg = "服务器出错";
        }
        BaseResult result= BaseResult.getBaseResult(1,null,null);
        return result;
    }

}
