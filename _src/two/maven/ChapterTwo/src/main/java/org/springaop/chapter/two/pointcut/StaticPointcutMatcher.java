package org.springaop.chapter.two.pointcut;

import java.lang.reflect.Method;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.StaticMethodMatcherPointcut;

public class StaticPointcutMatcher extends StaticMethodMatcherPointcut {

    public boolean matches(Method method, Class cls) {
        return ("printSpot".equals(method.getName()));
    }

    public ClassFilter getClassFilter() {
        return new ClassFilter() {
            public boolean matches(Class cls) {
                return (cls == PointcutTargetExample.class);
            }
        };

    }
}
