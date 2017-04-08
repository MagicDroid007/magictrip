package com.magicdroid.magictrip.databases;

import android.content.Context;
import android.content.SharedPreferences;
import com.magicdroid.magictrip.MagicTripApp;

/**
 * Created by magic on 08/04/17.
 */
public class PrefDataHandler {
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;
    private PrefDataHandler() {
        sharedPref = MagicTripApp.getContext().getSharedPreferences("gift_grab", Context.MODE_PRIVATE);
        editor = sharedPref.edit();
    }
    private static class SingletonHelper{
        private static final PrefDataHandler INSTANCE = new PrefDataHandler();
    }
    public static PrefDataHandler getInstance(){
        return SingletonHelper.INSTANCE;
    }
    public SharedPreferences.Editor getEditor() {
        return editor;
    }
    public SharedPreferences getSharedPref() {
        return sharedPref;
    }
}
