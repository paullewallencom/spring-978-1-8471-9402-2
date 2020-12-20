package org.springaop.chapter.three.proxy;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;

@Aspect
public class BeforeAspectJProxyExample {

	@Pointcut("execution(* org.springaop.chapter.three.proxy.CommandImpl.execute(..))")
	void beforeExecute() {
	}

	@Before("beforeExecute()")
	public void before() {
		System.out.println("I'm a AspectJ proxied invocation");

	}

	public static void main(String[] args) {

		Command target = new CommandImpl();
		AspectJProxyFactory factory = new AspectJProxyFactory(target);
		factory.addAspect(BeforeAspectJProxyExample.class);
		Command proxy = factory.getProxy();
		proxy.execute();
	}
}
