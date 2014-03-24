package com.test.test;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.test.activity.R;
import com.test.util.LogUtil;

public class TestActivity extends Activity {

	private Button execute;
	private Button cancel;
	private ProgressBar progressBar;
	private TextView textView;
	
	private MyTask myTask;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test);
		execute = (Button)findViewById(R.id.execute);
		cancel = (Button)findViewById(R.id.cancel);
		progressBar = (ProgressBar)findViewById(R.id.progress_bar);
		textView = (TextView)findViewById(R.id.text_view);
		
		execute.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				myTask = new MyTask();
				myTask.execute("http://www.baidu.com");
				execute.setEnabled(false);
				cancel.setEnabled(true);
			}
		});
		
		cancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				myTask.cancel(true);
			}
		});
	}
	
	
	
	
	private class MyTask extends AsyncTask<String, Integer, String>{
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			LogUtil.debug("onPreExecute called!----");
			textView.setText("loading......");
		}

		@Override
		protected String doInBackground(String... params) {
			LogUtil.debug("doInBackground(Params... params) called------");
			try {
				HttpClient client = new DefaultHttpClient();
				HttpGet get = new HttpGet(params[0]);
				HttpResponse response = client.execute(get);
				if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
					HttpEntity entity = response.getEntity();
					InputStream in = entity.getContent();
					long total = entity.getContentLength();
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					byte[] buff = new byte[1024];
					int count = 0;
					int length = -1;
					while((length = in.read(buff)) != -1){
						baos.write(buff, 0, length);
						count += length;
						publishProgress((int)((count / (float)total) * 100));
						Thread.sleep(500);
					}
					
					return new String(baos.toByteArray(), "gb2312");
				}
			} catch (Exception e) {
				LogUtil.error("doback error-----", e);
			}
			return null;
		}
		
		@Override
		protected void onProgressUpdate(Integer... progress) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(progress);
			LogUtil.debug("onProgressUpdate(Progress... progresses) called------");
			progressBar.setProgress(progress[0]);
			textView.setText("loading...." + progress[0] + "%");
		}
		
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			LogUtil.debug("onPostExecute(Result result) called------");
			textView.setText(result);
			execute.setEnabled(true);
			cancel.setEnabled(false);
		}
		
		@Override
		protected void onCancelled() {
			// TODO Auto-generated method stub
			super.onCancelled();
			LogUtil.debug("onCancelled() called-----");
			textView.setText("canceled");
			progressBar.setProgress(0);
			execute.setEnabled(true);
			cancel.setEnabled(false);
		}
		
	}
}
