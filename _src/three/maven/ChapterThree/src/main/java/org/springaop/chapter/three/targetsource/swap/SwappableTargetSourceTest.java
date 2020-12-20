package org.springaop.chapter.three.targetsource.swap;

import org.springaop.chapter.three.autoproxy.domain.Animal;
import org.springframework.aop.target.HotSwappableTargetSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SwappableTargetSourceTest {

	public static void main(String[] args) {
		String[] paths = { "org/springaop/chapter/three/targetsource/swap/applicationContext.xml" };
		ApplicationContext ctx = new ClassPathXmlApplicationContext(paths);

		Animal target1 = (Animal) ctx.getBean("animal1");
		Animal target2 = (Animal) ctx.getBean("animal2");
		Animal target3 = (Animal) ctx.getBean("animal3");

		Animal proxied = (Animal) ctx.getBean("swappable");

		System.out.println(proxied.getNumberPaws());
		HotSwappableTargetSource swapper = (HotSwappableTargetSource) ctx
				.getBean("swapper");

		Object old = swapper.swap(target2);

		System.out.println(proxied.getNumberPaws());

		Object oldTwo = swapper.swap(target3);

		System.out.println(proxied.getNumberPaws());
	}
}
