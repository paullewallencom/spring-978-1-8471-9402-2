package org.springaop.chapter.two.pointcut;

public class DynamicPointcutTargetExample {

    public void setSpot(String spot) {
        this.spot = spot;
    }

    public void printSpot() {
        System.out.println(spot);
    }

    private String spot;
}
