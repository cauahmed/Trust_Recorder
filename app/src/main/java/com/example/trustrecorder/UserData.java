package com.example.trustrecorder;

public class UserData {

    // fields
    private int userID;
    private String userName;
    // constructors
    public UserData() {}
    public UserData(int userID, String userName) {
        this.userID = userID;
        this.userName = userName;
    }
    // properties
    public void setID(int userID) {
        this.userID = userID;
    }
    public int getID() {
        return this.userID;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserName() {
        return this.userName;
    }
}

