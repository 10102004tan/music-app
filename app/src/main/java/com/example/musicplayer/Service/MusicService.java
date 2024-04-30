package com.example.musicplayer.Service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.example.musicplayer.PlayerMusicActivity;
import com.example.musicplayer.R;

public class MusicService extends Service {
    private static final int NOTIFICATION_ID = 1111;
    private MediaPlayer mediaPlayer;
    private static final String CHANNEL_ID = "MusicPlayer";
    private Notification notification;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        try {
            String action = intent.getAction();
            String pathTrack = intent.getStringExtra("pathTrack");
            if (mediaPlayer == null) {
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setDataSource(pathTrack);
                mediaPlayer.prepare();
            }

            Log.e("MusicService1", "onStartCommand: " + action);
            Log.e("MusicService2", "onStartCommand: " + pathTrack);
            if (pathTrack != null && action != null) {

                Log.e("MusicService3", "onStartCommand: " + pathTrack);
                if (action.equals("ACTION_PAUSE")) {
                    Log.e("MusicService4", "onStartCommand: " + pathTrack);
                    mediaPlayer.pause();
                } else {
                    mediaPlayer.start();
                }
            }

            createNotificationChannel();
            //Tap on icon notification => return SMSReceiverActivity
            Intent notifitionIntent = new Intent(this, PlayerMusicActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(
                    this,
                    4000,
                    notifitionIntent,
                    PendingIntent.FLAG_IMMUTABLE
            );

            //create nofication
            Notification notification = new NotificationCompat.Builder(this,CHANNEL_ID)
                    .setContentTitle("SMS auto enter receiver")
                    .setContentText("tét")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentIntent(pendingIntent).build();

            // Start the service in the foreground
            startForeground(NOTIFICATION_ID, notification);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return START_STICKY;
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }


    private void createNotificationChannel(){
        //=> 8.0 moi can
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "tét",
                    NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

    }
}
