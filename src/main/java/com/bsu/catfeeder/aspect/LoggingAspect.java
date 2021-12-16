package com.bsu.catfeeder.aspect;

import com.bsu.catfeeder.entity.Log;
import com.bsu.catfeeder.service.LogService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
@RequiredArgsConstructor
public class LoggingAspect {

    private final LogService logService;

    @Around("@annotation(com.bsu.catfeeder.annotation.LogMe)")
    public void logFeederServiceMethods(ProceedingJoinPoint joinPoint) throws Throwable{
        joinPoint.proceed();
        logService.addLog(new Log(LocalDateTime.now(),"executing method: "+joinPoint.toString()));
    }
}
