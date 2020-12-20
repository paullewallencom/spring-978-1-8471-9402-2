package org.springaop.chapter.two.introduction;

import org.springframework.aop.support.DefaultIntroductionAdvisor;

public class IsModifiableAdvisor extends DefaultIntroductionAdvisor {

    public IsModifiableAdvisor() {
        super(new IsModifiableMixin());
    }
}
