package com.techja.truyencuoi;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.TopicViewHolder> {

    private List<TopicEntity> topicList;
    private OnTopicClickListener listener;
    private AssetManager assetManager;

    public interface OnTopicClickListener {
        void onTopicClick(TopicEntity topic);
    }

    public TopicAdapter(List<TopicEntity> topicList, OnTopicClickListener listener, AssetManager assetManager) {
        this.topicList = topicList;
        this.listener = listener;
        this.assetManager = assetManager;
    }

    @NonNull
    @Override
    public TopicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_topic, parent, false);
        return new TopicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopicViewHolder holder, int position) {
        TopicEntity topic = topicList.get(position);
        holder.tvTopicName.setText(topic.getName());

        // Load ảnh từ assets
        try {
            InputStream is = assetManager.open("photo/" + topic.getImagePath());
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            holder.imgTopic.setImageBitmap(bitmap);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
            // Nếu không load được, dùng ảnh mặc định
            holder.imgTopic.setImageResource(R.mipmap.ic_launcher);
        }

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onTopicClick(topic);
            }
        });
    }

    @Override
    public int getItemCount() {
        return topicList.size();
    }

    static class TopicViewHolder extends RecyclerView.ViewHolder {
        ImageView imgTopic;
        TextView tvTopicName;

        public TopicViewHolder(@NonNull View itemView) {
            super(itemView);
            imgTopic = itemView.findViewById(R.id.imgTopic);
            tvTopicName = itemView.findViewById(R.id.tvTopicName);
        }
    }
}