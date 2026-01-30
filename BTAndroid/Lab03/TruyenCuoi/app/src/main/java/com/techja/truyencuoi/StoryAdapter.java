package com.techja.truyencuoi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.StoryViewHolder> {

    private List<StoryEntity> storyList;
    private OnStoryClickListener listener;

    public interface OnStoryClickListener {
        void onStoryClick(StoryEntity story);
    }

    public StoryAdapter(List<StoryEntity> storyList, OnStoryClickListener listener) {
        this.storyList = storyList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public StoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_story, parent, false);
        return new StoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoryViewHolder holder, int position) {
        StoryEntity story = storyList.get(position);
        holder.tvStoryTitle.setText(story.getTitle());


        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onStoryClick(story);
            }
        });
    }

    @Override
    public int getItemCount() {
        return storyList.size();
    }

    static class StoryViewHolder extends RecyclerView.ViewHolder {
        ImageView tvStoryIcon;
        TextView tvStoryTitle;

        public StoryViewHolder(@NonNull View itemView) {
            super(itemView);
            tvStoryIcon = itemView.findViewById(R.id.tvStoryIcon);
            tvStoryTitle = itemView.findViewById(R.id.tvStoryTitle);
        }
    }
}