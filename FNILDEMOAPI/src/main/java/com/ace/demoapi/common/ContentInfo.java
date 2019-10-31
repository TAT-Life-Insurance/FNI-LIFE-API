package com.ace.demoapi.common;

public class ContentInfo {
	private String phone;
	private String fax;
	private String mobile;
	private String email;

	public ContentInfo() {
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneOrMoblieNo() {
		if (phone == null && mobile == null) {
			return "-";
		} else {
			if (phone.isEmpty() || phone == "") {
				if (mobile != null && !mobile.isEmpty()) {
					return mobile;
				}
				return "-";
			} else
				return phone;
		}
	}

}
