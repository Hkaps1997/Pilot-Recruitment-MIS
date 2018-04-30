package com.example.harshit.mymis;

/**
 * Created by HARSHIT on 22-07-2017.
 */

public class User {

    String fName,lName,state,gen,cat,mob,email,height,bmi,region,grad,userID;

    public User(String fName, String lName, String state, String gen, String cat, String mob,String email, String height,String bmi , String region, String grad, String userID) {
        this.fName = fName;
        this.lName = lName;
        this.state = state;
        this.gen = gen;
        this.cat = cat;
        this.mob = mob;
        this.height = height;
        this.email = email;
        this.region = region;
        this.bmi = bmi;
        this.grad = grad;
        this.userID = userID;
    }

    public String getGrad() {
        return grad;
    }

    public String getUserID() {
        return userID;
    }

    public String getRegion() {
        return region;
    }

    public String getBmi() {
        return bmi;
    }

    public String getHeight() {
        return height;
    }

    public String getEmail() {
        return email;
    }

    public String getMob() {
        return mob;
    }

    public String getCat() {
        return cat;
    }

    public String getState() {
        return state;
    }

    public String getGen() {
        return gen;
    }

    public String getlName() {
        return lName;
    }

    public String getfName() {
        return fName;
    }
}
