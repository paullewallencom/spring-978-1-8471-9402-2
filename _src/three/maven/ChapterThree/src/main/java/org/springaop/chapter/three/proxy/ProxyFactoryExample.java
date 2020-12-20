package org.springaop.chapter.three.proxy;

import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;

public class ProxyFactoryExample {

	public static void main(String[] args) {
		// target class
		Command target = new CommandImpl();

		// create the proxy
		ProxyFactory pf = new ProxyFactory();
		// add interface
		pf.addInterface(Command.class);

		// add pointcut
		NameMatchMethodPointcut pc = new NameMatchMethodPointcut();
		pc.addMethodName("execute");

		// add advisor
		Advisor advisor = new DefaultPointcutAdvisor(pc,
				new BeforeAdviceProxyExample());
		pf.addAdvisor(advisor);

		// setTarget
		pf.setTarget(target);

		Command proxy = (Command) pf.getProxy();
		proxy.execute();
	}

}
