package com.test.android.push.dao;

import java.util.List;
import java.util.Map;

import com.test.android.push.pojo.ApnUser;

public interface ApnUserDao {
	
	public List<ApnUser> getApnUserList();
	
	public ApnUser getApnuserById(Long id);
	
	public void updateApnUser(ApnUser apnuser);
	
	public ApnUser getApnUser(Map<String, String> param);

}
