package com.elbek.twitchviewer.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.elbek.twitchviewer.R;
import com.elbek.twitchviewer.model.TwitchStream;

import java.util.ArrayList;
import java.util.List;

public class TwitchStreamAdapter extends RecyclerView.Adapter<TwitchStreamAdapter.GameViewHolder> {

    private List<TwitchStream> streamList;
    private Context context;

    public TwitchStreamAdapter(Context context) {
        streamList = new ArrayList<>();
        this.context = context;
    }

    @NonNull
    @Override
    public GameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list, parent, false);
        return new GameViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull GameViewHolder holder, int position) {
        final TwitchStream stream = streamList.get(position);
        holder.streamGameName.setText(stream.getGame().getName());
        holder.streamChannels.setText("Channels: " + stream.getChannels().toString());
        holder.streamViews.setText("Views: " + stream.getViewers().toString());

        // Image load
        Glide.with(context).load(stream.getGame().getBox().getLarge()).into(holder.streamImage);
    }

    @Override
    public int getItemCount() {
        return streamList.size();
    }

    public void addAll(List<TwitchStream> news) {
        streamList.addAll(news);
        notifyDataSetChanged();
    }

    public void clear(){
        int size = streamList.size();
        streamList.clear();
        notifyItemRangeRemoved(0, size);
    }

    class GameViewHolder extends RecyclerView.ViewHolder {

        ImageView streamImage;
        TextView streamGameName;
        TextView streamChannels;
        TextView streamViews;

        GameViewHolder(@NonNull View itemView) {
            super(itemView);
            streamImage = itemView.findViewById(R.id.item_image);
            streamGameName = itemView.findViewById(R.id.item_game_name);
            streamChannels = itemView.findViewById(R.id.item_channels);
            streamViews = itemView.findViewById(R.id.item_viewers);
        }
    }
}
