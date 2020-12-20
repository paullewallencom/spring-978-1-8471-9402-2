package org.springaop.chapter.three.autoproxy.domain;

public class Seabird implements Animal, Bird {

	public Seabird() {

	}

	public Integer getNumberPaws() {
		return 2;
	}

	public Boolean hasTail() {
		return false;
	}

	public Boolean hasBeak() {
		return true;
	}

	public Boolean hasFeathers() {
		return true;
	}

	public boolean hasFur() {
		return false;
	}

	public Boolean hasHotBlood() {
		return false;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private String name;
}
