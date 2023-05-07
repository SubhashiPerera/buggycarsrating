package com.buggycarsrating.qa.entities;

public class User {

    private String uName;
    private String fName;
    private String lName;
    private String pWord;


    public String getFName() {
        return fName;
    }

    public void setFName(String firstName) {
        this.fName = firstName;
    }


    public String getLName() {
        return lName;
    }


    public void setLName(String lastName) {
        this.lName = lastName;
    }


    public String getUName() {
        return uName;
    }


    public void setUName(String newUsername) {
        this.uName = newUsername;
    }


    public String getPWord() {
        return pWord;
    }


    public void setPWord(String newPassword) {
        this.pWord = newPassword;
    }

}
