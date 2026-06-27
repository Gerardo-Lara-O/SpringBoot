package com.gerardo.curso.springboot.app.aop.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(2)
@Aspect
@Component
public class GreetingAspect {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(String com.gerardo.curso.springboot.app.aop..*.*(..))")
    private void greetingLoggerPointCut(){}

    @Before("greetingLoggerPointCut")
    public void loggerBefore(JoinPoint joinPoint){
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());

        logger.info("\nAntes: " + method + " con los argumentos " + args);
    }

    @After("greetingLoggerPointCut")
    public void loggerAfter(JoinPoint joinPoint){
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());

        logger.info("\nDespues: " + method + " con los argumentos " + args);
    }

    @AfterReturning("greetingLoggerPointCut")
    public void loggerAfterReturning(JoinPoint joinPoint){
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());

        logger.info("\nDespues de retornar: " + method + " con los argumentos " + args);
    }

    @AfterThrowing("greetingLoggerPointCut")
    public void loggerAfterThrowing(JoinPoint joinPoint){
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());

        logger.info("\nDespues de lanzar la excepcion: " + method + " con los argumentos " + args);
    }

    @Around("greetingLoggerPointCut")
    public Object loggerAround(ProceedingJoinPoint joinPoint) throws Throwable{
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());

        Object result = null;

        try {
            // Antes de que se ejecute
            logger.info("\nEl metodo " + method + "() con los parametros " + args);
            result = joinPoint.proceed();
            // Despues de que se ejecute
            logger.info("\nEl metodo " + method + "() retorna el resultado: " + result);
            return result;
        } catch (Throwable e) {
            logger.error("\nError en la llamada del metodo " + method + "()");
            throw e;
        }
        // return result;
        
    }
}
