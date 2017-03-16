package com.example.societyslam.societyslam.io;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Leanne on 13/03/2017.
 */

public class Settings {


    // Declaring Variables
    private SharedPreferences.Editor edit;
    private SharedPreferences pref;


    public Settings(Context context) {
        pref = PreferenceManager.getDefaultSharedPreferences(context);
        edit = pref.edit();
    }


    public int getVolume(String key) {
        return pref.getInt(key, 5);
    }


    public String getLanguage() {
        return pref.getString("languageString", "en");
    }

    public String setLanguage(String value) {
        edit.putString("languageString", value);
        edit.commit();
        return pref.getString("languageString", "en");
    }



}
