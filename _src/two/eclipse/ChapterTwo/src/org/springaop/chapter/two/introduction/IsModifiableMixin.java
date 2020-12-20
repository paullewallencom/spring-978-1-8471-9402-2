package org.springaop.chapter.two.introduction;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;

public class IsModifiableMixin extends DelegatingIntroductionInterceptor implements IsModifiable {

    public boolean isModifiable() {
        return isModifiable;
    }

    public Object invoke(MethodInvocation invocation) throws Throwable {

        if (isModifiable) {

            if ((invocation.getMethod().getName().startsWith("set")) && (invocation.getArguments().length == 1)) {

                Method getter = getGetter(invocation.getMethod());

                if (getter != null) {
                    Object value = getter.invoke(invocation.getThis(), null);
                    if (value == null) {
                        isModifiable = true;
                    } else {
                        isModifiable = false;
                    }
                } else {
                    isModifiable = true;
                }
            }
        }
        return super.invoke(invocation);
    }

    private Method getGetter(Method setter) {
        Method getter = null;

        getter = (Method) setterMethodCache.get(setter);

        if (getter != null) {
            return getter;
        }

        String getterName = setter.getName().replaceFirst("set", "get");
        try {
            getter = setter.getDeclaringClass().getMethod(getterName, null);

            synchronized (setterMethodCache) {
                setterMethodCache.put(setter, getter);
            }

            return getter;
        } catch (NoSuchMethodException ex) {
            return null;
        }
    }

    private boolean isModifiable = true;
    private Map setterMethodCache = new HashMap();
}
