package com.example.musicplayer.Fragment;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.viewmodel.CreationExtras;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import com.example.musicplayer.MyLib.Player;
import com.example.musicplayer.R;
import com.example.musicplayer.databinding.MainDetailsFragmentBinding;

public class MainDetailsFragment extends Fragment{

    private MediaPlayer mediaPlayer;

    private MainDetailsFragmentBinding mainDetailsFragmentBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       mainDetailsFragmentBinding = MainDetailsFragmentBinding.inflate(inflater,container,false);

       Player player = mainDetailsFragmentBinding.player;
       player.setiClick(new Player.IClick() {
           @Override
           public void pause(View v) {
               PlaySong();
           }
       });

       return mainDetailsFragmentBinding.getRoot();
    }



    private void updateSeekbar(){
        int currPos = mediaPlayer.getCurrentPosition();
        mainDetailsFragmentBinding.seekbar.setProgress(currPos);
        final  Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                updateSeekbar();
                handler.postDelayed(this,1000);
            }
        });
    }

    private void PlaySong(){
        mediaPlayer = MediaPlayer.create(getActivity().getApplicationContext(),R.raw.music);
        mainDetailsFragmentBinding.seekbar.setMax(mediaPlayer.getDuration());
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mainDetailsFragmentBinding.seekbar.setMax(mediaPlayer.getDuration());
                mediaPlayer.start();
                updateSeekbar();
            }
        });

        mainDetailsFragmentBinding.seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser){
                    mediaPlayer.seekTo(progress);
                    mainDetailsFragmentBinding.seekbar.setProgress(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}