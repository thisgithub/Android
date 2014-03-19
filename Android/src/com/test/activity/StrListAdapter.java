package com.test.activity;

import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class StrListAdapter extends BaseAdapter {
	private Context context;
	
	public StrListAdapter(Context c) {
		context = c;
	}
	@Override
	public int getCount() {
		
		return strList.length;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		TextView text ;
		if(convertView == null){
			text = new TextView(context);
			text.setLayoutParams(new ListView.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
			text.setTextSize(24);
		} else {
			text = (TextView)convertView;
		}
		text.setText(strList[position]);
		return text;
	}
	
	private String[] strList = {
		"do one thing at a time, and do well",
		"never forget to say 'thanks'",
		"keep on going never give up",
		"whatever is worth doing is worth doing well",
		"believe in yourself",
		"I can because i think i can",
		"action speek louder than words"
	};

}
