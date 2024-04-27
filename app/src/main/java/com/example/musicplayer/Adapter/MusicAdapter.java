package com.example.musicplayer.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayer.Models.Track;
import com.example.musicplayer.R;
import com.example.musicplayer.databinding.MusicItemHolderBinding;

import java.util.List;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.MusicViewHolder> {

    private List<Track> trackList;

    public MusicAdapter(List<Track> trackList) {
        this.trackList = trackList;
    }

    @NonNull
    @Override
    public MusicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MusicItemHolderBinding binding = MusicItemHolderBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MusicViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MusicViewHolder holder, int position) {
        Track track = trackList.get(position);
        holder.binding.trackName.setText(track.getName());
        holder.binding.trackArti.setText("MTP");
    }

    @Override
    public int getItemCount() {
        return trackList.size();
    }

    class MusicViewHolder extends RecyclerView.ViewHolder{

        private MusicItemHolderBinding binding;
        public MusicViewHolder(@NonNull MusicItemHolderBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
