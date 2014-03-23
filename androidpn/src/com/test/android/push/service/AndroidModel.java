package com.test.android.push.service;

import java.io.Serializable;

import com.test.android.push.pojo.ApnUser;

public class AndroidModel implements Serializable {

	private static final long serialVersionUID = -5407059229913151916L;
	
	private ApnUser apnUser;

	
	
	public ApnUser getApnUser() {
		return apnUser;
	}

	public void setApnUser(ApnUser apnUser) {
		this.apnUser = apnUser;
	}
}
