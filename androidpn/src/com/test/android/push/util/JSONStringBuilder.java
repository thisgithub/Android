package com.test.android.push.util;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JSONStringBuilder {
	/**
	 * 从json对象中获得字符串
	 * @param list
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String getAjaxString(List list) {
		JSONArray ja = JSONArray.fromObject(list);
		return ja.toString();
	}
	/**
	 * 从Map对象中获得json字符�?
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String getAjaxString(Map map) {
		JSONObject jo = JSONObject.fromObject(map);
		return jo.toString();
	}
	
	public static String getAjaxString(Object obj) {
		JSONObject jo = JSONObject.fromObject(obj);
		return jo.toString();
	}
}
