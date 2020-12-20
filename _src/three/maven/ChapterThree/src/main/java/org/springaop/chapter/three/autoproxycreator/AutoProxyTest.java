package org.springaop.chapter.three.autoproxycreator;

import org.springaop.chapter.three.autoproxy.domain.Bird;
import org.springaop.chapter.three.autoproxy.domain.Cat;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AutoProxyTest {

	public static void main(String[] args) {

		String[] paths = { "org/springaop/chapter/three/autoproxy/applicationContext.xml" };
		ApplicationContext ctx = new ClassPathXmlApplicationContext(paths);

		Cat tiger = (Cat) ctx.getBean("tiger");
		StringBuilder sb = new StringBuilder("tiger.hasHotBlood():").append(
				tiger.hasHotBlood()).append("\n").append(
				"tiger.getNumberPaws():").append(tiger.getNumberPaws()).append(
				"\n");
		System.out.println(sb.toString());

		Bird albatross = (Bird) ctx.getBean("albatross");
		StringBuilder sbTwo = new StringBuilder("albatross.hasBeak():").append(
				albatross.hasBeak()).append("\n").append(
				"albatross.getNumberPaws():").append(albatross.getNumberPaws());
		System.out.println(sbTwo.toString());
	}
}
