package org.springaop.chapter.four.pointcut.annotation;

public class OnLineBanking {

    @SensitiveOperation
    public boolean addMoney(Integer amount) {
        // do something
        return true;
    }

    @SensitiveOperation()
    public boolean subtractMoney(Integer amount) {
        // do something
        return true;
    }

}
