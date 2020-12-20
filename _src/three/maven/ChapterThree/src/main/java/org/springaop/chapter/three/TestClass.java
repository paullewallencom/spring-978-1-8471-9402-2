package org.springaop.chapter.three;

import org.springaop.chapter.three.domain.Command;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.Advised;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestClass {

	public static void main(String[] args) {

		String[] paths = { "org/springaop/chapter/three/applicationContext.xml" };
		ApplicationContext ctx = new ClassPathXmlApplicationContext(paths);

		Command command = (Command) ctx.getBean("command");

		Advisor consoleAdvisor = (Advisor) ctx.getBean("consoleAdvisor");

		Advised advised = (Advised) command;

		System.out.println("Is Frozen ? :" + advised.isFrozen());
		System.out.println("Advisors size:" + advised.getAdvisors().length);
		System.out.println("IndexOf consoleAdvisor :"
				+ advised.indexOf(consoleAdvisor));

	}
}
