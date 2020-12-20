package org.springaop.target;

public class Hello {

    public void greeting() {
        System.out.println(label);
    }

    private String label = "reader";

    public void setLabel(String label) {
        this.label = label;
    }
}
