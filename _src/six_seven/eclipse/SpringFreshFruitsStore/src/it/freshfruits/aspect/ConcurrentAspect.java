package it.freshfruits.aspect;

import it.freshfruits.util.Constants;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

@SuppressWarnings("unused")
@Aspect() 
@Order(0)
public class ConcurrentAspect {

    @Pointcut("execution (* isAvailable(..))")
    private void isAvailable() {}

    @Pointcut("execution (* retainItem(..))")
    private void retainItem() {}

    @Pointcut("execution (* release(..))")
    private void release() {}

    @Pointcut("release() || retainItem()")
    private void releaseOrRetain() {}

    @Before("isAvailable()")
    public void setReadLock() {
        if (log.isInfoEnabled()) {
            log.info("setReadLock");
        }
        rLock.lock();
    }

    @After("isAvailable()")
    public void releaseReadLock() {
        rLock.unlock();
        if (log.isInfoEnabled()) {
            log.info("releaseReadLock");
        }
    }

    @Before("releaseOrRetain()")
    public void setWriteLock() {
        if (log.isInfoEnabled()) {
            log.info("setWriteLock");
        }
        wLock.lock();
    }

    @After("releaseOrRetain()")
    public void releaseWriteLock() {
        wLock.unlock();
        if (log.isInfoEnabled()) {
            log.info("releaseWriteLock");
        }
    }

    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock rLock = lock.readLock();
    private final Lock wLock = lock.writeLock();
    private Logger log = Logger.getLogger(Constants.LOG_NAME);;
}
