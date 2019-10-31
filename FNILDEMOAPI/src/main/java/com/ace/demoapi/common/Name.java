package com.ace.demoapi.common;

public class Name {
	private String firstName;
	private String middleName;
	private String lastName;

	public Name() {
	}

	public Name(Name name) {
		this.firstName = name.getFirstName();
		this.middleName = name.getMiddleName();
		this.lastName = name.getLastName();
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFullName() {
		String result = "";
		if (firstName != null && !firstName.isEmpty()) {
			result = result + " " + firstName;
		}
		if (middleName != null && !middleName.isEmpty()) {
			result = result + " " + middleName;
		}
		if (lastName != null && !lastName.isEmpty()) {
			result = result + " " + lastName;
		}
		return result;
	}

}
