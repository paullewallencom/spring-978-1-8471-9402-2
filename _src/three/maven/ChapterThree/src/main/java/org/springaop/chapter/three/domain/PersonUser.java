package org.springaop.chapter.three.domain;

public class PersonUser {

	public String getName() {
		return user.getName();
	}

	public String getSurname() {
		return user.getSurname();
	}

	public void setUser(User user) {
		this.user = user;
	}

	private User user;

}
