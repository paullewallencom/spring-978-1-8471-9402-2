package org.springaop.chapter.three.proxy;

public class CommandImpl implements Command {

	public void execute() {
		System.out.println(label);
	}

	private String label = "Goooo !";

}
