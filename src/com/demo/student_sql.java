package com.demo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class student_sql extends SQLiteOpenHelper {

	public student_sql(Context context) {
		super(context, "db", null, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("CREATE TABLE user(" +
				"_id INTEGER PRIMARY KEY AUTOINCREMENT,"+
				"stuid INTEGER DEFAULT \"\","+
				"name TEXT DEFAULT \"\","+
				"depar TEXT DEFAULT \"\","+
				"class TEXT DEFAULT \"\","+
				"sex TEXT DEFAULT \"\","+
				"age TEXT DEFAULT \"\","+
				"phone TEXT DEFAULT \"\","+
				"qq TEXT DEFAULT \"\","+
				"adress TEXT DEFAULT \"\")");

	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

}
