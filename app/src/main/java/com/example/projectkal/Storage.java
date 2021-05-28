package com.example.projectkal;

import android.content.Context;
import android.content.SharedPreferences;

public class Storage {
    public static final String STORAGE_NAME = "StorageName";
    // keys - topScore, savedLevel

    private static SharedPreferences settings = null;
    private static SharedPreferences.Editor editor = null;
    private static Context context = null;

    public static void init( Context cntxt ){
        context = cntxt;
    }

    public static void init(){
        settings = context.getSharedPreferences(STORAGE_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();
    }

    public static void update( String name, int value){
        if ( settings == null ) init();

        editor.putInt( name, value );
        editor.apply();
    }

    public static int load( String name ){
        if ( settings == null) init();
        return settings.getInt( name, 0);
    }
}
