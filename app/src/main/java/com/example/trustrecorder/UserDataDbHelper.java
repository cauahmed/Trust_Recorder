package com.example.trustrecorder;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.trustrecorder.UserDataContract.*;

public class UserDataDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "UserData.db";

    public UserDataDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_ENTRIES =
                String.format("CREATE TABLE%s " +
                        "(%s INTEGER PRIMARY KEY %s TIMESTAMP DEFAULT,%s INTEGER NOT NULL,%s INTEGER NOT NULL)",
                        TrustData.TABLE_NAME,
                        TrustData._ID,
                        TrustData.COLUMN_NAME_TIMESTAMP,
                        TrustData.COLUMN_NAME_TRUSTSCORE,
                        TrustData.COLUMN_NAME_TRUSTLEVEL);
        db.execSQL(SQL_CREATE_ENTRIES);


    }

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
