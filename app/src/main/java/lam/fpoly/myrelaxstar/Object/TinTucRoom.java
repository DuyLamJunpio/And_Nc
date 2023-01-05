package lam.fpoly.myrelaxstar.Object;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "tintuc")
public class TinTucRoom implements Serializable {
    @PrimaryKey(autoGenerate = true)
    int id;
    String username;
    String title;
    String description;
    String link;
    String img;

    public TinTucRoom(String username, String title, String description, String link, String img) {
        this.username = username;
        this.title = title;
        this.description = description;
        this.link = link;
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
