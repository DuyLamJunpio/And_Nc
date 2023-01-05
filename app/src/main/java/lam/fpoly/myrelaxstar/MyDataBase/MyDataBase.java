package lam.fpoly.myrelaxstar.MyDataBase;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import lam.fpoly.myrelaxstar.Interface.Tintuc_DAO;
import lam.fpoly.myrelaxstar.Interface.userDAO;
import lam.fpoly.myrelaxstar.Object.TinTucRoom;
import lam.fpoly.myrelaxstar.Object.User;


@Database(entities = {TinTucRoom.class} , version = 1)
public abstract class MyDataBase extends RoomDatabase {
    public static final String NAME_DATABASE = "tintuc.db";
    public static MyDataBase instance;

    public static synchronized MyDataBase getInstance(Context context){

        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), MyDataBase.class, NAME_DATABASE).
                    allowMainThreadQueries().build();
        }
        return instance;
    }

    public abstract Tintuc_DAO tintuc_dao();
}
