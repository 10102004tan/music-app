package com.example.musicplayer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.musicplayer.Adapter.ViewPagerAdapter;
import com.example.musicplayer.Models.Track;
import com.example.musicplayer.MyLib.PlayerMore;
import com.example.musicplayer.databinding.PlayerMusicLayoutBinding;

public class PlayerMusicActivity extends AppCompatActivity {

    private PlayerMusicLayoutBinding binding;
    private Track track;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        Intent intent = getIntent();
        if(intent!=null){
            track = (Track) intent.getSerializableExtra("track");
            Bundle bundle = new Bundle();
            bundle.putSerializable("track",track);

            ViewPagerAdapter adapter = new ViewPagerAdapter(this,bundle);
            binding.fragmentlayout.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
            binding.fragmentlayout.setAdapter(adapter);
            binding.fragmentlayout.setCurrentItem(1);


            PlayerMore playerMore = findViewById(R.id.playerMore);
        }


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}