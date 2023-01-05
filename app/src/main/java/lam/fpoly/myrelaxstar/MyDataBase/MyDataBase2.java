package lam.fpoly.myrelaxstar.MyDataBase;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import lam.fpoly.myrelaxstar.Interface.Musical_DAO;
import lam.fpoly.myrelaxstar.Object.AudioObject;



@Database(entities = {AudioObject.class} , version = 1)
public abstract class MyDataBase2 extends RoomDatabase {
    public static final String NAME_DATABASE = "musical.db";
    public static MyDataBase2 instance;

    public static synchronized MyDataBase2 getInstance(Context context){

        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), MyDataBase2.class, NAME_DATABASE).
                    allowMainThreadQueries().build();
        }
        return instance;
    }

    public abstract Musical_DAO musical_dao();
}
