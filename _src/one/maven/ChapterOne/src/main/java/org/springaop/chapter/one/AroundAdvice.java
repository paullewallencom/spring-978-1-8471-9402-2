package org.springaop.chapter.one;

import org.springaop.target.Hello;
import org.springframework.aop.framework.ProxyFactory;

public class AroundAdvice {

    public static void main(String[] args) {

        // target class
        Hello target = new Hello();

        // create the proxy
        ProxyFactory pf = new ProxyFactory();

        // add advice
        pf.addAdvice(new MethodDecorator());

        // setTarget
        pf.setTarget(target);

        Hello proxy = (Hello) pf.getProxy();
        proxy.greeting();
    }
}
