package org.springaop.chapter.two.pointcut;

import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;

public class RegExpMethodMatcherExample {

    public static void main(String[] args) {
        RegExpTargetExample target = new RegExpTargetExample();

        JdkRegexpMethodPointcut pc = new JdkRegexpMethodPointcut();
        String[] patterns = { ".*Spot.*", ".*Action.*" };
        pc.setPatterns(patterns);
        Advisor advisor = new DefaultPointcutAdvisor(pc, new AdviceExample());

        ProxyFactory pf = new ProxyFactory();
        pf.setTarget(target);
        pf.addAdvisor(advisor);
        RegExpTargetExample proxy = (RegExpTargetExample) pf.getProxy();

        proxy.printName();
        proxy.printAction();
        proxy.printSpot();
    }

}
