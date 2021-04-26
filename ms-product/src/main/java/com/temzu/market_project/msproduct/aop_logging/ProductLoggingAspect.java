package com.temzu.market_project.msproduct.aop_logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class ProductLoggingAspect {

    @Before("execution(public * com.temzu.market_project.msproduct.controllers.ProductController.*(..)))")
    public void beforeAnyMethodsInProductControllerWithDetails(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if (args.length > 0) {
            System.out.println("Arguments:");
            Arrays.stream(args).forEach(System.out::println);
        }
    }
}
