package com.ace.demoapi.common;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class UserRecorder {
	private String createdUserId;
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	private String updatedUserId;
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedDate;

}
