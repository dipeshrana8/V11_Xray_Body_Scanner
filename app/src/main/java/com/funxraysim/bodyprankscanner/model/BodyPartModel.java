package com.funxraysim.bodyprankscanner.model;

public class BodyPartModel {
    private final String title;
    private final int imageResId;
    private final String description;

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
