package com.test.android.push.action;

import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.test.android.push.pojo.ApnUser;
import com.test.android.push.service.AndroidService;

@Namespace("/manage")
@ParentPackage("manage")
public class UserAction extends BaseAction {
	private AndroidService androidService;
	private String username;
	private String password;
	
//	@Action(value="apnlist",results = { @Result(name = "success", location = "/android/user/list.jsp")})
	@Action(value = "login")
	public String login(){
		log.debug("--- login() start ---");
		log.debug("username = " + username);
		log.debug("password = " + password);
		if(StringUtils.isBlank(username)){
			log.warn("用户登录时, 传入参数username为空");
			sendAjaxResponse("0");
			return null;
		} 
		if(StringUtils.isBlank(password)){
			log.warn("用户登录时, 传入参数password为空");
			sendAjaxResponse("0");
			return null;
		}
		
		try {
			username = URLDecoder.decode(username, "utf-8").trim();
			password = URLDecoder.decode(password, "utf-8");
			ApnUser user = androidService.getApnUser(username, password);
			if(null == user){
				log.warn("用户名 " + username + " 不存在,登录失败!");
				sendAjaxResponse("4");
				return null;
			} else {
				Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
				resultMap.put("userId", user.getId());
				resultMap.put("username", user.getUsername());
				resultMap.put("password", user.getPassword());
				resultMap.put("online", user.getOnline());
				sendAjaxResponse(resultMap);
				log.info("用户 " + username + " 登录成功!");
			}
		} catch (Exception e) {
			log.error("出现未知异常,用户 " + username + " 登录失败!");
			sendAjaxResponse("3");
		}
		log.debug("--- login() end ---");
		return null;
	}

	public AndroidService getAndroidService() {
		return androidService;
	}

	public void setAndroidService(AndroidService androidService) {
		this.androidService = androidService;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
