package com.project.datahelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

	static final String dbName = "database";

	
	@Override
	public void onCreate(SQLiteDatabase arg0) {
		// create table in database
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}
	public DatabaseHelper(Context context){
		super(context, dbName, null, 1);
	}

}
