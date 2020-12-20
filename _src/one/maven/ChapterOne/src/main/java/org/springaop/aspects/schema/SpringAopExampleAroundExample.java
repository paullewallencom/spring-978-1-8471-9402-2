package org.springaop.aspects.schema;

import org.aspectj.lang.ProceedingJoinPoint;

public class SpringAopExampleAroundExample {

    public Object aroundGreeting(ProceedingJoinPoint pjp) throws Throwable {

        System.out.print("Hello ");
        try {
            return pjp.proceed();
        } finally {
            System.out.println("this is around aop !");
        }
    }
}
