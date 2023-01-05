package lam.fpoly.myrelaxstar.Interface;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

import lam.fpoly.myrelaxstar.Object.AudioObject;
import lam.fpoly.myrelaxstar.Object.User;

@Dao
public interface Musical_DAO {

    @Insert
    void insertData(AudioObject audioObject);

    @Query("SELECT * FROM song where username = :name")
    List<AudioObject> getList(String name);

    @Query("SELECT * FROM song where title = :name")
    List<AudioObject> checkData(String name);

    @Query("SELECT * FROM song where imgTym = :img and username = :name")
    List<AudioObject> checkDataTym(int img , String name);

    @Update
    void update(AudioObject audioObject);

    @Delete
    void delete(AudioObject audioObject);

    @Query("DELETE FROM song where title = :name")
    void deleteTitle(String name);
}
