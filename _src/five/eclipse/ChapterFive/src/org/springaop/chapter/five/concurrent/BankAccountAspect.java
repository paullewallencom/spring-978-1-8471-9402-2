package org.springaop.chapter.five.concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class BankAccountAspect {

    /* pointcuts */
    @Pointcut("execution(* org.springaop.chapter.five.concurrent.BankAccount.getBalance())")
    public void safeRead() {
    }

    @Pointcut("execution(* org.springaop.chapter.five.concurrent.BankAccount.set*(*))")
    public void stateModification() {
    }

    @Pointcut("execution(* org.springaop.chapter.five.concurrent.BankAccount.getId())")
    public void getId() {
    }

    @Pointcut("execution(* org.springaop.chapter.five.concurrent.BankAccount.getStartDate())")
    public void getStartDate() {
    }

    /* advices */
    @Before("safeRead()")
    public void beforeSafeRead() {
        rLock.lock();
    }

    @After("safeRead()")
    public void afterSafeRead() {
        rLock.unlock();
    }

    @Before("stateModification()")
    public void beforeSafeWrite() {
        wLock.lock();
    }

    @After("stateModification()")
    public void afterSafeWrite() {
        wLock.unlock();
    }

    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock rLock = lock.readLock();
    private final Lock wLock = lock.writeLock();
}
