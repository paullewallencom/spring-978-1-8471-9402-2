package org.springaop.chapter.five.security;

import org.springframework.security.annotation.Secured;

public class FooServiceImplWithAnnotations implements FooService {

    @Secured("ROLE_USER")
    public Integer getBalance(Integer idAccount) {
        Integer result = 0;
        // do something
        return result;
    }

    @Secured( { "ROLE_ACCOUNTING", "ROLE_ADMIN" })
    public void setBalanceAccount(Integer id, Integer balance) {
        // do something
    }

    @Secured("ROLE_ADMIN")
    public boolean suspendAccount(Integer id) {
        boolean result = false;
        // do something
        return result;
    }
}
