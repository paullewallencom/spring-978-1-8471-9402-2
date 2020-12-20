package org.springaop.chapter.one;

import java.lang.reflect.Method;

import org.springaop.target.Hello;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;

public class BeforeAdvice implements MethodBeforeAdvice {

    public static void main(String[] args) {
        // target class
        Hello target = new Hello();

        // create the proxy
        ProxyFactory pf = new ProxyFactory();

        // add advice
        pf.addAdvice(new BeforeAdvice());

        // setTarget
        pf.setTarget(target);

        Hello proxy = (Hello) pf.getProxy();
        proxy.greeting();
    }

    public void before(Method method, Object[] args, Object target) throws Throwable {

        System.out.println("Good morning");
    }
}
