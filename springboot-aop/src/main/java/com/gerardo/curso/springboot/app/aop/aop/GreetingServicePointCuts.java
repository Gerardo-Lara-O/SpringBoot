package com.gerardo.curso.springboot.app.aop.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class GreetingServicePointCuts {

    @Pointcut("execution(String com.gerardo.curso.springboot.app.aop..*.*(..))")
    public void greetingLoggerPointCut(){}

    @Pointcut("execution(String com.gerardo.curso.springboot.app.aop..*.*(..))")
    public void greetingFooPointCut(){}
}
