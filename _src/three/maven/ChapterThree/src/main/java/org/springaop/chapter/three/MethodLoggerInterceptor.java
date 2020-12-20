package org.springaop.chapter.three;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;
import org.springaop.chapter.three.util.Constants;

public class MethodLoggerInterceptor implements MethodInterceptor {

	public Object invoke(MethodInvocation invocation) throws Throwable {

		Logger log = Logger.getLogger(Constants.LOG_NAME);
		StringBuilder sb = new StringBuilder();
		sb.append("MethodLoggerInterceptor");
		sb.append("\n Method:").append(invocation.getMethod()).append(
				"\n on class:").append(invocation.getClass()).append(
				"\n with arguments:").append(invocation.getArguments());
		log.info(sb.toString());
		Object retVal = invocation.proceed();
		log.info("\n return value:" + retVal);
		return retVal;
	}

}
