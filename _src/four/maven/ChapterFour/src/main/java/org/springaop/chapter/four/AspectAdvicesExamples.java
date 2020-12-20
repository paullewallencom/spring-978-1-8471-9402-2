package org.springaop.chapter.four;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.DeclareParents;
import org.springaop.chapter.four.domain.DefaultInfo;
import org.springaop.chapter.four.domain.Info;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataAccessException;

@Order(1)
@Aspect
public class AspectAdvicesExamples {

    /* Before */

    @Before("execution(* org.springaop.app.services.*.*(..))")
    public void controlAccessCheck() {

    }

    /* AfterReturning */
    @AfterReturning("execution(* org.springaop.service.*.*(..))")
    public void logOperationCommited() {

    }

    @AfterReturning(pointcut = "execution(* org.springaop.service.*.*(..))", returning = "returningValue")
    public void logOperationPerformed(Object returningValue) {

    }

    /* AfterThrowing */
    @AfterThrowing("execution(* org.springaop.app.dao.*.*(..))")
    public void doRecoveryActionsOnDataAccessException() {

    }

    @AfterThrowing(pointcut = "execution(* org.springaop.app.dao.*.*(..))", throwing = "ex")
    public void doRecoveryActionsOnDataAccessException(DataAccessException ex) {

    }

    @After("execution (* org.springaop.app.dao.*.*(..))")
    public void releaseResource() {

    }

    /* Around */

    @Around("execution(* startMatch(..))")
    public Object doTimeProfiling(ProceedingJoinPoint pjp) throws Throwable {
        // start stopwatch
        Object retVal = pjp.proceed();
        // stop stopwatch
        return retVal;
    }

    /* Introduction */
    @DeclareParents(value = "org.springaop.app.*+", defaultImpl = DefaultInfo.class)
    public static Info mixin;

    @Before("execution(* org.springaop.app.services.*.*(..)) && this(info))")
    public void manageClass(Info info) {
        if (info.isJmxEnabled()) {

        }
    }

}
