package org.springaop.chapter.four.introduction;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/*run with jvm argument: -javaagent:<path>/spring-agent.jar*/
public class TestIntroduction {

    public static void main(String[] args) {
        String[] paths = { "org/springaop/chapter/four/introduction/applicationContext.xml" };
        ApplicationContext ctx = new ClassPathXmlApplicationContext(paths);

        Box bean = (Box) ctx.getBean("box");
        System.out.println(bean.getName());

        Matter beanMatter = (Matter) bean;
        System.out.println(beanMatter.getType());

        GeometricForm geoMetricBean = (GeometricForm) bean;
        System.out.println(geoMetricBean.getShape());
    }
}
