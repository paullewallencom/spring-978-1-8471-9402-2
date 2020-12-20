package org.springaop.chapter.four.schema.afterthrowing;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

    public static void main(String[] args) {
        String[] paths = { "org/springaop/chapter/four/schema/afterthrowing/applicationContext.xml" };
        ApplicationContext ctx = new ClassPathXmlApplicationContext(paths);

        ExceptionBean bean = (ExceptionBean) ctx.getBean("exceptionBean");
        try {
            bean.otherErrorMethod();
        } catch (NullPointerException e) {

        }

    }
}
