package lam.fpoly.myrelaxstar.Object;

import java.io.Serializable;

public class Object_Photo implements Serializable {
    private String resourceId;
    private String link;
    private String title;

    public Object_Photo(String resourceId, String link, String title) {
        this.resourceId = resourceId;
        this.link = link;
        this.title = title;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
