package org.springaop.chapter.four.schema;

public class ExampleBeanImpl implements ExampleBean {

    public String foo() {
        return "Foo method";
    }

    public String fooTwo(String msg) {
        return "Another Foo method:" + msg;
    }

    public String fooThree() {
        return "Another Foo method three:";
    }

    public void greeting() {
        System.out.println("writer");
    }

}
