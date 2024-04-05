package com.example.musicplayer.MyLib;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.example.musicplayer.R;

public class PlayerMore extends LinearLayout {

    private IClick iClick;
    private Context context;

    private OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (iClick!= null){
                if(v.getId() == R.id.imgHeart){
                    iClick.heart(v);
                }
                else if (v.getId() == R.id.imgPlaylist){
                    iClick.playlist(v);
                } else if (v.getId() == R.id.imgRandom) {
                    iClick.random(v);
                }
            }else{
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Please setter Iclick for Player more");
                builder.setCancelable(true);
                builder.setPositiveButton(
                        "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        }
    };

    public void setiClick(IClick iClick) {
        this.iClick = iClick;
    }

    public PlayerMore(Context context) {
        super(context);
        setup(context);
    }

    public PlayerMore(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setup(context);
    }

    public PlayerMore(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setup(context);
    }

    private void setup(Context context){
        inflate(context, R.layout.player_more_layout,this);
        this.context = context;
        ImageView imgHeart = findViewById(R.id.imgHeart);
        ImageView imgPlaylist = findViewById(R.id.imgPlaylist);
        ImageView imgRandom = findViewById(R.id.imgRandom);

        imgHeart.setOnClickListener(onClickListener);
        imgPlaylist.setOnClickListener(onClickListener);
        imgRandom.setOnClickListener(onClickListener);
    }

    public interface  IClick{
        void heart(View v);
        void playlist(View v);
        void random(View v);
    }
}
