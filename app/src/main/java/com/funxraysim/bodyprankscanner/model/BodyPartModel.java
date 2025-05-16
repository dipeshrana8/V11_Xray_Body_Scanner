package com.funxraysim.bodyprankscanner.model;

public class BodyPartModel {
    private String title;
    private int imageResId;
    private String description;

    public BodyPartModel(String title, int imageResId, String description) {
        this.title = title;
        this.imageResId = imageResId;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getDescription() {
        return description;
    }
}
