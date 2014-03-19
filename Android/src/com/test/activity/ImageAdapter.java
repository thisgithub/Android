package com.test.activity;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter{
	private Context context;
	public ImageAdapter(Context c) {
		context = c;
	}
	@Override
	public int getCount() {
		return mThumbIds.length;
	}
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView view;
		if(convertView == null){
			view = new ImageView(context);
			view.setLayoutParams(new GridView.LayoutParams(85, 85));
			view.setScaleType(ImageView.ScaleType.CENTER_CROP);
			view.setPadding(8, 8, 8, 8);
		} else {
			view = (ImageView)convertView;
		}
		
		view.setImageResource(mThumbIds[position]);
		return view;
	}
	
	private Integer[] mThumbIds = {
			R.drawable.sample_2, R.drawable.sample_3,
			R.drawable.sample_4, R.drawable.sample_5,
			R.drawable.sample_6, R.drawable.sample_7,
			R.drawable.sample_0, R.drawable.sample_1,
			R.drawable.sample_2, R.drawable.sample_3,
			R.drawable.sample_4, R.drawable.sample_5,
			R.drawable.sample_6, R.drawable.sample_7,
			R.drawable.sample_0, R.drawable.sample_1,
			R.drawable.sample_2, R.drawable.sample_3,
			R.drawable.sample_4, R.drawable.sample_5,
			R.drawable.sample_6, R.drawable.sample_7
	};
}
