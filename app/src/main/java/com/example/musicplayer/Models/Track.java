package com.example.musicplayer.Models;

import java.io.Serializable;

public class Track implements Serializable {
    private String path;
    private String name;
    private String artist;

    // getters and setters

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }
}

