package com.example.mylogin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "UserDetails.db";
    private static final int DATABASE_VERSION = 5;
    public static final String TABLE_NAME = "User";
    private static String DROP_TABLE = "drop_table";
    public static final String ID = "_id";
    public static final String NAME = "name";
    public static final String AGE = "age";
    public static final String PLACE = "place";
    public static final String DESIGNATION = "designation";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        String CREATE_DB_TABLE =
                " CREATE TABLE " + TABLE_NAME +
                        " (_id INTEGER PRIMARY KEY , " +
                        " name TEXT NOT NULL, " + " age TEXT NOT NULL, " + " place TEXT NOT NULL, " +
                        " designation TEXT NOT NULL);";
        db.execSQL(CREATE_DB_TABLE);
    }

    public static void dropTable(SQLiteDatabase db) {

        db.execSQL(DROP_TABLE + NAME);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean insertData(String id, String name, String age, String place, String designation) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID, id);
        contentValues.put(NAME, name);
        contentValues.put(AGE, age);
        contentValues.put(PLACE, place);
        contentValues.put(DESIGNATION, designation);
        long result = db.insert(TABLE_NAME, null, contentValues);
        Log.i("Log", "result:" + result);
        if (result == -1)
            return false;
        else
            return true;
    }

    public boolean updateData(String id, String name, String age, String place, String designation) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID, id);
        contentValues.put(NAME, name);
        contentValues.put(AGE, age);
        contentValues.put(PLACE, place);
        contentValues.put(DESIGNATION, designation);
        long result = db.update(TABLE_NAME, contentValues, "_id = ?", new String[]{id});
        Log.i("Log", "result:" + result);
        if (result == 0)
            return false;
        else
            return true;
    }

    public Integer deleteData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "_id = ?", new String[]{id});
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }

}
