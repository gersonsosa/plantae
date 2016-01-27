package edu.udistrital.plantae.ui;


/**
 * Created by hghar on 11/26/14.
 */
public class PictureItem {

    private Long id;
    private String context;
    private String imagePath;

    public PictureItem(Long id, String context, String imagePath) {
        this.id = id;
        this.context = context;
        this.imagePath = imagePath;
    }

    public PictureItem(String context, String imagePath) {
        this.id = id;
        this.context = context;
        this.imagePath = imagePath;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
