package com.test.util;

import com.test.activity.R;

import android.content.Context;

public class ApplicationContext {

	
	public static String getActionUrlByResId(Context context, int resId){
		if(null == context){
			return null;
		}
		
		String urlConnection = context.getResources().getString(R.string.urlConnection);
		String actionUrl = context.getString(resId);
		return urlConnection + actionUrl;
	}
}
