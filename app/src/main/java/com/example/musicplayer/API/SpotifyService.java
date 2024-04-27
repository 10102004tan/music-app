package com.example.musicplayer.API;

import com.example.musicplayer.Models.SpotifyResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SpotifyService {
    static final String BASE_URL = "https://api.spotify.com/v1/";
    @GET("v1/albums/{id}/tracks")
    Call<SpotifyResponse> listTracks(@Query("id") String id);
}
