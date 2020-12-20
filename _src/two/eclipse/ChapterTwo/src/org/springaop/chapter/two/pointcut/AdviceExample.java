package org.springaop.chapter.two.pointcut;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class AdviceExample implements MethodInterceptor {

    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("Invoking " + invocation.getMethod().getName());
        Object retVal = invocation.proceed();
        System.out.println("Job Done");
        return retVal;
    }
}
