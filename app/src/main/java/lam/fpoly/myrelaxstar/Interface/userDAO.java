package lam.fpoly.myrelaxstar.Interface;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import lam.fpoly.myrelaxstar.Object.TinTucRoom;
import lam.fpoly.myrelaxstar.Object.User;

@Dao
public interface userDAO {
    @Insert
    void insertDataUser(User user);

    @Query("SELECT * FROM user")
    List<User> getListUser();

    @Query("SELECT * FROM user where name = :name")
    List<User> checkDataUser(String name);

    @Query("SELECT * FROM user where name = :name and pass = :pass")
    List<User> checkDataUserPass(String name,String pass);

}
