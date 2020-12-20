package org.springaop.chapter.four.configurable;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/* run with jvm argument: -javaagent:<path>/spring-agent.jar
 * in conjunction with:
 * <include within="org.springaop.chapter.four.configurable.*"/> in aop.xml
 * */
public class Test {

    public static void main(String[] args) {
        String[] paths = { "org/springaop/chapter/four/configurable/applicationContext.xml" };
        new ClassPathXmlApplicationContext(paths);

        User user = new User();

        System.out.println("user name:" + user.getName());
    }
}
