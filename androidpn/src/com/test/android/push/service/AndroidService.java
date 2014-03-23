package com.test.android.push.service;

import java.util.List;

import com.test.android.push.pojo.ApnUser;

/**
 * android推送接口
 * @author 殷修武
 * @date 2012-3-16
 *
 */
public interface AndroidService {
	
	/**
	 * 更新缓存对应的推送信息
	 * @param apnUser
	 * @throws KapuException
	 */
	public void updateAndroidPns(ApnUser apnuser) throws KapuException;
	
	/**
	 * 获得缓存里推送信息列表中
	 * @return
	 * @throws KapuException
	 */
	public List<ApnUser> getAndroidPnsList() throws KapuException;
	
	/**
	 * 获得缓存里对应推送信息
	 * @param id
	 * @return
	 * @throws KapuException
	 */
	public ApnUser getAndroidPns(Long id) throws KapuException;
	
	public ApnUser getApnUser(String username, String password) throws KapuException;
	
}
