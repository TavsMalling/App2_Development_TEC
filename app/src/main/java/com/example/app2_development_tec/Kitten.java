package com.example.app2_development_tec;

public class Kitten {
    String title;
    String description;
    int kittenImage;

    public Kitten(String title, String description, int kittenImage) {
        this.title = title;
        this.description = description;
        this.kittenImage = kittenImage;
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

    public int getKittenImage() {
        return kittenImage;
    }

    public void setKittenImage(int kittenImage) {
        this.kittenImage = kittenImage;
    }
}
