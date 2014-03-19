package com.test.receiver;

import com.test.util.LogUtil;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		LogUtil.debug("network state changed");
		if(!isNetworkAvailable(context)){
			Toast.makeText(context, "network disconnected!", 0).show();
		}
	}
	
	public static boolean isNetworkAvailable(Context context){
		ConnectivityManager mgr = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo[] info = mgr.getAllNetworkInfo();
		if(info != null){
			for(int i = 0; i < info.length; i++){
				if(info[i].getState() == NetworkInfo.State.CONNECTED){
					return true;
				}
			}
		}
		return false;
	}

}
