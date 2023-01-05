package lam.fpoly.myrelaxstar.MyDataBase;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import lam.fpoly.myrelaxstar.Interface.inputUser_DAO;
import lam.fpoly.myrelaxstar.Object.InputUser;


@Database(entities = {InputUser.class} , version = 1)
public abstract class MyDataBase3 extends RoomDatabase {
    public static final String NAME_DATABASE = "input.db";
    public static MyDataBase3 instance;

    public static synchronized MyDataBase3 getInstance(Context context){

        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), MyDataBase3.class, NAME_DATABASE).
                    allowMainThreadQueries().build();
        }
        return instance;
    }

    public abstract inputUser_DAO inputUser_dao();
}
