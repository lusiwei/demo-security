package com.lusiwei.web.aspect;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


/**
 * @Author: lusiwei
 * @Date: 2019/1/20 20:42
 * @Description:
 */
@Aspect
@Component
public class TimeAspect {
    @Around("execution(* com.lusiwei.web.controller.UserController.*(..))")
    public Object handleControllerMethod(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("time aspect start ");
        Object[] args = proceedingJoinPoint.getArgs();
        for (Object arg : args) {
            System.out.println("arg is "+arg);
        }
        long startTime = System.currentTimeMillis();
        Object proceed = proceedingJoinPoint.proceed();
        System.out.println("time aspect 耗时："+(System.currentTimeMillis()-startTime));
        System.out.println("time aspect end");
        return proceed;
    }
}
