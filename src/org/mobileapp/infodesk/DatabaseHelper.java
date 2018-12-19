package org.mobileapp.infodesk;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{
	public static final String LOCALdb_NAME = "ySQLiteDatabase";
	public static final String userTable_NAME = "users_tbl";
	public static final String user_ID = "id";
	public static final String user_USER = "user";
	public static final String user_pass = "password";
	public static final String user_type = "user_type";
	public static final String newsfeeedTBL = "news_tbl";
	public static final String newsIdCol = "news_id";
	public static final String ContentCol = "news_Body";
	public static final String authorCol = "news_Author";
	public static final String dateCol = "news_Date";
	public static final String eventTbl = "event_tbl";
	public static final String eventId = "event_id";
	public static final String eventName =  "event_name";
	public static final String eventWriter = "event_writer";
	public static final String eventConcerns = "event_conserns";
	public static final String eventDate = "event_date";
	
	public static final int DB_VERSION = 1;
	
	public DatabaseHelper(Context context){
		super(context, LOCALdb_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase sqLiteDatabase){
		String sql = "CREATE TABLE "+userTable_NAME+" ("+user_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+user_USER+" VARCHAR, "+user_pass+" VARCHAR, " +user_type + " VARCHAR)";
		sqLiteDatabase.execSQL(sql);	
		String sql2 = "CREATE TABLE "+newsfeeedTBL+" ("+newsIdCol+" INTEGER PRIMARY KEY AUTOINCREMENT, "+ContentCol+" VARCHAR, "+authorCol+" VARCHAR, "+dateCol+" VARCHAR)";
		sqLiteDatabase.execSQL(sql2);
		String sql3 = "CREATE TABLR "+eventTbl+" (" +eventId+" INTEGER PRIMARY KEY AUTOINCREMENT, "+eventName+" VARCHAR, "+eventConcerns+" VARCHAR, "+eventWriter+" VARCHAR, "+ eventDate+" VARCHAR)";
		sqLiteDatabase.execSQL(sql3);
	}
	
	
	@Override
	public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int il){
		String sql = "DROP TABLE IF EXISTS"+ userTable_NAME;
		sqLiteDatabase.execSQL(sql);
		onCreate(sqLiteDatabase);
	}
	
	
	public boolean addACC(String name){
		SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
		
		ContentValues contentValues = new ContentValues();
		
		contentValues.put(user_USER, name);
		
		return sqLiteDatabase.insert(userTable_NAME, null, contentValues) != 1;
	}
	
	public boolean addCont(String content){
		SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		contentValues.put(user_pass, content);
		
		return sqLiteDatabase.insert(userTable_NAME, null, contentValues) !=1;
		
		
	}
	
	public Cursor getUser(){
		SQLiteDatabase sqLiteDatabae = getReadableDatabase();
		String sql = "SELECT * FROM " + userTable_NAME + ";";
		return sqLiteDatabae.rawQuery(sql, null);
	}
	
}
