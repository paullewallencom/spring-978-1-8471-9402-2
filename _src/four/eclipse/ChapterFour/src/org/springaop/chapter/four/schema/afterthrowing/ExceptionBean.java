package org.springaop.chapter.four.schema.afterthrowing;

public class ExceptionBean {

    public void errorMethod() throws Exception {
        System.out.println("now throw a Exception");
        throw new Exception("Fake exception");
    }

    public void otherErrorMethod() throws NullPointerException {
        System.out.println("now throw a NullPointerException");
        throw new NullPointerException("Other Fake exception");
    }
}
