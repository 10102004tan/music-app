package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.musicplayer.Adapter.ViewPagerAdapter;
import com.example.musicplayer.Fragment.MainDetailsFragment;
import com.example.musicplayer.MyLib.PlayerMore;
import com.example.musicplayer.databinding.DetailsLayoutBinding;

public class MainActivity extends AppCompatActivity {

    private DetailsLayoutBinding detailsLayoutBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        detailsLayoutBinding =DetailsLayoutBinding.inflate(getLayoutInflater());
        setContentView(detailsLayoutBinding.getRoot());

        setSupportActionBar(detailsLayoutBinding.toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ViewPagerAdapter adapter = new ViewPagerAdapter(this);
        detailsLayoutBinding.fragmentlayout.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        detailsLayoutBinding.fragmentlayout.setCurrentItem(1);
        detailsLayoutBinding.fragmentlayout.setAdapter(adapter);



        PlayerMore playerMore = findViewById(R.id.playerMore);
        playerMore.setiClick(new PlayerMore.IClick() {
            @Override
            public void heart(View v) {
                Toast.makeText(MainActivity.this, "heart", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void playlist(View v) {
                Toast.makeText(MainActivity.this, "playlist", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void random(View v) {
                Toast.makeText(MainActivity.this, "random", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void replaceLayout(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentlayout,fragment);
        transaction.commit();
    }
}