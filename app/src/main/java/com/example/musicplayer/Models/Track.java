package com.example.musicplayer.Models;

public class Track {

    private String urlImage;
    private String name;
    private String preview_url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPreview_url() {
        return preview_url;
    }

    public void setPreview_url(String preview_url) {
        this.preview_url = preview_url;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public Track(String urlImage, String name, String preview_url) {
        this.urlImage = urlImage;
        this.name = name;
        this.preview_url = preview_url;
    }
}

