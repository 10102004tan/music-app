package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;

import com.example.musicplayer.API.SpotifyService;
import com.example.musicplayer.Adapter.MusicAdapter;
import com.example.musicplayer.Models.SpotifyResponse;
import com.example.musicplayer.Models.Track;
import com.example.musicplayer.databinding.PlaylistLayoutBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PlaylistActivity extends AppCompatActivity {

    private PlaylistLayoutBinding playlistLayoutBinding;
    private List<Track> trackList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        playlistLayoutBinding = PlaylistLayoutBinding.inflate(getLayoutInflater());
        setContentView(playlistLayoutBinding.getRoot());

        setSupportActionBar(playlistLayoutBinding.toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

//        MusicAdapter musicAdapter = new MusicAdapter(trackList);
//        playlistLayoutBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
//        playlistLayoutBinding.recyclerView.setAdapter(musicAdapter);

    }


    private void initData() {
        trackList = new ArrayList<>();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SpotifyService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SpotifyService service = retrofit.create(SpotifyService.class);
        Call<SpotifyResponse> call =service.listTracks("37i9dQZF1DXcBWIGoYBM5M");
        call.enqueue(new Callback<SpotifyResponse>() {
            @Override
            public void onResponse(Call<SpotifyResponse> call, Response<SpotifyResponse> response) {
                if (response.isSuccessful()){
                    SpotifyResponse spotifyResponse = response.body();
                    trackList = spotifyResponse.getTracks();
                    Log.d("TAG", "onResponse: "+trackList.size());
                }
            }

            @Override
            public void onFailure(Call<SpotifyResponse> call, Throwable t) {

            }
        });

    }
}