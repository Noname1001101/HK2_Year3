package com.techja.truyencuoi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class M002StoryList extends Fragment {

    private String topicName;
    private RecyclerView recyclerViewStories;
    private StoryAdapter adapter;
    private List<StoryEntity> storyList;
    private TextView tvTopicName;
    private ImageButton btnBack;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.m002_screen_storylist, container, false);

        if (getActivity() != null) {
            topicName = ((MainActivity) getActivity()).getTopicName();
        }

        initViews(view);
        loadStories();
        return view;
    }

    private void initViews(View view) {
        tvTopicName = view.findViewById(R.id.tvTopicName);
        btnBack = view.findViewById(R.id.btnBack);
        recyclerViewStories = view.findViewById(R.id.recyclerViewStories);

        tvTopicName.setText(topicName);
        recyclerViewStories.setLayoutManager(new LinearLayoutManager(getContext()));

        btnBack.setOnClickListener(v -> {
            if (getActivity() != null) {
                ((MainActivity) getActivity()).backToM001Screen();
            }
        });
    }

    private void loadStories() {
        storyList = new ArrayList<>();

        // Đọc file .txt từ assets/story/
        String fileName = getFileNameFromTopicName(topicName);

        try {
            InputStream is = getActivity().getAssets().open("story/" + fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));

            String line;

            ArrayList<String> tempStoryLines = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                line = line.trim();

                if (line.contains("','0');")) {


                    if (!tempStoryLines.isEmpty()) {
                        // Dòng đầu tiên trong danh sách gom được là Tiêu đề
                        String title = tempStoryLines.get(0);

                        // Các dòng còn lại gom lại thành Nội dung
                        StringBuilder contentBuilder = new StringBuilder();
                        for (int i = 1; i < tempStoryLines.size(); i++) {
                            contentBuilder.append(tempStoryLines.get(i)).append("\n");
                        }

                        // Thêm vào danh sách hiển thị
                        storyList.add(new StoryEntity(title, contentBuilder.toString(), fileName));
                    }

                    // Xóa danh sách tạm để chuẩn bị đọc truyện mới
                    tempStoryLines.clear();

                } else {

                    if (!line.isEmpty()) {
                        tempStoryLines.add(line);
                    }
                }
            }


            if (!tempStoryLines.isEmpty()) {
                String title = tempStoryLines.get(0);
                StringBuilder contentBuilder = new StringBuilder();
                for (int i = 1; i < tempStoryLines.size(); i++) {
                    contentBuilder.append(tempStoryLines.get(i)).append("\n");
                }
                storyList.add(new StoryEntity(title, contentBuilder.toString(), fileName));
            }

            reader.close();
            is.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


        adapter = new StoryAdapter(storyList, new StoryAdapter.OnStoryClickListener() {
            @Override
            public void onStoryClick(StoryEntity story) {
                // Chuyển sang màn hình M003 để đọc truyện
                if (getActivity() != null) {
                    ((MainActivity) getActivity()).gotoM003Screen(
                            (ArrayList<StoryEntity>) storyList, story);
                }
            }
        });

        recyclerViewStories.setAdapter(adapter);
    }

    private String getFileNameFromTopicName(String topicName) {

        return topicName + ".txt";
    }
}