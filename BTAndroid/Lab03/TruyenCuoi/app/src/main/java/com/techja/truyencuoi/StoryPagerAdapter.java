package com.techja.truyencuoi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StoryPagerAdapter extends RecyclerView.Adapter<StoryPagerAdapter.StoryPageViewHolder> {

    private List<StoryEntity> storyList;

    public StoryPagerAdapter(List<StoryEntity> storyList) {
        this.storyList = storyList;
    }

    @NonNull
    @Override
    public StoryPageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_story_page, parent, false);
        return new StoryPageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoryPageViewHolder holder, int position) {
        StoryEntity story = storyList.get(position);
        holder.tvStoryTitle.setText(story.getTitle());
        holder.tvStoryContent.setText(story.getContent());
    }

    @Override
    public int getItemCount() {
        return storyList.size();
    }

    static class StoryPageViewHolder extends RecyclerView.ViewHolder {
        TextView tvStoryTitle;
        TextView tvStoryContent;

        public StoryPageViewHolder(@NonNull View itemView) {
            super(itemView);
            tvStoryTitle = itemView.findViewById(R.id.tvStoryTitle);
            tvStoryContent = itemView.findViewById(R.id.tvStoryContent);
        }
    }
}