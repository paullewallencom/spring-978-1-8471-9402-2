package org.springaop.chapter.five.concurrent;

import java.util.Date;

public class BankAccount {

    public BankAccount(Integer id) {
        this.id = id;
        this.balance = new Float(0);
        this.startDate = new Date();
    }

    public BankAccount(Integer id, Float balance) {
        this.id = id;
        this.balance = balance;
        this.startDate = new Date();
    }

    public BankAccount(Integer id, Float balance, Date start) {
        this.id = id;
        this.balance = balance;
        this.startDate = start;
    }

    public boolean debitOperation(Float debit) {
        float balance = getBalance();
        if (balance < debit) {
            return false;
        } else {
            setBalance(balance - debit);
            return true;
        }
    }

    public void creditOperation(Float credit) {
        setBalance(getBalance() + credit);
    }

    private void setBalance(Float balance) {
        this.balance = balance;
    }

    public Float getBalance() {
        return balance;
    }

    public Integer getId() {
        return id;
    }

    public Date getStartDate() {
        return (Date) startDate.clone();
    }

    private Float balance;
    private final Integer id;
    private final Date startDate;

}
