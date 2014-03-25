package com.test.util;

import com.google.gson.Gson;
import com.test.model.UserBean;

public class JSONUtil {
	static Gson gson = new Gson();

	public static UserBean getUserBean(String result){
		UserBean userBean = new UserBean();
		userBean = gson.fromJson(result, UserBean.class);
		return userBean;
	}
}
