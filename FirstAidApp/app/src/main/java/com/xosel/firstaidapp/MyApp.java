package com.xosel.firstaidapp;

import android.app.Application;
import android.content.Context;

/**
 * Created by Xochitl on 05/03/2016.
 */
public class MyApp extends Application {
    private static MyApp instance;
    private static Context mContext;

    public static MyApp getInstance(){
        return instance;
    }

    public static Context getContext(){
        return mContext;
    }

    public static void setContext(Context context){
        mContext = context;
    }

    @Override
    public void onCreate(){
        super.onCreate();
        this.instance = this;
        this.mContext = this.instance.getApplicationContext();
    }
}