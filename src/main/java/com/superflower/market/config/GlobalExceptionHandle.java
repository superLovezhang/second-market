package com.superflower.market.config;

import com.superflower.market.entity.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandle {

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public Result illegalException(Exception e) {
        return Result.fail(2002l, e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result exceptionHandle(Exception e) {
        return Result.fail(2001l, e.getMessage());
    }

}
