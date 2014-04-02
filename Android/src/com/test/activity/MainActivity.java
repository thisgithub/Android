package com.test.activity;

import java.util.HashMap;
import java.util.Map;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.test.model.UserBean;
import com.test.util.ApplicationContext;
import com.test.util.HttpClientUtil;
import com.test.util.JSONUtil;
import com.test.util.LogUtil;
import com.test.util.SharedPreferencesUtil;

public class MainActivity extends BaseActivity implements OnClickListener{
	
	private String usernameStr;
	private String passwordStr;
	private EditText username;
	private EditText password;
	private Context context;
	private UserBean user;
	private Button btn;
	private SharedPreferences sf;
	private static final int REQUEST_CODE = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);
		sf = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
		usernameStr = sf.getString("username", null);
		passwordStr = sf.getString("password", null);
		initComponent();
		initDate();
	}
	
	private void initComponent(){
		btn = (Button)findViewById(R.id.login_button);
		username = (EditText)findViewById(R.id.username);
		password = (EditText)findViewById(R.id.password);
		btn.setOnClickListener(this);
	}
	
	public void initDate(){
		context = this;
		username.setText(usernameStr);
		password.setText(passwordStr);
		
		if(null != usernameStr && null != passwordStr && !"".equals(usernameStr.trim()) && !"".equals(passwordStr.trim())){
			if(checkLogin()){//自动登陆
				if(!checkNetWorkState()){
					return ;
				}
				LogUtil.debug("-----------------checkLogin()" + checkLogin());
				new TestTask().execute(0);
			}
		}
		
	}
	
	private boolean checkLogin(){
		LogUtil.debug("-----------------checkLogin()");
		if(username == null || "".equals(username.getText().toString().trim())){
			Toast.makeText(this, "用户名不能为空！", Toast.LENGTH_SHORT).show();
			return false;
		} else if(password == null || "".equals(password.getText().toString().trim())){
			Toast.makeText(this, "密码不能为空！", Toast.LENGTH_SHORT).show();
			return false;
		} else {
			return true;
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch(requestCode){
		case RESULT_OK:
			Bundle bundle = new Bundle();
			String name = bundle.getString("name");
			Toast.makeText(getApplicationContext(), name, Toast.LENGTH_SHORT).show();
		}
	}
	
	private String url;
	
	class TestTask extends AsyncTask<Integer, Void, String>{
		private String result;
		private ProgressDialog progressDialog;
		
		@Override
		protected void onPreExecute() {
			LogUtil.debug("onPreExecute()---------");
			usernameStr = username.getText().toString().trim();
			passwordStr = password.getText().toString().trim();
			progressDialog = ProgressDialog.show(MainActivity.this, null, "正在登录，请稍后。", true);
			super.onPreExecute();
		}
		
		@Override
		protected String doInBackground(Integer... params) {
			LogUtil.debug("------doInBackground()------");
			url = ApplicationContext.getActionUrlByResId(context, R.string.user_login_action);
			Map map = new HashMap();
			map.put("username", usernameStr);
			map.put("password", passwordStr);
			
			try {
				LogUtil.debug("------usernameStr: " + usernameStr + "_____" + "--------passwordStr: " + passwordStr);
				result = HttpClientUtil.getStringByPost(url, map, (10 * 1000));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}
		
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			LogUtil.debug("-----onPostExecute()-------" + result);
			if(null != progressDialog && progressDialog.isShowing()){
				progressDialog.dismiss();
				progressDialog = null;
			}
			if("0".equals(result)){
				Toast.makeText(getApplicationContext(), "请填写用户名和密码！", Toast.LENGTH_LONG).show();
			} else if("3".equals(result)){
				Toast.makeText(getApplicationContext(), "请检查网络连接！", Toast.LENGTH_LONG).show();
			} else if("4".equals(result)){
				Toast.makeText(getApplicationContext(), "用户名不存在", Toast.LENGTH_LONG).show();
			} else {
				user = JSONUtil.getUserBean(result);
				if(null == user){
					return ;
				}
				SharedPreferencesUtil.saveUserInfo(getApplicationContext(), user.getUserId(), user.getUsername(), user.getPassword(), user.getOnline(), "");
				Toast.makeText(getApplicationContext(), "登陆成功", Toast.LENGTH_LONG).show();
				Intent intent = new Intent(MainActivity.this, IndexActivity.class);
				LogUtil.debug("----------------跳到IndexActivity---");
				//startActivity(intent);
				//从第二个页面返回用到的
				startActivity(intent);
				finish();
			}
		}
	}


	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.login_button:
			if(checkLogin()){//自动登陆
				if(!checkNetWorkState()){
					return ;
				}
				LogUtil.debug("-----------------checkLogin()" + checkLogin());
				new TestTask().execute(0);
			}
			break;
			default: break;
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.activity_main, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()){
		case R.id.action_search:
			//...
			return true;
		case R.id.action_settings:
			//...
			return true;
		default :
			return super.onOptionsItemSelected(item);
		}
	}
}
