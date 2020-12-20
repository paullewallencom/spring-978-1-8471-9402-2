package org.springaop.chapter.three.autoproxy;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;
import org.springaop.chapter.three.util.Constants;

public class AnimalAdvice implements MethodInterceptor {

	public Object invoke(MethodInvocation invocation) throws Throwable {

		Logger log = Logger.getLogger(Constants.LOG_NAME);
		StringBuilder sb = new StringBuilder();
		sb.append("Target Class:").append(invocation.getThis()).append("\n")
				.append(invocation.getMethod()).append("\n");

		Object retVal = invocation.proceed();

		sb.append(" return value:").append(retVal).append("\n");
		log.info(sb.toString());
		return retVal;
	}
}
