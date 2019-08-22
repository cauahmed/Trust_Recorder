package com.example.trustrecorder;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.trustrecorder.UserDataContract.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class UserDataDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "UserData.db";

    public UserDataDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_ENTRIES = "CREATE TABLE " + TrustData.TABLE_NAME +
                " (" + TrustData.COLUMN_NAME_ID + " TEXT," + TrustData.COLUMN_NAME_TIMESTAMP + " TEXT,"
                + TrustData.COLUMN_NAME_TRUSTCHANGESCORE + " TEXT," + TrustData.COLUMN_NAME_TRUSTLEVELSCORE + " TEXT,"
                + TrustData.COLUMN_NAME_TRUSTLEVELTYPE + " TEXT)";
        db.execSQL(SQL_CREATE_ENTRIES);



    }

    ////////////////////////add by haiyang this method could insert an item into  the database
    public void insertNewRecorder(SQLiteDatabase db, String id, String time, String trust_c_score, String trust_l_score, String trust_l_type){
        ContentValues trust_level_recorder=new ContentValues();
        trust_level_recorder.put(TrustData.COLUMN_NAME_ID,id);
        trust_level_recorder.put(TrustData.COLUMN_NAME_TIMESTAMP, time);
        trust_level_recorder.put(TrustData.COLUMN_NAME_TRUSTCHANGESCORE,trust_c_score);
        trust_level_recorder.put(TrustData.COLUMN_NAME_TRUSTLEVELSCORE, trust_l_score);
        trust_level_recorder.put(TrustData.COLUMN_NAME_TRUSTLEVELTYPE,trust_l_type);

        db.insert(TrustData.TABLE_NAME,null,trust_level_recorder);

    }

    public void delete_By_id(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TrustData.TABLE_NAME , TrustData.COLUMN_NAME_ID + " = ?",
                new String[] { String.valueOf(id) });
    }

    public List<Integer> getAlltrustLevelById(int id) {

        List<Integer> recorders = new ArrayList<Integer>();
        String selectQuery = "SELECT  * FROM " + TrustData.TABLE_NAME + " WHERE "
                + TrustData.COLUMN_NAME_ID + " = " + id;



        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {


                // adding to card list
                recorders.add(c.getInt((c.getColumnIndex(TrustData.COLUMN_NAME_TRUSTLEVELSCORE))));
            } while (c.moveToNext());
        }

        return recorders;
    }


///////////////////////////////////
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + TrustData.TABLE_NAME;


        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);

    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

}
