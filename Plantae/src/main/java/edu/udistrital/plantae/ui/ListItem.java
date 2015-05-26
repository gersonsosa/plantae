package edu.udistrital.plantae.ui;

/**
 * Created by Gerson Sosa on 4/23/14.
 */
public class ListItem {
    private Long id;
    private String title;
    private String superTitle;
    private String descriptionText;
    private int image;
    private String imagePath;
    private String subitemCount;
    private boolean isLocated;
    private boolean isChecked;

    public ListItem(Long id, String title, String superTitle) {
        this.id = id;
        this.title = title;
        this.superTitle = superTitle;
    }

    public ListItem(Long id, String title, String descriptionText, int image, String subitemCount, boolean isLocated) {
        this.id = id;
        this.title = title;
        this.descriptionText = descriptionText;
        this.image = image;
        this.subitemCount = subitemCount;
        this.isLocated = isLocated;
    }

    public ListItem(Long id, String title, String descriptionText, String imagePath, boolean isLocated) {
        this.id = id;
        this.title = title;
        this.descriptionText = descriptionText;
        this.imagePath = imagePath;
        this.isLocated = isLocated;
    }

    @Override
    public String toString() {
        return title;
    }

    public ListItem(Long id, String title, String descriptionText, String subitemCount, boolean isLocated, boolean isChecked) {
        this.id = id;
        this.title = title;
        this.descriptionText = descriptionText;
        this.subitemCount = subitemCount;
        this.isLocated = isLocated;
        this.isChecked = isChecked;
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

    public String getSuperTitle() {
        return superTitle;
    }

    public void setSuperTitle(String superTitle) {
        this.superTitle = superTitle;
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

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }
}
