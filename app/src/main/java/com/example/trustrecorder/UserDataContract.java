package com.example.trustrecorder;

import android.provider.BaseColumns;

public class UserDataContract {

    private UserDataContract(){}
    // I think each test should have an ID, and the recorder of the test should be identifed
    //by the ID. so, there will be a series of recorders in the data base which has same ID, and these recorders
    //together show the status of the whole test.
    //so I change this part but you guys could edit it if you get a better way
    public static class TrustData implements BaseColumns {
        public static final String TABLE_NAME =  "ChangeData";
        public static final String COLUMN_NAME_ID =  "Id";
        public static final String COLUMN_NAME_TRUSTCHANGESCORE = "TrustCScore";
        public static final String COLUMN_NAME_TRUSTLEVELSCORE = "TrustLScore";
        public static final String COLUMN_NAME_TRUSTLEVELTYPE = "TrustLType";
    }


}

