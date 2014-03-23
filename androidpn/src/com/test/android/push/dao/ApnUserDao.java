package com.test.android.push.dao;

import java.util.List;

import com.test.android.push.pojo.ApnUser;

public interface ApnUserDao {
	
	public List<ApnUser> getApnUserList();
	
	public ApnUser getApnuserById(Long id);
	
	public void updateApnUser(ApnUser apnuser);

}
