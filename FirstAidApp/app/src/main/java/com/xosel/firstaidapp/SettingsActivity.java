package com.xosel.firstaidapp;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Xochitl on 05/03/2016.
 */
public class SettingsActivity extends AppCompatActivity {

    public static final String FAA_PREFS = "firstaidapp_preferences.xml";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getApplicationContext();
    }

    public void saveNumberFamiliar(String number){
        Context context = MyApp.getContext();
        SharedPreferences settings = context.getSharedPreferences(FAA_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("numberPhone", number);
        editor.commit();
    }

    public String getNumberFamiliar(){
        Context context = MyApp.getContext();
        SharedPreferences settings = context.getSharedPreferences(FAA_PREFS,MODE_PRIVATE);
        return (settings.getString("numberPhone", ""));
    }
}
