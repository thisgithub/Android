package com.test.activity;

import com.test.util.NetUtil;

import android.app.Activity;
import android.view.KeyEvent;
import android.widget.Toast;

public class BaseActivity extends Activity {

	
	public boolean checkNetWorkState(){
		boolean netWorkState = NetUtil.checkNetWork(this);
		if(!netWorkState){
			Toast.makeText(this, "亲，你的网络不给力哦，请检测网络！", Toast.LENGTH_SHORT).show();
			return false;
		}
		return true;
	}
	
	//退出键
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_HOME){
			
		}
		return super.onKeyDown(keyCode, event);
	}
}
