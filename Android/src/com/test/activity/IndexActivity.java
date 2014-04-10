package com.test.activity;

import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.test.util.LogUtil;
import com.test.util.SharedPreferencesUtil;

public class IndexActivity extends BaseActivity implements OnClickListener{
	
	ListView list ;
	GridView gridView;
	int showType = 0;
	ImageAdapter imageAdapter;
	StrListAdapter strListAdapter;
	TextView topText;
	Button exitBtn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.index);
		//topText = (TextView)findViewById(R.id.top_bar_center_text);
		//topText.setText(R.string.index_text);
		list = (ListView)findViewById(R.id.list);
		gridView = (GridView)findViewById(R.id.gridList);
		imageAdapter = new ImageAdapter(this);
		strListAdapter = new StrListAdapter(this);
		gridView.setVisibility(View.GONE);
		list.setAdapter(strListAdapter);
		
		//
		//Button gridBtn = (Button)findViewById(R.id.top_bar_right_button);
		//exitBtn = (Button)findViewById(R.id.top_bar_left_button);
		//gridBtn.setOnClickListener(this);
		//exitBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
//		case R.id.top_bar_right_button:
//			if(showType == 0){
//				showType = 1;
//				gridView.setNumColumns(4);
//				gridView.setVisibility(View.VISIBLE);
//				list.setVisibility(View.GONE);
//				gridView.setAdapter(imageAdapter);
//				imageAdapter.notifyDataSetChanged();
//			} else {
//				showType = 0;
//				list.setVisibility(View.VISIBLE);
//				gridView.setVisibility(View.GONE);
//				list.setAdapter(strListAdapter);
//				strListAdapter.notifyDataSetChanged();
//			}
//			break;
//		case R.id.top_bar_left_button:
//			String msg = "确定要退出吗？";
//			Builder builder = new Builder(IndexActivity.this);
//			builder.setTitle("提示");
//			builder.setMessage(msg);
//			builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//				@Override
//				public void onClick(DialogInterface dialog, int which) {
//					LogUtil.debug("------------退出-----------");
//					SharedPreferencesUtil.clearUserInfo(getApplicationContext());
//					IndexActivity.this.finish();
//					android.os.Process.killProcess(android.os.Process.myPid());
//					System.exit(0);
//				}
//			});
//			builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//				@Override
//				public void onClick(DialogInterface dialog, int which) {
//					dialog.cancel();
//				}
//			});
//			
//			builder.show();
		}
	}
	
	//退出键
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK){
			String msg = "确定要退出吗？";
			Builder builder = new Builder(IndexActivity.this);
			builder.setTitle("提示");
			builder.setMessage(msg);
			builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					LogUtil.debug("------------退出键退出-----------");
					SharedPreferencesUtil.clearUserInfo(getApplicationContext());
					IndexActivity.this.finish();
					android.os.Process.killProcess(android.os.Process.myPid());
					System.exit(0);
				}
			});
			builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.cancel();
				}
			});
			
			builder.show();
			return true;
		} else {
			return super.onKeyDown(keyCode, event);
		}
	}
	
	class uploadUserStateTast extends AsyncTask<Integer, Void, String>{
		private String result;
		@Override
		protected String doInBackground(Integer... params) {
			return null;
		}
		
		@Override
		protected void onPostExecute(String result) {
			LogUtil.debug("onPostExecute()-------------" + result);
			IndexActivity.this.finish();
			android.os.Process.killProcess(android.os.Process.myPid());
			System.exit(0);
			super.onPostExecute(result);
		}
		
	}
	
	//
	private void onExitCurrentApplication(){
		
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.activity_main, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()){
		case R.id.action_search:
			//
			return true;
		case R.id.action_settings:
			//
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
}
