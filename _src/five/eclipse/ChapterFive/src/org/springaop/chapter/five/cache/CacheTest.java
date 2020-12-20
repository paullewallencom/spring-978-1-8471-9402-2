package org.springaop.chapter.five.cache;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CacheTest {

    public static void main(String[] args) {
        String[] paths = { "org/springaop/chapter/five/cache/applicationContext.xml" };
        ApplicationContext ctx = new ClassPathXmlApplicationContext(paths);

        DummyClass dummy = (DummyClass) ctx.getBean("dummy");

        dummy.getFooFighters();
        dummy.getHives("2004");
        dummy.getDandyWarhols();

        dummy.getFooFighters();
        dummy.getHives("2004");
        dummy.getDandyWarhols();

        dummy.exit();
    }
}
