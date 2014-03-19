package com.test.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

public class IndexActivity extends Activity implements OnClickListener{
	
	ListView list ;
	GridView gridView;
	int showType = 0;
	ImageAdapter imageAdapter;
	StrListAdapter strListAdapter;
	TextView topText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.index);
		topText = (TextView)findViewById(R.id.top_bar_center_text);
		topText.setText(R.string.index_text);
		list = (ListView)findViewById(R.id.list);
		gridView = (GridView)findViewById(R.id.gridList);
		imageAdapter = new ImageAdapter(this);
		strListAdapter = new StrListAdapter(this);
		gridView.setVisibility(View.GONE);
		list.setAdapter(strListAdapter);
		
		//
		Button gridBtn = (Button)findViewById(R.id.top_bar_right_button);
		gridBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.top_bar_right_button:
			if(showType == 0){
				showType = 1;
				gridView.setNumColumns(4);
				gridView.setVisibility(View.VISIBLE);
				list.setVisibility(View.GONE);
				gridView.setAdapter(imageAdapter);
				imageAdapter.notifyDataSetChanged();
			} else {
				showType = 0;
				list.setVisibility(View.VISIBLE);
				gridView.setVisibility(View.GONE);
				list.setAdapter(strListAdapter);
				strListAdapter.notifyDataSetChanged();
			}
		}
	}
	
	
}
