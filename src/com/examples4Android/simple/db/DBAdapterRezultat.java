package com.examples4Android.simple.db;

import com.examples4Android.simple.Rezultat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class DBAdapterRezultat implements BaseColumns {
	public static final  String TAG="DBAdapterRezultat";

	public static final  String STANJE = "i_stanje";
	public static final  String NAME = "s_name";
	public static final  int POS__ID=0;
	public static final  int POS_NAME=1;
	public static final  int POS_STANJE=2;

	public static final  String TABLE="rezultati";



	private final Context context;

	private DatabaseHelper DBHelper;
	private SQLiteDatabase db;

	public DBAdapterRezultat(Context ctx) 
	{
		this.context = ctx;
		DBHelper = new DatabaseHelper(context);
	}


	//---opens the database---
	public DBAdapterRezultat open() throws SQLException 
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
	public long insertRezultat(Rezultat stevec) 
	{
		ContentValues initialValues = new ContentValues();
		initialValues.put(NAME, stevec.getIme()); 
		initialValues.put(STANJE, stevec.getTock()); 
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
				STANJE},    //POS_VALUE =2
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
					STANJE}, 
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
	public boolean updateStevec(Rezultat tmp) 
	{
		ContentValues args = new ContentValues();
		args.put(NAME, tmp.getIme());
		args.put(STANJE, tmp.getTock());
		return db.update(TABLE, args, 
				_ID + "=" + tmp.getId(), null) > 0;
	}


}
