package lam.fpoly.myrelaxstar.Interface;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import lam.fpoly.myrelaxstar.Object.TinTucRoom;
import lam.fpoly.myrelaxstar.Object.TinTucRoom;

@Dao
public interface Tintuc_DAO {

    @Insert
    void insertData(TinTucRoom tinTucRoom);

    @Query("SELECT * FROM tintuc where username = :name")
    List<TinTucRoom> getList(String name);

    @Query("SELECT * FROM tintuc where title = :name")
    List<TinTucRoom> checkData(String name);

    @Delete
    void deleteData(TinTucRoom tinTucRoom);

    @Query("SELECT * FROM tintuc WHERE title LIKE '%' || :name || '%'")
    List<TinTucRoom> searchData(String name);

}