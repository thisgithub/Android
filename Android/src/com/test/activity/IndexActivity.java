package com.test.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class IndexActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.index);
		Bundle bundle = this.getIntent().getExtras();
		ArrayList<String> doList = bundle.getStringArrayList("sendList");
		ListView list = (ListView)findViewById(R.id.list);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, doList);
		list.setAdapter(adapter);
		
		//
		Button gridBtn = (Button)findViewById(R.id.top_bar_right_button);
		gridBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				GridView gridView = (GridView)findViewById(R.id.gridList);
				gridView.setAdapter(new ImageAdapter(getApplicationContext()));
				gridView.setOnItemClickListener(new OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent, View v,
							int position, long id) {
						Toast.makeText(IndexActivity.this, "" + position, Toast.LENGTH_SHORT).show();
					}
				});
			}
		});
	}
	
}
