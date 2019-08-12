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
        public static final String COLUMN_NAME_ID =  "Id";//add by haiyang
        public static final String COLUMN_NAME_TIMESTAMP =  "Timestamp";
        public static final String COLUMN_NAME_TRUSTSCORE = "TrustScore";
        public static final String COLUMN_NAME_TRUSTLEVEL = "TrustLevel";
        public static final String COLUMN_NAME_UP_OR_DOWN = "TrustLevelStatus";//this column is used to recorder which button has been clicked, the value should be 1 if the user press the "+" button and the value should be 2 if the user press the "-" button

    }


}

