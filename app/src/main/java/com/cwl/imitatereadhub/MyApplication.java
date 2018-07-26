package com.cwl.imitatereadhub;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {
    private static MyApplication mInstance;

    public static Context getAppContext(){
        return mInstance.getApplicationContext();
    }
    public static MyApplication getmInstance(){
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance=this;
    }
}
