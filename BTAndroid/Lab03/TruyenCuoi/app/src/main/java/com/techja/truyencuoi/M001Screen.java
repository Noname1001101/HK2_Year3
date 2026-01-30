package com.techja.truyencuoi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class M001Screen extends Fragment {

    private RecyclerView recyclerView;
    private TopicAdapter adapter;
    private List<TopicEntity> topicList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.m001_screen, container, false);
        initViews(view);
        loadData();
        return view;
    }

    private void initViews(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void loadData() {
        topicList = new ArrayList<>();

        // Thêm dữ liệu với tên file ảnh trong assets/photo/
        topicList.add(new TopicEntity("Công sở", "Công sở.png"));
        topicList.add(new TopicEntity("Cười 18", "Cười 18.png"));
        topicList.add(new TopicEntity("Cực hài", "Cực hài.png"));
        topicList.add(new TopicEntity("Dân gian", "Dân gian.png"));
        topicList.add(new TopicEntity("Gia đình", "Gia đình.png"));
        topicList.add(new TopicEntity("Giao thông", "Giao thông.png"));
        topicList.add(new TopicEntity("Học sinh", "Học sinh.png"));
        topicList.add(new TopicEntity("Con gái", "Con gái.png"));
        topicList.add(new TopicEntity("Con nít", "Con nít.png"));
        topicList.add(new TopicEntity("Con trai", "Con trai.png"));
        topicList.add(new TopicEntity("Học trò cười", "Học trò cười.png"));
        topicList.add(new TopicEntity("Khoa học", "Khoa học.png"));
        topicList.add(new TopicEntity("Nghề nghiệp", "Nghề nghiệp.png"));
        topicList.add(new TopicEntity("Người lớn", "Người lớn.png"));


        adapter = new TopicAdapter(topicList, new TopicAdapter.OnTopicClickListener() {
            @Override
            public void onTopicClick(TopicEntity topic) {
                // Chuyển sang màn hình M002
                if (getActivity() != null) {
                    ((MainActivity) getActivity()).gotoM002Screen(topic.getName());
                }
            }
        }, getActivity().getAssets());

        recyclerView.setAdapter(adapter);
    }
}