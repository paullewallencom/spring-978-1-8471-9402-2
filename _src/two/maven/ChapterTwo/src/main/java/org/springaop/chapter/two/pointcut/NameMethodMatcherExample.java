package org.springaop.chapter.two.pointcut;

import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;

public class NameMethodMatcherExample {

    public static void main(String[] args) {

        NameMethodTargetExample target = new NameMethodTargetExample();

        NameMatchMethodPointcut pc = new NameMatchMethodPointcut();
        pc.addMethodName("printSpot");
        pc.addMethodName("printAction");
        Advisor advisor = new DefaultPointcutAdvisor(pc, new AdviceExample());

        ProxyFactory pf = new ProxyFactory();
        pf.setTarget(target);
        pf.addAdvisor(advisor);
        NameMethodTargetExample proxy = (NameMethodTargetExample) pf.getProxy();

        proxy.printName();
        proxy.printAction();
        proxy.printSpot();
    }

}
