package org.springaop.chapter.three.handler;

public class Test {

	public static void main(String[] args) {
		Info info = new DefaultInfo();

		Info information = (Info) FooInvocationHandler.createProxy(info);
		information.isJmxEnabled();

	}

}
