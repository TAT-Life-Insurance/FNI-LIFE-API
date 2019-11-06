package com.ace.demoapi.common;


public enum RiskyOccupation {

	YES("Yes"), NO("No");

	private String label;

	private RiskyOccupation(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

}
