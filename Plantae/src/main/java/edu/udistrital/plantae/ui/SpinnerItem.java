package edu.udistrital.plantae.ui;

/**
 * Created by Gerson Sosa on 4/23/14.
 */
public class SpinnerItem {
    private Long id;

    private String title;

    public SpinnerItem(Long id, String title) {
        this.id = id;
        this.title = title;
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

    @Override
    public String toString() {
        return title;
    }
}
