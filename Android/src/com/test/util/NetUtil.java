package com.test.util;

import android.content.Context;
import android.net.ConnectivityManager;

public class NetUtil {
	
	/**
	 * 判断网络是否可用
	 * 
	 * @return
	 */
	public static boolean checkNetWork(Context context){
		boolean netWorkFlag = false;
		ConnectivityManager connMan = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
		if(connMan.getActiveNetworkInfo() != null){
			netWorkFlag = connMan.getActiveNetworkInfo().isAvailable();
		}
		
		return netWorkFlag;
	}

}
