package org.springaop.chapter.one.annotation.after.returning;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class AfterReturningAspect {

    @AfterReturning("execution(* greeting(..))")
    public void afterGreeting() {

        System.out.println("this is a aop !");
    }
}
