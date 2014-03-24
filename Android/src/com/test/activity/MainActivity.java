package com.test.activity;

import com.test.util.HttpClientUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	EditText username;
	EditText password;
	private static final int REQUEST_CODE = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);
		Button btn = (Button)findViewById(R.id.login_button);
		username = (EditText)findViewById(R.id.username);
		password = (EditText)findViewById(R.id.password);
		btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String s_username = username.getText().toString();
				String s_password = password.getText().toString();
				if("admin".equals(s_username) && "admin".equals(s_password)){
					Intent intent = new Intent();
					intent.setClass(MainActivity.this, IndexActivity.class);
					intent.putExtra("name", "theusername");
					Bundle extras = new Bundle();
					intent.putExtras(extras);
					//startActivity(intent);
					//从第二个页面返回用到的
					startActivityForResult(intent, REQUEST_CODE);   
				} else {   
					Toast.makeText(getApplicationContext(), R.string.login_error, Toast.LENGTH_LONG).show();
				}
			}
		});
		
		new TestTask().execute(0);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		switch(requestCode){
		case RESULT_OK:
			Bundle bundle = new Bundle();
			String name = bundle.getString("name");
			Toast.makeText(getApplicationContext(), name, Toast.LENGTH_SHORT).show();
		}
	}
	
	
	class TestTask extends AsyncTask<Integer, Void, String>{
		private String result;
		@Override
		protected String doInBackground(Integer... params) {
			String url = "http://10.10.38.108:8080/androidpn/manage/login.action";
			result = HttpClientUtil.getStringByPost(url);
			return result;
		}
		
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
		}
	}
}
