package com.example.oizbyakova.myapplication;

import android.app.Application;


public class BaseApplication extends Application {

    private static BaseApplication instance;

    public BaseApplication() {
        instance = this;
    }

    public static BaseApplication getInstance() {
        return instance;
    }
}
