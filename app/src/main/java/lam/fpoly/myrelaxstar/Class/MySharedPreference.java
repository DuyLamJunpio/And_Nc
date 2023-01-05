package lam.fpoly.myrelaxstar.Class;

import android.content.Context;
import android.content.SharedPreferences;

public class MySharedPreference {
    public static final String My_SHARED_PREFERENCE = "My_SHARED_PREFERENCE";
    private Context context;

    public MySharedPreference(Context context) {
        this.context = context;
    }

    public void putBooleanValue(String key,boolean value){
        SharedPreferences sharedPreferences = context.getSharedPreferences(My_SHARED_PREFERENCE
                ,context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key,value);
        editor.apply();
    }

    public boolean getBooleanValue(String key){
        SharedPreferences sharedPreferences = context.getSharedPreferences(My_SHARED_PREFERENCE
                ,context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key,false);
    }
}
