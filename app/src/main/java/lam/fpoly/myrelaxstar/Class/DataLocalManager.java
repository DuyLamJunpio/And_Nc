package lam.fpoly.myrelaxstar.Class;

import android.content.Context;

public class DataLocalManager {
    private static final String PREF_FIRST_INSTALL = "PREF_FIRST_INSTALL";
    private static DataLocalManager instance;
    private MySharedPreference mySharedPreference;

    public static void init(Context context){
        instance = new DataLocalManager();
        instance.mySharedPreference = new MySharedPreference(context);
    }

    private static DataLocalManager getInstance(){
        if (instance == null){
            instance = new DataLocalManager();
        }
        return instance;
    }

    public static void setFirstInstaller(boolean isFirst){
        DataLocalManager.getInstance().mySharedPreference.putBooleanValue(PREF_FIRST_INSTALL,isFirst);
    }

    public static boolean getFirstInstaller(){
        return DataLocalManager.getInstance().mySharedPreference.getBooleanValue(PREF_FIRST_INSTALL);
    }
}
