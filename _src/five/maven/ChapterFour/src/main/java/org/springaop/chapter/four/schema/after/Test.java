package org.springaop.chapter.four.schema.after;

import org.springaop.chapter.four.schema.ExampleBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

    public static void main(String[] args) {
        String[] paths = { "org/springaop/chapter/four/schema/after/applicationContext.xml" };
        ApplicationContext ctx = new ClassPathXmlApplicationContext(paths);

        ExampleBean bean = (ExampleBean) ctx.getBean("exampleBean");
        bean.greeting();
    }
}
