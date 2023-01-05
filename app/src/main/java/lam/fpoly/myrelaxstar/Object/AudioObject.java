package lam.fpoly.myrelaxstar.Object;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "song")
public class AudioObject implements Serializable {
    @PrimaryKey(autoGenerate = true)
    int id;
    String username;
    String path;
    String title;
    String duration;
    String Artist;
    Long idImg;
    int imgTym;

    public AudioObject() {
    }

    public AudioObject(String username, String path, String title, String duration, String artist, Long idImg, int imgTym) {
        this.username = username;
        this.path = path;
        this.title = title;
        this.duration = duration;
        Artist = artist;
        this.idImg = idImg;
        this.imgTym = imgTym;
    }

    public int getImgTym() {
        return imgTym;
    }

    public void setImgTym(int imgTym) {
        this.imgTym = imgTym;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getArtist() {
        return Artist;
    }

    public void setArtist(String artist) {
        Artist = artist;
    }

    public Long getIdImg() {
        return idImg;
    }

    public void setIdImg(Long idImg) {
        this.idImg = idImg;
    }
}
