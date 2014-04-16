package com.test.util;

import com.test.util.FeedReaderContract.FeedEntry;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.GestureDetector.OnDoubleTapListener;

public class FeedReaderDbHelper extends SQLiteOpenHelper {
	
	public static final int DATABASE_VERSION = 1;
	public static final String DATABASE_NAME = "FeedReader.db";

	public FeedReaderDbHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(FeedReaderContract.SQL_CREATE_ENTRIES);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(FeedReaderContract.SQL_DELETE_ENTRIES);
		onCreate(db);
	}
	
	@Override
	public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		onUpgrade(db, oldVersion, newVersion);
	}
	
	public void insert(Integer id, String title, String subtitle){
		FeedReaderDbHelper helper = new FeedReaderDbHelper(null);
		SQLiteDatabase db = helper.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(FeedEntry.COLUMN_NAME_ENTRY_ID, id);
		values.put(FeedEntry.COLUMN_NAME_TITLE, title);
		values.put(FeedEntry.COLUMN_NAME_SUBTITLE, subtitle);
		long newRowId;
		newRowId = db.insert(FeedEntry.TABLE_NAME, FeedEntry.COLUMN_NAME_NULLABLE, values);
	}
	
	public void read(String selection, String[] selectionArgs){
		FeedReaderDbHelper helper = new FeedReaderDbHelper(null);
		SQLiteDatabase db = helper.getReadableDatabase();
		String[] projection = {FeedEntry._ID, FeedEntry.COLUMN_NAME_TITLE, FeedEntry.COLUMN_NAME_SUBTITLE};
		String sortOrder = FeedEntry.COLUMN_NAME_SUBTITLE + "DESC";
		Cursor cursor = db.query(FeedEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
		cursor.moveToFirst();
		long itemId = cursor.getLong(cursor.getColumnIndexOrThrow(FeedEntry._ID));
	}
	
	public void delete(String rowId){
		FeedReaderDbHelper helper = new FeedReaderDbHelper(null);
		SQLiteDatabase db = helper.getReadableDatabase();
		String selection = FeedEntry.COLUMN_NAME_ENTRY_ID + " LIKE ?";
		String[] selectionArgs = {String.valueOf(rowId)};
		db.delete(FeedEntry.TABLE_NAME, selection, selectionArgs);
		
	}
	
	public void update(String title, String rowId){
		FeedReaderDbHelper helper = new FeedReaderDbHelper(null);
		SQLiteDatabase db = helper.getReadableDatabase();
		ContentValues values = new ContentValues();
		values.put(FeedEntry.COLUMN_NAME_TITLE, title);
		
		String selection = FeedEntry.COLUMN_NAME_ENTRY_ID + " LIKE ?";
		String[] selectionArgs = {String.valueOf(rowId)};
		int count = db.update(FeedEntry.TABLE_NAME, values, selection, selectionArgs);
	}

	
	public static void main(String[] args) {
//		FeedReaderDbHelper db = new FeedReaderDbHelper(context);
	}

}
