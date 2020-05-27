package com.lkf.validation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

/**
 * todo
 *
 * @author 刘凯峰
 * @version V1.0
 * update-logs:方法变更说明
 * ****************************************************
 * name:
 * date:
 * description:
 * *****************************************************
 * @date 2020-04-24 15:28
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler2 {


    /**
     * @param e
     * @return com.eddue.datav.base.ResponseResult
     * @description 方法参数异常处理
     */
//    @ExceptionHandler(value = {ConstraintViolationException.class})
//    @ResponseBody
//    public ResponseResult handleResourceNotFoundException(ConstraintViolationException e) {
//        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
//        StringBuilder strBuilder = new StringBuilder();
//        for (ConstraintViolation<?> violation : violations) {
//            strBuilder.append(violation.getMessage());
//        }
//        return ResponseResult.builder().code(CodeEnum.SYS_SERVER_ERROR.getCode()).msg(strBuilder.toString()).build();
//    }


}
