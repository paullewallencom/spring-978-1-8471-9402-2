package org.springaop.chapter.one.schema.throwsadvice;

public class ExceptionTarget {

    public void errorMethod() throws Exception {
        throw new Exception("Fake exception");
    }

    public void otherErrorMethod() throws NullPointerException {
        throw new NullPointerException("Other Fake exception");
    }

}
