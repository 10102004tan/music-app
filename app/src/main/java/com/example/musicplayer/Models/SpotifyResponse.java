package com.example.musicplayer.Models;

import java.util.List;

public class SpotifyResponse {
    private List<Track> tracks;

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }
}
