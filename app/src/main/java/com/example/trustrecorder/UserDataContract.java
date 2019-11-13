package com.example.trustrecorder;

import android.provider.BaseColumns;

public class UserDataContract {

    private UserDataContract(){}

    public static class TrustData implements BaseColumns {
        public static final String TABLE_NAME =  "ChangeData";
        public static final String COLUMN_NAME_ID =  "Id";
        public static final String COLUMN_NAME_TIMESTAMP =  "Timestamp";
        public static final String COLUMN_NAME_TRUSTCHANGESCORE = "TrustCScore";
        public static final String COLUMN_NAME_TRUSTLEVELSCORE = "TrustLScore";
        public static final String COLUMN_NAME_TRUSTLEVELTYPE = "TrustLType";
    }


}

