package com.dbcow.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

@Aspect
@Component
@Slf4j
public class LoggingAdvice {

    @Before("execution(* com.dbcow.*.*.*(..))")
    public void invokeApiControllerBefore(JoinPoint joinPoint) {
        outputLog(joinPoint);
    }


    @AfterReturning(pointcut = "execution(* com.dbcow.*.*.*(..))", returning = "returnValue")
    public void invokeControllerAfter(JoinPoint joinPoint, Object returnValue) {
        outputLog(joinPoint, returnValue);
    }

    @AfterThrowing(value = "execution(* com.dbcow.*.*.*(..))", throwing = "e")
    public void invokeControllerAfterThrowing(JoinPoint joinPoint, Throwable e) {
        outputErrorLog(joinPoint, e);
    }


    private void outputLog(JoinPoint joinPoint) {
        String logMessage = "[DEBUG:開始]"+ getClassName(joinPoint) + "." + getSignatureName(joinPoint) + "[引数]" + getArguments(joinPoint);
        log.info(logMessage);
    }

    private void outputLog(JoinPoint joinPoint, Object returnValue) {
        String logMessage = "[DEBUG:終了]"+ getClassName(joinPoint) + "." + getSignatureName(joinPoint) + "[戻値]" + getReturnValue(returnValue);
        log.info(logMessage);
    }

    private void outputErrorLog(JoinPoint joinPoint, Throwable e) {
        String logMessage = "[DEBUG:例外]"+ getClassName(joinPoint) + "." + getSignatureName(joinPoint) + "[引数]" + getArguments(joinPoint);
        log.info(logMessage);
        //log.error(logMessage, e);
    }

    private String getClassName(JoinPoint joinPoint) {
        return joinPoint.getTarget().getClass().getSimpleName();
    }

    private String getSignatureName(JoinPoint joinPoint) {
        return joinPoint.getSignature().getName();
    }

    private String getArguments(JoinPoint joinPoint) {
        if (joinPoint.getArgs() == null) { return "null"; }

        Object[] arguments = joinPoint.getArgs();
        ArrayList<String> argumentStrings = new ArrayList();

        for (Object argument : arguments) { if (argument != null) { argumentStrings.add(argument.toString()); } }
        return String.join(",", argumentStrings);
    }

    private String getReturnValue(Object returnValue) {
        return (returnValue != null) ? returnValue.toString() : "null";
    }
}