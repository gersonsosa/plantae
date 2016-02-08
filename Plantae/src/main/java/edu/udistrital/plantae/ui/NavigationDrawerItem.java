package edu.udistrital.plantae.ui;

/**
 * Created by Gerson Sosa on 4/10/14.
 */
public class NavigationDrawerItem {
    private Long id;
    private String name;
    private int image;
    private int count;

    public NavigationDrawerItem(Long id, String name, int image, int count) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.count = count;
    }

    public NavigationDrawerItem(String name, int image, int count) {
        this.name = name;
        this.image = image;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
