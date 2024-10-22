package org.springaop.chapter.three.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class FooInvocationHandler implements InvocationHandler {

    public FooInvocationHandler(Object target) {
        this.target = target;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("remove before fly :-) ");

        Object result = method.invoke(target, args);

        System.out.println(result + "\n mmh it's not rocket science ");
        return result;
    }

    public static Object createProxy(Object target) {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new FooInvocationHandler(target));
    }

    private Object target;

}
