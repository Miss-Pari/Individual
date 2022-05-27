package com.practical.individual;

import android.widget.EditText;

public class Minerals {
    String english, konkani, pro;


    public Minerals(String id, EditText english, EditText konkani, EditText pro){

    }

    public Minerals(String english, String konkani, String pro){
        this.english = english;
        this.konkani = konkani;
        this.pro = pro;
    }

    public String getEnglish() {
        return english;
    }

    public String getKonkani() {
        return konkani;
    }

    public String getPro() {
        return pro;
    }
}
