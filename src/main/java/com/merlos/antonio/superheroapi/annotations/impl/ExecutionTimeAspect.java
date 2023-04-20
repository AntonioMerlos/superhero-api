package com.merlos.antonio.superheroapi.annotations.impl;

import com.merlos.antonio.superheroapi.annotations.ExecutionTime;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ExecutionTimeAspect {

    @Around("@annotation(executionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint, ExecutionTime executionTime) throws Throwable{

        Long startTime = System.nanoTime();

        Object result = joinPoint.proceed();

        Long endTime = System.nanoTime();
        Long duration = (endTime - startTime) / 1000000;

        log.info("{} took {} ms", executionTime.value(), duration);
        return result;
    }
}
