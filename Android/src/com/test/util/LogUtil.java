package com.test.util;

import android.util.Log;

/**
 * 日志处理类
 * @author admin
 *
 */
public class LogUtil {
	private static String tag = "MY_TEST_ANDROID";
	
	public static void debug(String msg){
		Log.d(tag, appendMsgAndInfo(msg, getCurrentInfo()));
	}
	
	public static void error(String msg, Throwable tr){
		Log.e(tag, appendMsgAndInfo(msg, getCurrentInfo()), tr);
	}
	
	public static void info(String msg) {
		Log.i(msg, appendMsgAndInfo(msg, getCurrentInfo()));
	}
	
	private static String getCurrentInfo(){
		StackTraceElement[] eles = Thread.currentThread().getStackTrace();
		StackTraceElement targetEle = eles[5];
		String info = "(" + targetEle.getClassName() + "." + targetEle.getMethodName() + ":" + targetEle.getLineNumber() + ")";
		return info;
	}
	
	private static String appendMsgAndInfo(String msg, String info){
		return msg + " " + getCurrentInfo();
	}
	
	public static String getTag(){
		return tag;
	}
	
	public static void setTag(String tag){
		LogUtil.tag = tag;
	}
	
}
