package org.springaop.chapter.two.advice;

import java.lang.reflect.Method;
import java.util.logging.Logger;

import org.springframework.aop.ThrowsAdvice;

public class RuntimeExceptionAdvice implements ThrowsAdvice {

    public void afterThrowing(Method m, Object[] args, Object target, RuntimeException ex) {

        logger.fine(traceExceptionContext(m, args, target, ex));
    }

    private String traceExceptionContext(Method m, Object[] args, Object target, RuntimeException ex) {
        StringBuilder sb = new StringBuilder("Exception on method :").append(m.getName());
        sb.append("with args :").append(args);
        sb.append("on object :").append(target);
        sb.append("exception msg :").append(ex.getMessage());
        return sb.toString();
    }

    private Logger logger;
}
