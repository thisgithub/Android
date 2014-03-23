package com.test.android.push.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.test.android.push.pojo.ApnUser;
import com.test.android.push.service.AndroidService;
import com.test.android.push.service.KapuException;

@Namespace("/manage")
@ParentPackage("manage")
public class ApnUserAction extends BaseAction{

	private static final long serialVersionUID = 8238525957408661195L;
	private AndroidService androidService;
	private List<ApnUser> userList;
	
	/**
	 *     
	 * @return
	 */
	@Action(value="apnlist",results = { @Result(name = "success", location = "/android/user/list.jsp")})
	public String list() {
		try {
			userList = androidService.getAndroidPnsList();
		} catch (KapuException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public void setUserList(List<ApnUser> userList) {
		this.userList = userList;
	}

	public List<ApnUser> getUserList() {
		return userList;
	}


	public void setAndroidService(AndroidService androidService) {
		this.androidService = androidService;
	}
}
