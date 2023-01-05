package lam.fpoly.myrelaxstar.Object;



import java.io.Serializable;


public class AudioModel implements Serializable {
    String path;
    String title;
    String duration;
    String Artist;
    Long idImg;
    int imgAvt;

    public AudioModel(String path, String title, String duration, String artist, Long idImg, int imgAvt) {
        this.path = path;
        this.title = title;
        this.duration = duration;
        Artist = artist;
        this.idImg = idImg;
        this.imgAvt = imgAvt;
    }

    public int getImgAvt() {
        return imgAvt;
    }

    public void setImgAvt(int imgAvt) {
        this.imgAvt = imgAvt;
    }

    public Long getIdImg() {
        return idImg;
    }

    public void setIdImg(Long idImg) {
        this.idImg = idImg;
    }

    public String getArtist() {
        return Artist;
    }

    public void setArtist(String artist) {
        Artist = artist;
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
}
