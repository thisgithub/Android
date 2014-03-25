package com.test.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SharedPreferencesUtil {

	public static void saveUserInfo(Context context, String userId, String username, String password, String online, String createTime){
		SharedPreferences sharedPreferences = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
		Editor editor = sharedPreferences.edit();
		if(null != userId){
			editor.putString("userId", userId);
		}
		if(null != username){
			editor.putString("username", username);
		}
		if(null != password){
			editor.putString("password", password);
		}
		
		if(null != online){
			editor.putString("online", online);
		}
		if(null != createTime){
			editor.putString("createTime", createTime);
		}
		
		editor.commit();
	}
	
	
	public static void clearUserInfo(Context context){
		SharedPreferences sharedPreferences = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
		Editor editor = sharedPreferences.edit();
		editor.clear();
		editor.commit();
	}
}
