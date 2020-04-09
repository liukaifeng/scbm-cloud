package com.lkf.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

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
 * @date 2020-04-09 15:43
 */
@Slf4j
@Aspect
@Configuration
public class LogAspect {

    @Around(value = "execution(* com.lkf.aop..*(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {

        log.info("请求参数：pjp.getArgs—— {}, pjp.getSimpleName()——{}", Arrays.toString(pjp.getArgs()),  pjp.getTarget().getClass().getSimpleName());
        Object proceed = pjp.proceed();
        if (proceed == null) {
            log.info("处理结果：null");
        } else {
            log.info("处理结果：{}", proceed.toString());
        }
        return proceed;
    }
}
