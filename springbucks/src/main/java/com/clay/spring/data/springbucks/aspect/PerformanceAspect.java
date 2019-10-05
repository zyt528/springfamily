package com.clay.spring.data.springbucks.aspect;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author yuntzhao
 */

@Aspect
@Component
@Slf4j
public class PerformanceAspect {

    @Around("repositoryOps()")
    public Object logPerformance(ProceedingJoinPoint point) throws Throwable {
        long startTime = System.currentTimeMillis();
        String name = "-";
        String result = "Y";
        try {
            name = point.getSignature().toShortString();
            return point.proceed();
        } catch (Throwable e) {
            result = "N";
            throw e;
        } finally {
            long endTime = System.currentTimeMillis();
            log.info("{}; {}; {}ms", name, result, endTime - startTime);
        }
    }

    @Pointcut(value = "execution(* com.clay.spring.data.springbucks.repository..*(..))")
    private void repositoryOps() {

    }
}
