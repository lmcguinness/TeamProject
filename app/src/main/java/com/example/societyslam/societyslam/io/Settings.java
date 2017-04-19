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


    /**
     * Gets the value of music volume
     *
     * @param key
     * @return The value of volume value, it's default value is 5
     */
    public int getVolume(String key) {
        return pref.getInt(key, 5);
    }

    /**
     * Increases the value of music volume
     *
     * @param string
     * @return The value of volume value, it's default value is 5
     */
    public int increaseVolume(String string) {
        int value = pref.getInt(string, 5);
        edit.putInt(string, value += 1);
        edit.commit();
        return pref.getInt(string, 5);
    }

    /**
     * Decreases the value of music volume
     *
     * @param string
     * @return The value of volume value, it's default value is 5
     */
    public int decreaseVolume(String string) {
        int value = pref.getInt(string, 5);
        edit.putInt(string, value = value - 1);
        edit.commit();
        return pref.getInt(string, 5);
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
