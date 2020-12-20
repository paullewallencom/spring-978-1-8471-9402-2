package org.springaop.chapter.four.schema.introduction;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
/* run with jvm argument: -javaagent:<path>/spring-agent.jar
 * in conjunction with:
 * <include within="org.springaop.chapter.four.introduction.*"/> in aop.xml
 * */
public class Test {

    public static void main(String[] args) {
        String[] paths = { "org/springaop/chapter/four/schema/introduction/applicationContext.xml" };
        ApplicationContext ctx = new ClassPathXmlApplicationContext(paths);

        GeometricForm cube = (GeometricForm) ctx.getBean("cube");
        cube.getShape();
        cube.getShape();
        cube.getShape();
        CounterTracker counterCube = (CounterTracker) cube;
        System.out.println(counterCube.getCounter());
        Matter titanium = (Matter) ctx.getBean("titanium");
        titanium.getType();
        titanium.getType();
        CounterTracker counterTitanium = (CounterTracker) titanium;
        System.out.println(counterTitanium.getCounter());
    }
}
