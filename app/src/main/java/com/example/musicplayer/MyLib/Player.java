package com.example.musicplayer.MyLib;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.example.musicplayer.R;

public class Player extends LinearLayout {

    private IClick iClick;

    private OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
           if(iClick != null){
               if (v.getId() == R.id.imgPause){
                   iClick.pause(v);
               }
           }
        }
    };

    public void setiClick(IClick iClick) {
        this.iClick = iClick;
    }

    public Player(Context context) {
        super(context);
        setup(context);
    }

    public Player(Context context, @Nullable AttributeSet attrs) {

        super(context, attrs);
        setup(context);
    }

    public Player(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setup(context);
    }

    private void setup(Context context){
        inflate(context, R.layout.player_layout,this);
        ImageView imgPause = findViewById(R.id.imgPause);
        imgPause.setOnClickListener(onClickListener);
    }

    public interface IClick{
        void pause(View v);
    }
}
