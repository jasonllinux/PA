package com.jasonllinux.app.db;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

@SuppressLint("NewApi")
public class DBHelper extends SQLiteOpenHelper{
	
    private final static int DB_VERSION = 1;
    private final static String DB_DATABASE_NAME = "pa.db";
    
    public static final String TABLE_USER = "userauth";
    
    public static final String COLUMN_USER_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PASSWD = "passwd";
    
	public DBHelper(Context context) {
		super(context, DB_DATABASE_NAME, null, DB_VERSION);
	}
	
	public DBHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
		
	}
	

	public DBHelper(Context context, String name, CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
		super(context, name, factory, version, errorHandler);
		
	}


	@Override
	public void onCreate(SQLiteDatabase db) {
		//user&&passwd
//		db.execSQL(DATABASE_CREATE);
//		db.execSQL(
//				"CREATE TABLE MyTable (" +
//				" _ID INTEGER PRIMARY KEY, " +
//                " _DATA VARCHAR(50) NOT NULL, " +
//                " _DATETIME DATETIME NULL " +
//                             ")"
//                  );
		
		//注意语句间的空格
		db.execSQL("create table " + TABLE_USER + "("+
				   COLUMN_USER_ID + " integer "+
				   " primary key autoincrement,"+
				   COLUMN_NAME + " varchar(20), "+
				   COLUMN_PASSWD + " varchar(10)"+
				   ")"
				 );
//		System.out.println("Create sqlite db sucess !!");
		
		//user&&todo
		
		//user&&money
		
	}

	  @Override
	  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	    System.out.println(DBHelper.class.getName() + "\n" +
	        "Upgrading database from version " + oldVersion + " to "
	            + newVersion + ", which will destroy all old data");
	    db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
	    onCreate(db);
	    
	  }
	

	
	

}
