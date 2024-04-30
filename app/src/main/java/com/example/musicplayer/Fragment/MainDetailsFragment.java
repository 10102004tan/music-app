package com.example.musicplayer.Fragment;

import android.animation.ObjectAnimator;
import android.content.Intent;
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
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.musicplayer.Models.Track;
import com.example.musicplayer.MyLib.Player;
import com.example.musicplayer.R;
import com.example.musicplayer.Service.MusicService;
import com.example.musicplayer.databinding.MainDetailsFragmentBinding;

import java.util.Objects;

public class MainDetailsFragment extends Fragment{

    private MediaPlayer mediaPlayer;
    private ObjectAnimator discAnimator;
    private boolean isPlaying = true;
    private Track track;

    private MainDetailsFragmentBinding mainDetailsFragmentBinding;
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            mainDetailsFragmentBinding.seekbar.setProgress((int) (((float) mediaPlayer.getCurrentPosition() / mediaPlayer.getDuration()) * 100));
            mainDetailsFragmentBinding.txtMin.setText(String.valueOf(mediaPlayer.getCurrentPosition() / 1000 / 60) + ":" + String.valueOf(mediaPlayer.getCurrentPosition() / 1000 % 60));
            handler.postDelayed(this, 1000);
        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mainDetailsFragmentBinding = MainDetailsFragmentBinding.inflate(inflater, container, false);

        track = (Track) getArguments().getSerializable("track");

        mainDetailsFragmentBinding.nameTxt.setText(track.getName());

        // Init methods
        Player player = mainDetailsFragmentBinding.player;
        mediaPlayer = new MediaPlayer();

        /*DISC ANIMATION PROCESSING*/
        discAnimator = ObjectAnimator.ofFloat(mainDetailsFragmentBinding.circleImageView, "rotation", 0f, 360f);
        Glide.with(this).load(R.drawable.logo_music_logo).into(mainDetailsFragmentBinding.circleImageView);
        discAnimator.setDuration(20000);
        discAnimator.setInterpolator(new LinearInterpolator());
        discAnimator.setRepeatCount(Animation.INFINITE);

        Intent intent = new Intent(getActivity(), MusicService.class);
        intent.putExtra("pathTrack", track.getPath());
        player.setiClick(new Player.IClick() {
            @Override
            public void pause(View v) {
                ImageView imageView = (ImageView) v;
               try {
                   if (isPlaying){
                       intent.setAction("ACTION_PLAY");
                       discAnimator.start();
                       handler.postDelayed(runnable, 1000);
                       isPlaying = false;
                       imageView.setImageResource(R.drawable.ic_pause);
                   }
                   else{
                       intent.setAction("ACTION_PAUSE");
                       discAnimator.pause();
                       handler.removeCallbacks(runnable);
                       isPlaying = true;
                       imageView.setImageResource(R.drawable.ic_play);
                   }

                   requireActivity().startService(intent);
               }
               catch (Exception e){
                   Log.d("Error: ", e.getMessage());
               }

            }
        });



        /*
        SEEKBAR PROCESSING
        */
        mainDetailsFragmentBinding.seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser){
                    int duration = mediaPlayer.getDuration();
                    int seekPosition = (int) ((float) progress / 100 * duration);
                    mediaPlayer.seekTo(seekPosition);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
               //bat su kien keo
                mediaPlayer.pause();
                handler.removeCallbacks(runnable);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.start();
                handler.postDelayed(runnable, 1000);
            }
        });

        return mainDetailsFragmentBinding.getRoot();
    }




    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }

        if (discAnimator != null) {
            discAnimator.cancel();
            discAnimator = null;
        }
    }
}