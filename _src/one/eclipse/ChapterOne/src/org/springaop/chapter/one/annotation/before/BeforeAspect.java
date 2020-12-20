package org.springaop.chapter.one.annotation.before;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class BeforeAspect {

    @Before("execution(* greeting(..))")
    public void beforeGreeting() {

        System.out.println("Good morning ");
    }
}
