package com.test.android.push.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.test.android.push.dao.ApnUserDao;
import com.test.android.push.pojo.ApnUser;

public class ApnUserDaoImple extends SqlMapClientDaoSupport implements ApnUserDao{

	@Override
	public List<ApnUser> getApnUserList() {
		List<ApnUser> list = getSqlMapClientTemplate().queryForList("apnuser.getApnuserList");
		return list;
	}

	@Override
	public ApnUser getApnuserById(Long id) {
		ApnUser apnuser = (ApnUser)getSqlMapClientTemplate().queryForObject("apnuser.getApnuserByUserId", id);
		return apnuser;
	}

	@Override
	public void updateApnUser(ApnUser apnuser) {
		getSqlMapClientTemplate().update("apnuser.updateApnuser", apnuser);
	}

	@Override
	public ApnUser getApnUser(Map<String, String> param) {
		ApnUser user = (ApnUser)getSqlMapClientTemplate().queryForObject("apnuser.getApnuserByAppTypeUserId", param);
		return user;
	}
	
}
