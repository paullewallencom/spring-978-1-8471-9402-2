package org.springaop.chapter.four.pointcut;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class FooAspect {

    @Before("org.springaop.chapter.four.pointcut.ApplicationPointcutsAspect.inResourceLayer()")
    public void beforeFooMethods() {
        // do something
    }

}