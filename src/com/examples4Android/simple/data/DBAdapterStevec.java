package com.examples4Android.simple.data;

import com.examples4Android.simple.Stevec;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class DBAdapterStevec implements BaseColumns {
	public static final  String TAG="DBAdapterStevec";

	public static final  String VALUE = "i_value";
	public static final  String NAME = "s_name";
	public static final  int POS__ID=0;
	public static final  int POS_NAME=1;
	public static final  int POS_VALUE=2;

	public static final  String TABLE="stevec";



	private final Context context;

	private DatabaseHelper DBHelper;
	private SQLiteDatabase db;

	public DBAdapterStevec(Context ctx) 
	{
		this.context = ctx;
		DBHelper = new DatabaseHelper(context);
	}


	//---opens the database---
	public DBAdapterStevec open() throws SQLException 
	{
		db = DBHelper.getWritableDatabase();
		return this;
	}

	//---closes the database---    
	public void close() 
	{
		DBHelper.close();
	}

	//---insert a stevec
	public long insertStevc(Stevec stevec) 
	{
		ContentValues initialValues = new ContentValues();
		initialValues.put(NAME, stevec.getName()); 
		initialValues.put(VALUE, stevec.getStanje()); 
		return db.insert(TABLE, null, initialValues);
	}

	//---deletes a particular title---
	public boolean deleteStevec(long rowId) 
	{
		return db.delete(TABLE, _ID + "=" + rowId, null) > 0;
	}

	//---retrieves all the titles---
	public Cursor getAll() 
	{
		return db.query(TABLE, new String[] {
				_ID,       //POS__ID=0;
				NAME,      //POS_NAME=1
				VALUE},    //POS_VALUE =2
				null, 
				null, 
				null, 
				null, 
				null);
	}

	//---retrieves a particular title---
	public Cursor getStevec(long rowId) throws SQLException 
	{
		Cursor mCursor =
			db.query(true, TABLE, new String[] {
					_ID, 
					NAME,
					VALUE}, 
					_ID + "=" + rowId, 
					null,
					null, 
					null, 
					null, 
					null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}

	//---update---
	public boolean updateStevec(Stevec tmp) 
	{
		ContentValues args = new ContentValues();
		args.put(NAME, tmp.getName());
		args.put(VALUE, tmp.getStanje());
		return db.update(TABLE, args, 
				_ID + "=" + tmp.getDbID(), null) > 0;
	}


}
