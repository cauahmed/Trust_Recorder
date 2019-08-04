package com.example.trustrecorder;

import android.provider.BaseColumns;

public class UserDataContract {

    private UserDataContract(){}

    public static class TrustChange implements BaseColumns {
        public static final String TABLE_NAME =  "ChangeData";
        public static final String COLUMN_NAME_TIMESTAMP =  "Timestamp";
        public static final String COLUMN_NAME_TRUSTSCORE = "TrustScore";
    }

    public static class TrustLevel implements BaseColumns {
        public static final String TABLE_NAME =  "LevelData";
        public static final String COLUMN_NAME_TIMESTAMP =  "Timestamp";
        public static final String COLUMN_NAME_TRUSTLEVEL = "TrustLevel";
    }

}

