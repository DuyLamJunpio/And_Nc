package lam.fpoly.myrelaxstar.MyDataBase;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import lam.fpoly.myrelaxstar.Interface.userDAO;
import lam.fpoly.myrelaxstar.Object.User;


@Database(entities = {User.class} , version = 1)
public abstract class MyDataBase1 extends RoomDatabase {
    public static final String NAME_DATABASE = "user.db";
    public static MyDataBase1 instance;

    public static synchronized MyDataBase1 getInstance(Context context){

        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), MyDataBase1.class, NAME_DATABASE).
                    allowMainThreadQueries().build();
        }
        return instance;
    }

    public abstract userDAO userDAO();
}
