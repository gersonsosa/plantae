package edu.udistrital.plantae;

/**
 * Created by Gerson Sosa on 4/23/14.
 */
public class ListItem {
    private Long id;
    private String title;
    private String descriptionText;
    private int image;
    private String subitemCount;
    private boolean isLocated;
    public ListItem(Long id, String title, String descriptionText, int image, String subitemCount, boolean isLocated) {
        this.id = id;
        this.title = title;
        this.descriptionText = descriptionText;
        this.image = image;
        this.subitemCount = subitemCount;
        this.isLocated = isLocated;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescriptionText() {
        return descriptionText;
    }

    public void setDescriptionText(String descriptionText) {
        this.descriptionText = descriptionText;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getSubitemCount() {
        return subitemCount;
    }

    public void setSubitemCount(String subitemCount) {
        this.subitemCount = subitemCount;
    }

    public boolean isLocated() {
        return isLocated;
    }

    public void setLocated(boolean isLocated) {
        this.isLocated = isLocated;
    }
}
