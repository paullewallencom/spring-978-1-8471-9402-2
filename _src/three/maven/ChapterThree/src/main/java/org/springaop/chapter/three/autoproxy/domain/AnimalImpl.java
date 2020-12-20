package org.springaop.chapter.three.autoproxy.domain;

public class AnimalImpl implements Animal {

	public boolean hasFur() {
		return true;
	}

	public Integer getNumberPaws() {
		return paws;
	}

	public Boolean hasTail() {
		return true;
	}

	public Boolean hasHotBlood() {
		return true;
	}

	public void setPaws(Integer paws) {
		this.paws = paws;
	}

	private Integer paws;
}
