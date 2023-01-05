package lam.fpoly.myrelaxstar.Interface;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import lam.fpoly.myrelaxstar.Object.InputUser;


@Dao
public interface inputUser_DAO {

    @Insert
    void insertData(InputUser inputUser);

    @Query("SELECT * FROM input_user")
    List<InputUser> getListInputUser();

    @Query("SELECT * FROM input_user")
    InputUser getUser();

    @Update
    void updateData(InputUser inputUser);

    @Query("DELETE FROM input_user")
    void deleteData();
}