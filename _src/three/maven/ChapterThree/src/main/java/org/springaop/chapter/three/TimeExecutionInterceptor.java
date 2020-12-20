package org.springaop.chapter.three;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;

import org.springaop.chapter.three.util.Constants;
import org.springframework.util.StopWatch;

public class TimeExecutionInterceptor implements MethodInterceptor {

	public Object invoke(MethodInvocation invocation) throws Throwable {
		long timeBeforeMethodExecution = 0;
		long timeAfterMethodExecution = 0;
		Logger log = Logger.getLogger(Constants.LOG_NAME);
		// Spring util class
		StopWatch sw = new StopWatch();
		log.info(new StringBuilder("\n Time before execution:")
				.append(timeBeforeMethodExecution));
		sw.start(invocation.getMethod().getName());

		Object returnValue = invocation.proceed();

		sw.stop();
		timeAfterMethodExecution = System.currentTimeMillis();
		log.info(new StringBuilder("\n Time after execution:").append(
				(timeAfterMethodExecution - timeBeforeMethodExecution)).append(
				" ms").append("\n result:").append(returnValue));
		dumpInfo(invocation, sw.getTotalTimeMillis(), log);
		return returnValue;
	}

	private void dumpInfo(MethodInvocation invocation, long ms, Logger log) {

		Method m = invocation.getMethod();
		Object[] args = invocation.getArguments();

		log.info(new StringBuilder("\n Method :").append(m.getName()).append(
				"\n On object type: :").append(
				invocation.getThis().getClass().getName()).append(
				(" \n With arguments : \n")));

		for (int x = 0; x < args.length; x++) {
			log.info(new StringBuilder("    > ").append(args[x]));
		}

		log.info(new StringBuilder(" \n Time of execution: ").append(ms)
				.append(" ms"));
	}

}
