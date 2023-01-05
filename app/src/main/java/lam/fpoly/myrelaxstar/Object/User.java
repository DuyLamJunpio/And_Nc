package lam.fpoly.myrelaxstar.Object;


import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import lam.fpoly.myrelaxstar.MyDataBase.MyDataBase;
import lam.fpoly.myrelaxstar.MyDataBase.MyDataBase1;

@Entity(tableName = "user")
public class User implements Serializable {
    @PrimaryKey(autoGenerate = true)
    int id;
    String name;
    String pass;
    Long avt;


    public User(String name, String pass, Long avt) {
        this.name = name;
        this.pass = pass;
        this.avt = avt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Long getAvt() {
        return avt;
    }

    public void setAvt(Long avt) {
        this.avt = avt;
    }

    public boolean isCheckUserName(Context context){
        List<User> list = new ArrayList<>();
        list = MyDataBase1.getInstance(context).userDAO().getListUser();
        for(int i = 0; i < list.size() ; i++){
            if(!TextUtils.isEmpty(name) && name.equals(list.get(i).getName())){
                return true;
            }
        }
        return false;
    }

    public boolean isCheckPassWord(Context context){
        List<User> list1 = new ArrayList<>();
        list1 = MyDataBase1.getInstance(context).userDAO().getListUser();
        for(int i = 0; i < list1.size() ; i++){
            if(!TextUtils.isEmpty(pass) && pass.equals(list1.get(i).getPass())){
                return true;
            }
        }
        return false;
    }

}


