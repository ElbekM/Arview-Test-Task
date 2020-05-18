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
import com.elbek.twitchviewer.model.GameOverview;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>{

    private List<GameOverview> streamList;
    private Context context;

    public RecyclerAdapter(List<GameOverview> streamList, Context context) {
        this.streamList = streamList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list, parent, false);
        return new MyViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.MyViewHolder holder, int position) {
        final GameOverview stream = streamList.get(position);
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

    public void addNews(List<GameOverview> news) {
        streamList.addAll(news);
        notifyDataSetChanged();
    }

    public List<GameOverview> getNewsList() {
        return streamList;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView streamImage;
        TextView streamGameName;
        TextView streamChannels;
        TextView streamViews;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            streamImage = itemView.findViewById(R.id.item_image);
            streamGameName = itemView.findViewById(R.id.item_game_name);
            streamChannels = itemView.findViewById(R.id.item_channels);
            streamViews = itemView.findViewById(R.id.item_viewers);
        }
    }
}
