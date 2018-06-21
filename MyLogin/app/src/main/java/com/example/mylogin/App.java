package com.example.mylogin;

import android.app.Application;
import android.util.Log;

import com.example.mylogin.data.DataManager;
import com.example.mylogin.data.SharedPrefsHelper;

public class App extends Application {

    DataManager dataManager;

    @Override
    public void onCreate() {
        super.onCreate();

        SharedPrefsHelper sharedPrefsHelper = new SharedPrefsHelper(getApplicationContext());
        dataManager = new DataManager(sharedPrefsHelper);
    }

    public DataManager getDataManager() {
        return dataManager;
    }
}
