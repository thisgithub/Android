package com.test.android.push.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.test.android.push.dao.ApnUserDao;
import com.test.android.push.pojo.ApnUser;

public class AndroidServiceImpl implements AndroidService {
	
	private ApnUserDao apnuserDao;

	public ApnUserDao getApnuserDao() {
		return apnuserDao;
	}

	public void setApnuserDao(ApnUserDao apnuserDao) {
		this.apnuserDao = apnuserDao;
	}
	public ApnUser getAndroidPns(Long id) throws KapuException {
		ApnUser apnuser = apnuserDao.getApnuserById(id);
		return apnuser;
	}
	@SuppressWarnings("unchecked")
	public List<ApnUser> getAndroidPnsList() throws KapuException {
		List<ApnUser> list = apnuserDao.getApnUserList();
		return list;
	}

	@Override
	public void updateAndroidPns(ApnUser apnuser) throws KapuException {
		apnuserDao.updateApnUser(apnuser);
	}

	@Override
	public ApnUser getApnUser(String username, String password)
			throws KapuException {
		Map<String, String> param = new HashMap<String, String>();
		param.put("username", username);
		param.put("password", password);
		ApnUser user = apnuserDao.getApnUser(param);
		return user;
	}

}
