package org.springaop.chapter.four.schema;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springaop.chapter.four.util.Constants;

public class MyAspect {

    public void trace(JoinPoint jp) {
        if (log.isInfoEnabled()) {
            StringBuilder sb = new StringBuilder();
            sb.append("Join point information:");
            sb.append("\nKind : ").append(jp.getKind()).append("\nSignature declaring type : ").append(jp.getSignature().getDeclaringTypeName()).append("\nSignature name : ").append(
                    jp.getSignature().getName()).append("\nArguments : ").append(Arrays.toString(jp.getArgs())).append("\nTarget : ").append(jp.getTarget().getClass().getName()).append(
                    "\nThis class : ").append(jp.getThis().getClass().getName());
            log.info(sb.toString());
        }
    }

    public Object around(ProceedingJoinPoint jp) throws Throwable {

        StringBuilder sb = new StringBuilder();
        sb.append("Join point information:");
        sb.append("\nKind : ").append(jp.getKind()).append("\nSignature declaring type : ").append(jp.getSignature().getDeclaringTypeName()).append("\nSignature name : ").append(
                jp.getSignature().getName()).append("\nArguments : ").append(Arrays.toString(jp.getArgs())).append("\nTarget : ").append(jp.getTarget().getClass().getName()).append("\nThis class : ")
                .append(jp.getThis().getClass().getName());
        log.info(sb.toString());
        Object result = jp.proceed();
        log.info(result);
        return result;
    }

    public void methodFinally() {
        log.info("finally");
    }

    public void afterReturning(Object ret) {
        log.info("return value from method :" + ret);
    }

    public void fooRecoveryActions() {
        log.info("recovery");
    }

    public void logInvocation(JoinPoint jp) {
        System.out.println(jp);
    }

    public void afterGreeting() {

        System.out.println("this is afterAspect !");
    }

    private Logger log = Logger.getLogger(Constants.LOG_NAME);
}
