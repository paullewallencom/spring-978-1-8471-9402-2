package org.springaop.chapter.four;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.DeclareParents;
import org.springaop.chapter.four.domain.DefaultInfo;
import org.springaop.chapter.four.domain.InfoMBean;

@Aspect
public class IntroductionInformation {

    @DeclareParents(value = "org.springaop.app.*", defaultImpl = DefaultInfo.class)
    public static InfoMBean mixin;

    @Before("execution(* org.springaop.app.services.*.*(..)) && this(info)")
    public void manageClass(InfoMBean info) {
        if (info.isJmxEnabled()) {

        }
    }
}
