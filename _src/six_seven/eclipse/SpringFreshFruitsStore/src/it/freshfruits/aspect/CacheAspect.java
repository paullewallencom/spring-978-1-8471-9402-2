package it.freshfruits.aspect;

import it.freshfruits.util.Constants;
import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;

public class CacheAspect {

    public void flush() {
        cache.flush();
    }

    public Object invoke(ProceedingJoinPoint pjp) throws Throwable {

        Object result;
        String cacheKey = getCacheKey(pjp);

        Element element = (Element) cache.get(cacheKey);
        if (log.isInfoEnabled()) {
            log.info(new StringBuilder("CacheAspect invoke:").append("\n get:").append(cacheKey).append(" value:").append(element).toString());
        }

        if (element == null) {

            result = pjp.proceed();

            element = new Element(cacheKey, result);
            cache.put(element);
            if (log.isInfoEnabled()) {
                log.info(new StringBuilder("\n put:").append(cacheKey).append(" value:").append(result).toString());
            }

        }
        return element.getValue();
    }

    private String getCacheKey(ProceedingJoinPoint pjp) {

        String targetName = pjp.getTarget().getClass().getSimpleName();
        String methodName = pjp.getSignature().getName();
        Object[] arguments = pjp.getArgs();

        StringBuilder sb = new StringBuilder();
        sb.append(targetName).append(".").append(methodName);
        if ((arguments != null) && (arguments.length != 0)) {
            for (int i = 0; i < arguments.length; i++) {
                sb.append(".").append(arguments[i]);
            }
        }
        return sb.toString();
    }

    public void setCache(Cache cache) {
        this.cache = cache;
    }

    private Cache cache;
    private Logger log = Logger.getLogger(Constants.LOG_NAME);

}
