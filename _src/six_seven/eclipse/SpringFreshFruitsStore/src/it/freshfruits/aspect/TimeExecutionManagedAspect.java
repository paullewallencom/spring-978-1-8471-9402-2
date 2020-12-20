package it.freshfruits.aspect;

import it.freshfruits.util.Constants;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.util.StopWatch;

@ManagedResource("freshfruitstore:type=TimeExecutionManagedAspect")
@Aspect()
@Order(2)
public class TimeExecutionManagedAspect {

    @ManagedAttribute
    public boolean isLogEnabled() {
        return isLogEnabled;
    }

    @ManagedAttribute
    public void setLogEnabled(boolean isLogEnabled) {
        this.isLogEnabled = isLogEnabled;
    }

    @ManagedAttribute
    public boolean isTimeExecutionEnabled() {
        return isTimeExecutionEnabled;
    }

    @ManagedAttribute
    public void setTimeExecutionEnabled(boolean isTimeExecutionEnabled) {
        this.isTimeExecutionEnabled = isTimeExecutionEnabled;
    }

    @ManagedAttribute
    public long getAverageCallTime() {
        return (this.callCount > 0 ? this.accumulatedCallTime / this.callCount : 0);
    }

    @ManagedOperation
    public void resetCounters() {
        this.callCount = 0;
        this.accumulatedCallTime = 0;
    }

    @Around("within(it.freshfruits.domain.entity.CustomerImpl)")
    public Object invoke(ProceedingJoinPoint joinPoint) throws Throwable {

        if (this.isTimeExecutionEnabled) {
            StopWatch sw = new StopWatch(joinPoint.toString());

            sw.start("invoke");
            try {
                return joinPoint.proceed();
            } finally {
                sw.stop();
                synchronized (this) {
                    this.accumulatedCallTime += sw.getTotalTimeMillis();
                }
                if (isLogEnabled) {
                    logger.info(sw.prettyPrint());
                }
            }
        } else {
            return joinPoint.proceed();
        }
    }

    private boolean isTimeExecutionEnabled = false;
    private boolean isLogEnabled = false;
    private long accumulatedCallTime = 0;
    private int callCount = 0;
    private Logger logger = Logger.getLogger(Constants.LOG_NAME);
}
