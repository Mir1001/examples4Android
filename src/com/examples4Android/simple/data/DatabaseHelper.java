package com.examples4Android.simple.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public  class DatabaseHelper extends SQLiteOpenHelper 
{	

	public static final  String TAG="DatabaseHelper";
	private static final int DATABASE_VERSION = 2;
	private static final String DATABASE_NAME = "mojtest";
	private static final String DATABASE_CREATE =
		"create table "+DBAdapterStevec.TABLE+" ("+DBAdapterStevec._ID+" integer primary key autoincrement, "
		+ DBAdapterStevec.NAME+" TEXT not null, "+DBAdapterStevec.VALUE+" INTEGER );";

	DatabaseHelper(Context context) 
	{
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) 
	{
		db.execSQL(DATABASE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, 
			int newVersion) 
	{
		Log.w(TAG, "Upgrading database from version " + oldVersion 
				+ " to "
				+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS "+DBAdapterStevec.TABLE);
		onCreate(db);
	}
}
