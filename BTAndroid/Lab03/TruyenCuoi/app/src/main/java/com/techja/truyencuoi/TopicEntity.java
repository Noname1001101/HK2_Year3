package com.techja.truyencuoi;

public class TopicEntity {
    private String name;
    private String imagePath; // Đổi từ imageResId sang imagePath

    public TopicEntity(String name, String imagePath) {
        this.name = name;
        this.imagePath = imagePath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}