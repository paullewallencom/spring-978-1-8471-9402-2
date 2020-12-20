package org.springaop.chapter.one.schema.throwsadvice;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestException {

    public static void main(String[] args) {
        String[] paths = { "org/springaop/chapter/one/schema/throwsadvice/applicationContext.xml" };
        ApplicationContext ctx = new ClassPathXmlApplicationContext(paths);

        ExceptionTarget exceptiontarget = (ExceptionTarget) ctx.getBean("exceptionTarget");
        try {
            exceptiontarget.errorMethod();
        } catch (Exception ignored) {
        }
    }
}
