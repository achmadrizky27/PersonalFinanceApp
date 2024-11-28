package com.management.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LoggingAspect {
    @Pointcut("execution(* com.management.services.FinanceService.addTransaction(..))")
    public void addTransactionPointcut() {}

    @After("addTransactionPointcut()")
    public void logAddTransaction() {
        System.out.println("Transaction added successfully!");
    }
}
