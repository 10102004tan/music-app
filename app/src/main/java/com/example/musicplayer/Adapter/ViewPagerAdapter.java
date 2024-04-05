package com.example.musicplayer.Adapter;

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
    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public ViewPagerAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    public ViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0 : return new MoreDetailsFragment();

            case 1 : return new MainDetailsFragment();

            case 2 : return new LyricsDetailsFragment();
        }

        return null;
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
