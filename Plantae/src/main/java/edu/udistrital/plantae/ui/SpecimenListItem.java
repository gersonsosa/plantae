package edu.udistrital.plantae.ui;

/**
 * Created by Gerson Sosa on 4/23/14.
 */
public class SpecimenListItem {
    private Long id;
    private String specimenTitle;
    private String scientificName;
    private String specimenLocality;
    private String specimenDescription;
    private int specimenImage;
    private String imagePath;
    private boolean isLocated;
    private boolean isChecked;

    public SpecimenListItem(Long id, String specimenTitle, String scientificName, String specimenLocality, String specimenDescription, int specimenImage, boolean isLocated) {
        this.id = id;
        this.specimenTitle = specimenTitle;
        this.scientificName = scientificName;
        this.specimenLocality = specimenLocality;
        this.specimenDescription = specimenDescription;
        this.specimenImage = specimenImage;
        this.isLocated = isLocated;
    }

    public SpecimenListItem(Long id, String specimenTitle, String scientificName, String specimenLocality, String specimenDescription, String imagePath, boolean isLocated) {
        this.id = id;
        this.specimenTitle = specimenTitle;
        this.scientificName = scientificName;
        this.specimenLocality = specimenLocality;
        this.specimenDescription = specimenDescription;
        this.imagePath = imagePath;
        this.isLocated = isLocated;
    }

    public SpecimenListItem(Long id, String specimenTitle, String imagePath, boolean isLocated) {
        this.id = id;
        this.specimenTitle = specimenTitle;
        this.imagePath = imagePath;
        this.isLocated = isLocated;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSpecimenTitle() {
        return specimenTitle;
    }

    public void setSpecimenTitle(String specimenTitle) {
        this.specimenTitle = specimenTitle;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public String getSpecimenLocality() {
        return specimenLocality;
    }

    public void setSpecimenLocality(String specimenLocality) {
        this.specimenLocality = specimenLocality;
    }

    public String getSpecimenDescription() {
        return specimenDescription;
    }

    public void setSpecimenDescription(String specimenDescription) {
        this.specimenDescription = specimenDescription;
    }

    public int getSpecimenImage() {
        return specimenImage;
    }

    public void setSpecimenImage(int specimenImage) {
        this.specimenImage = specimenImage;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
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
