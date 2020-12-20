package org.springaop.chapter.five.security;

public interface FooService {

    public Integer getBalance(Integer idAccount);

    public void setBalanceAccount(Integer id, Integer balance);

    public boolean suspendAccount(Integer id);

}
