package org.springaop.chapter.three.domain;

public class CommandImpl implements Command {

	public Object execute() {
		for (int x = 0; x < 1000; x++) {
			action();
		}
		return null;
	}

	private void action() {
		System.out.println("\n .");
	}

}
