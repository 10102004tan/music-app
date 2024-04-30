package com.example.musicplayer.Adapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.musicplayer.Fragment.LyricsDetailsFragment;
import com.example.musicplayer.Fragment.MainDetailsFragment;
import com.example.musicplayer.Fragment.MoreDetailsFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {

    private Bundle bundle;
    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity,Bundle bundle) {
        super(fragmentActivity);
        this.bundle = bundle;
    }

    public ViewPagerAdapter(@NonNull Fragment fragment,Bundle bundle) {
        super(fragment);
        this.bundle = bundle;
    }

    public ViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle,Bundle bundle) {
        super(fragmentManager, lifecycle);
        this.bundle = bundle;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0 : return new MoreDetailsFragment();

            case 1 : {
                MainDetailsFragment mainDetailsFragment = new MainDetailsFragment();
                mainDetailsFragment.setArguments(bundle);
                return mainDetailsFragment;
            }

            case 2 : return new LyricsDetailsFragment();
        }

        return null;
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
