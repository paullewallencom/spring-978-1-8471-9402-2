package it.freshfruits.aspect;

import it.freshfruits.util.Constants;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;

@ManagedResource("freshfruitstore:type=LogManagedAspect")
@Aspect()
@Order(3)
public class LogManagedAspect {

    @ManagedAttribute
    public boolean isLogEnabled() {
        return isLogEnabled;
    }

    @ManagedAttribute
    public void setLogEnabled(boolean isLogEnabled) {
        this.isLogEnabled = isLogEnabled;
    }

    @Around("within(it.freshfruits.domain.entity.CustomerImpl )")
    public Object invoke(ProceedingJoinPoint joinPoint) throws Throwable {

        if (isLogEnabled) {
            logger.info("LogManagedAspect :"+joinPoint.toLongString());
            return joinPoint.proceed();
        }else{
            return joinPoint.proceed();
        }
    }

    private boolean isLogEnabled = true;
    private Logger logger = Logger.getLogger(Constants.LOG_NAME);
}
