package com.jasonllinux.app.dict;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class DbAdapter {

	private static final String DATABASE_NAME = "wordbook.db"; // 数据库名称
	private static final int DATABASE_VERSION = 1; // 数据库版本

	private static final String TAG = "DbAdapter"; // 标签
	public static DatabaseHelper dbHelper;
	public static SQLiteDatabase db;

	private static Context ctx;

	public static class DatabaseHelper extends SQLiteOpenHelper {

		public DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			try {
				db.execSQL("CREATE TABLE wordbook "
						+ "(_id INTEGER PRIMARY KEY AUTOINCREMENT, "
						+
						// "WordID INTEGER, " +
						"Word TEXT, " + "Phonetics TEXT, "
						+ "Translation TEXT);");

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// Log.w(TAG,
			// "Upgrading database, this will drop tables and recreate.");
			db.execSQL("DROP TABLE IF EXISTS " + WordBook.TABLE_NAME);
			onCreate(db);
		}

		@Override
		public synchronized void close() {
			if (db != null)
				db.close();
			super.close();
		}
	}

	/**
	 * 构造函数 Constructor - takes the context to allow the database to be
	 * opened/created
	 * 
	 * @param ctx
	 *            the Context within which to work
	 */
	public DbAdapter(Context ctx) {
		DbAdapter.ctx = ctx;
		if (dbHelper == null) {
			dbHelper = new DatabaseHelper(ctx);
			db = dbHelper.getWritableDatabase();
		}
	}

	public void close() {
		dbHelper.close();
		dbHelper = null;
	}

	public long saveWord(ContentValues WordParams) {
		if (!ifExistsWord((String) WordParams.get(WordBook.Word)))
			return db.insert(WordBook.TABLE_NAME, null, WordParams);
		else {
			return -1;
		}
	}

	/*
	 * public void saveEmail(String theSql) { db.execSQL(theSql); }
	 */

	public boolean ifExistsWord(String Word) throws SQLException {

		String[] columns = { Word };
		Cursor cursor = db.query(true, WordBook.TABLE_NAME, null, WordBook.Word
				+ "=?", columns, null, null, null, null);
		cursor.moveToFirst();

		if (!cursor.isAfterLast() && (cursor.getString(1) != null)) {
			return true;
		} else {
			return false;

		}
	}

	/**
	 * Delete the email settings with the given emailId
	 * 
	 * @param emailId
	 *            id of email settings to delete
	 * @return true if deleted, false otherwise
	 */
	public boolean deleteWord(String Word) {
		return db.delete(WordBook.TABLE_NAME,
				WordBook.Word + "='" + Word + "'", null) > 0;
	}

	/**
	 * Delete the all WorkBook settings from database. Useful during testing.
	 * 
	 * @return true if deleted, false otherwise
	 */
	public boolean deleteAllWorkBook() {
		return db.delete(WordBook.TABLE_NAME, WordBook._ID + "> 0", null) > 0;
	}

	/**
	 * Return a Cursor over the list of all WorkBook in the database
	 * 
	 * @return Cursor over all WorkBook
	 */
	public Cursor fetchAllWorkBook() {

		return db.query(WordBook.TABLE_NAME, new String[] { "*" }, null, null,
				null, null, WordBook.Word);
	}

	/**
	 * Return a Cursor positioned at the email that matches the given emailId
	 * 
	 * @param emailId
	 *            id of email to retrieve
	 * @return Cursor positioned to matching email, if found
	 * @throws SQLException
	 *             if email could not be found/retrieved
	 */
	public Cursor fetchWord(long WordId) throws SQLException {

		Cursor cursor = db.query(true, WordBook.TABLE_NAME,
				new String[] { "*" }, WordBook._ID + "=" + WordId, null, null,
				null, null, null);
		if (cursor != null) {
			cursor.moveToFirst();
		}
		return cursor;
	}

	/**
	 * Update the email settings using the details provided. The email to be
	 * updated is specified using the emailId, and it is altered to use the
	 * values passed in
	 * 
	 * @param emailId
	 *            id of email to update
	 * @param emailParams
	 *            to set new email parameters
	 * @return true if the email was successfully updated, false otherwise
	 */
	public boolean updateSavedWord(long WordId, ContentValues WordParams) {
		return db.update(WordBook.TABLE_NAME, WordParams, WordBook._ID + "="
				+ WordId, null) > 0;
	}

	/**
	 * WorkBook table
	 */

	public static final class WordBook implements BaseColumns {

		private WordBook() {

		}

		/**
		 * Table name
		 */
		public static String TABLE_NAME = "wordbook";

		/**
		 * The message id
		 * <P>
		 * Type: TEXT
		 * </P>
		 */
		public static final String _ID = "_id";

		// public static final String WordID = "WordID";

		public static final String Word = "Word";

		public static final String Phonetics = "Phonetics";

		public static final String Translation = "Translation";

	}

}
