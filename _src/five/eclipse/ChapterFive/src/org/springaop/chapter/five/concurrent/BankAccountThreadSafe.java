package org.springaop.chapter.five.concurrent;

import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/*
 * Final threadsafe class
 * */
public final class BankAccountThreadSafe {

    public BankAccountThreadSafe(Integer id) {
        this.id = id;
        this.balance = new Float(0);
        this.startDate = new Date();
    }

    public BankAccountThreadSafe(Integer id, Float balance) {
        this.id = id;
        this.balance = balance;
        this.startDate = new Date();
    }

    public BankAccountThreadSafe(Integer id, Float balance, Date start) {
        this.id = id;
        this.balance = balance;
        this.startDate = start;
    }

    /*
     * Thread safe write business operation
     */
    public boolean debitOperation(Float debit) {
        wLock.lock();
        try {
            float balance = getBalance();
            if (balance < debit) {
                return false;
            } else {
                setBalance(balance - debit);
                return true;
            }
        } finally {
            wLock.unlock();
        }
    }

    /*
     * Thread safe write business operation
     */
    public void creditOperation(Float credit) {
        wLock.lock();
        try {
            setBalance(getBalance() + credit);
        } finally {
            wLock.unlock();
        }
    }

    /*
     * Thread safe write
     */
    private void setBalance(Float balance) {

        wLock.lock();
        try {
            this.balance = balance;
        } finally {
            wLock.unlock();
        }
    }

    /*
     * Thread safe read
     */
    public Float getBalance() {
        rLock.lock();
        try {
            return balance;
        } finally {
            rLock.unlock();
        }
    }

    public Integer getId() {
        return id;
    }

    // safe copy
    public Date getStartDate() {
        return (Date) startDate.clone();
    }

    private Float balance;
    private final Integer id;
    private final Date startDate;
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock rLock = lock.readLock();
    private final Lock wLock = lock.writeLock();
}
