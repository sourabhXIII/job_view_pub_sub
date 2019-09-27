package com.phenom.bean;

import java.time.LocalDateTime;

public class Users {
    private String UserName;
    private String FName;
    private String LName;

//    TODO: We need to capture the date time timezone of the searches along with the search keys.
//    https://www.geeksforgeeks.org/new-date-time-api-java8/
//    private LocalDateTime[] dateTime;
//    private String[] key_words;


    public Users(){}

    public String getUserName() { return this.UserName; }
    public String getFName() { return this.LName; }
    public String getLName() { return this.LName; }
    public String getFullName() { return this.FName + " " + this.LName; }
}