package org.springaop.chapter.two.pointcut;

import java.lang.reflect.Method;

import org.springframework.aop.Advisor;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.StaticMethodMatcher;

public class ComposablePointcutExample {

    public static void main(String[] args) {
        // create target
        ComposableTargetExample target = new ComposableTargetExample();

        ComposablePointcut pc = new ComposablePointcut(ClassFilter.TRUE, new GetterMethodMatcher());

        System.out.println("Test GetterMetodMatcher :");
        ComposableTargetExample proxy = getProxy(pc, target);
        testInvoke(proxy);

        System.out.println("Test GetterMetodMatcher UNION SetterMethodMatcher :");
        pc.union(new SetterMethodMatcher());
        proxy = getProxy(pc, target);
        testInvoke(proxy);

        System.out.println("Test (GetterMetodMatcher UNION SetterMethodMatcher) INTERSECT GetStartDateMethodMatcher :");
        pc.intersection(new GetStartDateMethodMatcher());
        proxy = getProxy(pc, target);
        testInvoke(proxy);
    }

    private static ComposableTargetExample getProxy(ComposablePointcut pc, ComposableTargetExample target) {

        Advisor advisor = new DefaultPointcutAdvisor(pc, new ComposableBeforeAdvice());

        ProxyFactory pf = new ProxyFactory();
        pf.setTarget(target);
        pf.addAdvisor(advisor);

        return (ComposableTargetExample) pf.getProxy();
    }

    private static void testInvoke(ComposableTargetExample proxy) {
        proxy.getStartDate();
        proxy.getDescription();
        proxy.setDescription("New Description");
    }

    private static class GetterMethodMatcher extends StaticMethodMatcher {

        public boolean matches(Method method, Class cls) {
            return (method.getName().startsWith("get"));
        }

    }

    private static class GetStartDateMethodMatcher extends StaticMethodMatcher {
        public boolean matches(Method method, Class cls) {
            return "getStartDate".equals(method.getName());
        }
    }

    private static class SetterMethodMatcher extends StaticMethodMatcher {

        public boolean matches(Method method, Class cls) {
            return (method.getName().startsWith("set"));
        }

    }
}
