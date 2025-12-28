package com.example.security.aspect;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Slf4j
@Component
@Aspect
public class LoggingAspectInterceptor {

    @Around("within(@org.springframework.web.bind.annotation.RestController *)")
    public Object loggingInterceptor(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();

        String uri = request.getRequestURI();
        String method = request.getMethod();

        long start = System.currentTimeMillis();
        Object jointPoint = proceedingJoinPoint.proceed();
        long timePeriod = System.currentTimeMillis() - start;

        Signature signature = proceedingJoinPoint.getSignature();
        String controllerMethod = signature.getDeclaringTypeName()+"."+signature.getName();
        log.info(String.format("[%s] %s executed by %s in %d ms ",method,uri,controllerMethod,timePeriod));
        log.info(String.format("Response : %s",response));
        return jointPoint;

    }
}
