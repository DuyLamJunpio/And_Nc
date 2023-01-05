package lam.fpoly.myrelaxstar.Object;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "input_user")
public class InputUser {
    @PrimaryKey(autoGenerate = false)
    int id;
    String name;
    String pass;
    Long avt;

    public InputUser(String name, String pass, Long avt) {
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
}
