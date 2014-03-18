package com.test.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	ListView list;
	EditText username;
	EditText password;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
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
					startActivity(intent);
				} else {
					Toast.makeText(getApplicationContext(), R.string.login_error, Toast.LENGTH_LONG).show();
				}
			}
		});
		list = (ListView)findViewById(R.id.list);
	}
	
}
