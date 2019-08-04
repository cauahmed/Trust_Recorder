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
                        "(%s INTEGER PRIMARY KEY %s TIMESTAMP DEFAULT,%s INTEGER NOT NULL)",
                        TrustChange.TABLE_NAME,
                        TrustChange._ID,
                        TrustChange.COLUMN_NAME_TIMESTAMP,
                        TrustChange.COLUMN_NAME_TRUSTSCORE);
        db.execSQL(SQL_CREATE_ENTRIES);

        final String SQL_CREATE_ENTRIES2 =
                String.format("CREATE TABLE%s " +
                        "(%s INTEGER PRIMARY KEY %s TIMESTAMP DEFAULT,%s INTEGER NOT NULL)",
                        TrustLevel.TABLE_NAME,
                        TrustLevel._ID,
                        TrustLevel.COLUMN_NAME_TIMESTAMP,
                        TrustLevel.COLUMN_NAME_TRUSTLEVEL);
        db.execSQL(SQL_CREATE_ENTRIES2);

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + TrustChange.TABLE_NAME;

        final String SQL_DELETE_ENTRIES2 =
                "DROP TABLE IF EXISTS " + TrustLevel.TABLE_NAME;

        db.execSQL(SQL_DELETE_ENTRIES);
        db.execSQL(SQL_DELETE_ENTRIES2);
        onCreate(db);

    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
