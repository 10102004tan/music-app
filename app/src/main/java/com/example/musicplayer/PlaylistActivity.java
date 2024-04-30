package com.example.musicplayer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

import com.example.musicplayer.API.SpotifyService;
import com.example.musicplayer.Adapter.MusicAdapter;
import com.example.musicplayer.Models.SpotifyResponse;
import com.example.musicplayer.Models.Track;
import com.example.musicplayer.Service.MusicService;
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
    private static final int MY_PERMISSIONS_REQUEST_READ_MEDIA_AUDIO = 1;
    private List<Track> trackList;
    private View preView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        playlistLayoutBinding = PlaylistLayoutBinding.inflate(getLayoutInflater());
        setContentView(playlistLayoutBinding.getRoot());

        setSupportActionBar(playlistLayoutBinding.toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_MEDIA_AUDIO)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_MEDIA_AUDIO},
                    MY_PERMISSIONS_REQUEST_READ_MEDIA_AUDIO);
        }

        trackList = getAllAudioFromDevice(this);
        MusicAdapter musicAdapter = new MusicAdapter(trackList);
        playlistLayoutBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));



        // ON CLICK LISTENER
        playlistLayoutBinding.recyclerView.setAdapter(musicAdapter);
        musicAdapter.setiClickMusicItem(new MusicAdapter.IClickMusicItem() {
            @Override
            public void onMusicItemClicked(Track track, View view) {
                Intent intent = new Intent(PlaylistActivity.this, PlayerMusicActivity.class);
                intent.putExtra("track", track);
                if(preView != view){
                    stopService(new Intent(PlaylistActivity.this, MusicService.class));
                    preView = view;
                }
                startActivity(intent);


            }
        });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_MEDIA_AUDIO: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission was granted
                    // You can now read audio files
                } else {
                    // Permission was denied

                }
                return;
            }

        }
    }



    private ArrayList<Track> getAllAudioFromDevice(final Context context) {
        final ArrayList<Track> tempAudioList = new ArrayList<>();
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String[] projection = { MediaStore.Audio.AudioColumns.DATA, MediaStore.Audio.AudioColumns.TITLE, MediaStore.Audio.ArtistColumns.ARTIST };
        Cursor c = context.getContentResolver().query(uri, projection, null, null, null);

        if (c != null) {
            while (c.moveToNext()) {
                Track track = new Track();
                String path = c.getString(0);
                String name = c.getString(1);
                String artist = c.getString(2);
                track.setPath(path);
                track.setName(name);
                track.setArtist(artist);
                tempAudioList.add(track);
            }
            c.close();
        }

        return tempAudioList;
    }
}