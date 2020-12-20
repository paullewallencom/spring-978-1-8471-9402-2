package org.springaop.chapter.two.advice;

import java.lang.reflect.Method;
import java.util.logging.Logger;

import org.springframework.aop.MethodBeforeAdvice;

public class BeforeAdvice implements MethodBeforeAdvice {

    public void before(Method m, Object[] args, Object target) {
        logger.fine(traceInvocation(m, args, target));
        // do something
    }

    private String traceInvocation(Method m, Object[] args, Object target) {
        StringBuilder sb = new StringBuilder("Invoked method :").append(m.getName());
        sb.append("with args :").append(args);
        sb.append("on object :").append(target);
        return sb.toString();
    }

    private Logger logger;
}
