package com.jasonllinux.app.db;

import java.util.List;

import com.jasonllinux.app.user.User;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class UserAuthDataSource {
	
	private SQLiteDatabase dataBase;
	private DBHelper dbHelper;
	
	
	public UserAuthDataSource(Context context) {
		dbHelper = new DBHelper(context);
	}
	
	public void open() {
		dataBase = dbHelper.getWritableDatabase();
		
	}
	
	public void close() {
		dbHelper.close();
	}
	
	public void createUser(User user) {
		String name = user.getName();
		String passwd = user.getPasswd();
		
		String statement = "insert into" + DBHelper.TABLE_USER +"(" + DBHelper.COLUMN_USER_ID + ", " +
		                     DBHelper.COLUMN_NAME + "," + 
				             DBHelper.COLUMN_PASSWD + ") values(?, ?, ?)";
		
		dataBase.execSQL(statement, new Object[] {
				null,
				name,
				passwd
		});
		
	}
	
	public void deleteUser(User user) {
		String name = user.getName();
		String passwd = user.getPasswd();
		String statement = "delete from" + DBHelper.TABLE_USER +" where "+ DBHelper.COLUMN_NAME + "=?";
		dataBase.execSQL(statement, new Object[] {
						name
		});
		
		
	}
	
	public void updateUser(User user) {
		String name = user.getName();
		String passwd = user.getPasswd();
		
	}
	
	private User cursorToComment(Cursor cursor) {
		
		return null;
	}
	
	public List<User> getAllUsers() {
		
		return null;
	}

}
