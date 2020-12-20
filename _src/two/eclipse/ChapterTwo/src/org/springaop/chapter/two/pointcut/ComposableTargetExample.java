package org.springaop.chapter.two.pointcut;

import java.util.Date;

public class ComposableTargetExample {

    public void setDescription(String description) {
        this.description = description;
    }

    public ComposableTargetExample() {
        startDate = new Date();
    }

    public ComposableTargetExample(String description) {
        startDate = new Date();
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public Date getStartDate() {
        return (Date) startDate.clone();
    }

    private Date startDate;
    private String description;
}
