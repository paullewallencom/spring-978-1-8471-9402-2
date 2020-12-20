package org.springaop.chapter.two.advice;

import java.lang.reflect.Method;
import java.util.logging.Logger;

import org.springframework.aop.AfterReturningAdvice;

public class AfterAdvice implements AfterReturningAdvice {

    public void afterReturning(Object returnValue, Method m, Object[] args, Object target) {

        logger.fine(traceResultInvocation(m, args, target, returnValue));
    }

    private String traceResultInvocation(Method m, Object[] args, Object target, Object returnValue) {
        StringBuilder sb = new StringBuilder("Invoked method :").append(m.getName());
        sb.append("with args :").append(args);
        sb.append("on object :").append(target);
        sb.append("return value :").append(returnValue);
        return sb.toString();
    }

    private Logger logger;
}
