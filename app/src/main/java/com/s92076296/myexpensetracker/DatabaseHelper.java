package com.s92076296.myexpensetracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Expenses.db";
    public static final String TABLE_NAME = "expenses_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "AMOUNT";
    public static final String COL_3 = "TYPE";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT," + "AMOUNT INTEGER,TYPE TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String amount, String type) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, amount);
        contentValues.put(COL_3, type);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }
    public Cursor getAllData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor results=db.rawQuery(" select * from "+TABLE_NAME,null);
        return results;
    }
    public boolean upgradeData(String id,String amount,String type){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,amount);
        contentValues.put(COL_3,type);
        db.update(TABLE_NAME,contentValues,"id=?",new String[] {id});
        return true;
    }
    public Integer deleteData(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(TABLE_NAME,"ID=?",new String[]{id});
    }
}