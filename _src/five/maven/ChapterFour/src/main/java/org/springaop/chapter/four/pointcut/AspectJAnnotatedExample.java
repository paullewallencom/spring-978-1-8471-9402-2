package org.springaop.chapter.four.pointcut;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class AspectJAnnotatedExample {

    @Before("execution(* insert(..))")
    public void fooMethod() {
        // do something
    }

}
