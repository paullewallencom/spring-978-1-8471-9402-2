package org.springaop.chapter.three;

import org.springaop.chapter.three.domain.Command;
import org.springaop.chapter.three.domain.PersonUser;
import org.springaop.chapter.three.domain.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ProxyFactoryBeanTest {

	public static void main(String[] args) {

		String[] paths = { "org/springaop/chapter/three/applicationContext.xml" };
		ApplicationContext ctx = new ClassPathXmlApplicationContext(paths);

		User user = (User) ctx.getBean("user");
		user.getName();

		Command command = (Command) ctx.getBean("command");
		command.execute();

		PersonUser person = (PersonUser) ctx.getBean("personUser");
		person.getName();
	}
}
