package com.test.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import com.test.activity.R;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.provider.OpenableColumns;
import android.util.Log;

public class FireDirTest {
	Context context;
	void createExternalstoragePrivateFile(){
		File file = new File(context.getExternalFilesDir(null), "DemoFile.jpg");
		try {
			InputStream is = context.getResources().openRawResource(R.drawable.action_plus);
			OutputStream os = new  FileOutputStream(file);
			byte[] data = new byte[is.available()];
			is.read(data);
			os.write(data);
			
		} catch (Exception e) {
			Log.w("ExternalStorage", "Error writing " + file, e);
		}
	}
	
	void deleteExternalStoragePrivateFile(){
		File file = new File(context.getExternalFilesDir(null), "DemoFile.jpg");
		if(file != null){
			file.delete();
		}
	}
	
	boolean hasExternalStoragePrivateFile(){
		File file = new File(context.getExternalFilesDir(null), "DemoFile.jpg");
		if(file != null){
			return file.exists();
		}
		return false;
	}
	
	//简单的代码复制图片从应用程序的资源到外部文件
	void createExternalStoragePublicPicture(){
		File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
		File file = new File(path, "DemoPicture.jpg");
		try {
			path.mkdirs();
			InputStream is = context.getResources().openRawResource(R.drawable.action_plus);
			OutputStream os = new FileOutputStream(file);
			byte[] data = new byte[is.available()];
			is.read(data);
			os.write(data);
			is.close();
			os.close();
			 // Tell the media scanner about the new file so that it is
	        // immediately available to the user.
			MediaScannerConnection.scanFile(context, new String[]{file.toString()}, null, new MediaScannerConnection.OnScanCompletedListener() {
				@Override
				public void onScanCompleted(String path, Uri uri) {
					Log.i("ExternalStorage", "Scanned" + path + ":");
					Log.i("ExternalStorage", "-> uri=" + uri);
				}
			});
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	void deleteExternalStoragePublicPicture(){
		File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
		File file = new File(path, "DemoPicture.jpg");
		file.delete();
	}
	
	boolean hasExternalStoragePublicPicture(){
		File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
		File file = new File(path, "DemoPicture.jpg");
		return file.exists();
	}
	
	
	//
	BroadcastReceiver mExternalStorageReceiver;
	boolean  mExternalStorageAvailable = false;
	boolean mExternalStorageWriteable = false;
	
	void updateExternalStorageState(){
		String state = Environment.getExternalStorageState();
		if(Environment.MEDIA_MOUNTED.equals(state)){
			mExternalStorageAvailable = mExternalStorageWriteable = true;
		} else if(Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)){
			mExternalStorageAvailable = true;
			mExternalStorageWriteable = false;
		} else {
			mExternalStorageAvailable = mExternalStorageWriteable = false;
		}
		
		//handleExternalStorageState(mExternalStorageAvailable, mExternalStorageWriteable);
	}
	
	void startWatchingExternalStorage(){
		mExternalStorageReceiver = new BroadcastReceiver() {
			@Override
			public void onReceive(Context context, Intent intent) {
				updateExternalStorageState();
			}
		};
		
		IntentFilter filter = new IntentFilter();
		filter.addAction(Intent.ACTION_MEDIA_MOUNTED);
		filter.addAction(Intent.ACTION_MEDIA_REMOVED);
		context.registerReceiver(mExternalStorageReceiver, filter);
		updateExternalStorageState();
	}
	
	void stopWatchingExternalStorage(){
		context.unregisterReceiver(mExternalStorageReceiver);
	}
	
	String filename = "myfile";
	String string = "Hello World! ";
	FileOutputStream outputStream;
	
	public File getTempFile(Context context, String url){
		File file = null;
		try {
//			outputStream = context.openFileOutput(filename, Context.MODE_PRIVATE);
//			outputStream.write(string.getBytes());
//			outputStream.close();
			String fileName = Uri.parse(url).getLastPathSegment();
			file = File.createTempFile(fileName, null, context.getCacheDir());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return file;
	}
	
	
	//Checks if external storage is available for read and write
	public boolean isExternalStorageWriteable(){
		String state = Environment.getExternalStorageState();
		if(Environment.MEDIA_MOUNTED.equals(state)){
			return true;
		}
		return false;
	}
	
	//Checks if external storage is available to at least read
	public boolean isExternalStorageReadable(){
		String state = Environment.getExternalStorageState();
		if(Environment.MEDIA_MOUNTED.equals(state) || Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)){
			return true;
		}
		return false;
	}
	
	public File getAlbumStorageDir(String albumName){
		// Get the directory for the user's public pictures directory.
		File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), albumName);
		if(!file.mkdirs()){
			//
		}
		return file;
	}
	
	//一个你可以用来创建一个个人相册目录的方法
	public File getAlbumStorageDir(Context context, String albumName){
		//Get the directory for the app's private pictures directory.
		File file = new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), albumName);
		if(!file.mkdirs()){
			//
		}
		return file;
	}
}
