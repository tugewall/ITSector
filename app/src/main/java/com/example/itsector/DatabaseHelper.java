package com.example.itsector;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";

    private static final String TABLE_NAME = "table_divisions";
    private static final String COL1 = "ID";
    private static final String COL2 = "name";
    private static final String COL3 = "false";

    public DatabaseHelper(@Nullable Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + "("+ COL1 + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL2 + " TEXT," +  COL3 + " TEXT"+ ")";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
            db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
            onCreate(db);
    }

    // insert data on BD
    public boolean addData (String espaco, String turn ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, espaco);
        contentValues.put(COL3, turn);

        Log.d(TAG, "addData: Adding" + espaco + "with" + turn + "to" + TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, contentValues);

        //   if date as inserted incorrectly it will return -1
        if (result == 1){
            return false;
        }else {
            return  true;
        }

    }
/*
    public boolean addData (String espaco, String turn ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, espaco);
        contentValues.put(COL3, turn);

        Log.d(TAG, "addData: Adding" + espaco + "with" + turn + "to" + TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, contentValues);

        //   if date as inserted incorrectly it will return -1
        if (result == 1){
            return false;
        }else {
            return  true;
        }

    }
*/

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public ArrayList<HashMap<String, String>> getAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        String query = "SELECT name, false FROM "+ TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            HashMap<String,String> user = new HashMap<>();
            user.put("name",cursor.getString(cursor.getColumnIndex(COL2)));
            user.put("false",cursor.getString(cursor.getColumnIndex(COL3)));
            userList.add(user);
        }
        return  userList;
    }

    public int UpdateSwitch(String division, String switchs, int id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cVals = new ContentValues();
        cVals.put(COL2, division);
        cVals.put(COL3, switchs);
        int count = db.update(TABLE_NAME, cVals, COL1+" = ?",new String[]{String.valueOf(id)});
        return  count;
    }

}

