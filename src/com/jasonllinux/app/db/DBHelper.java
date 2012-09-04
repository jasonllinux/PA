package com.jasonllinux.app.db;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

@SuppressLint("NewApi")
public class DBHelper extends SQLiteOpenHelper{
	
    final private static int DB_VERSION = 1;
    final private static String DB_DATABASE_NAME = "user_info";
	
	private Context mContext;
	private String str;
	private String table;

	
	public DBHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}
	

	public DBHelper(Context context, String name, CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
		super(context, name, factory, version, errorHandler);
	}



	//产生DB
	@Override
	public void onCreate(SQLiteDatabase db) {
	      db.execSQL(
                  "CREATE TABLE MyTable (" +
                          " _ID INTEGER PRIMARY KEY, " +
                          " _DATA VARCHAR(50) NOT NULL, " +
                          " _DATETIME DATETIME NULL " +
                  ")"
          );
	}

	//upGrade DB
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		 db.execSQL("DROP TABLE IF EXISTS MyTable");
         onCreate(db);
	}
	

	
	

}
