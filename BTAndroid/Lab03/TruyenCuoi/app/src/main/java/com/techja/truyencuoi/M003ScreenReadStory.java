package com.techja.truyencuoi;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;

public class M003ScreenReadStory extends Fragment {

    private TextView tvTopicName;
    private TextView tvStoryIndex;
    private ImageButton btnBack;
    private ImageButton btnRotation;
    private ViewPager2 viewPager;

    private ArrayList<StoryEntity> storyList;
    private StoryEntity currentStory;
    private String topicName;
    private int currentPosition;

    private boolean isRotationEnabled = true;  // Mặc định cho phép xoay

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.m003_screen_readstory, container, false);


        if (getActivity() != null) {
            MainActivity mainActivity = (MainActivity) getActivity();
            topicName = mainActivity.getTopicName();
            storyList = mainActivity.getStoryList();
            currentStory = mainActivity.getCurrentStory();

            // Tìm vị trí của truyện hiện tại
            currentPosition = 0;
            if (storyList != null && currentStory != null) {
                for (int i = 0; i < storyList.size(); i++) {
                    if (storyList.get(i).getTitle().equals(currentStory.getTitle())) {
                        currentPosition = i;
                        break;
                    }
                }
            }
        }

        initViews(view);
        setupViewPager();
        updateRotationIcon();

        return view;
    }

    private void initViews(View view) {
        tvTopicName = view.findViewById(R.id.tvTopicName);
        tvStoryIndex = view.findViewById(R.id.tvStoryIndex);
        btnBack = view.findViewById(R.id.btnBack);
        btnRotation = view.findViewById(R.id.btnRotation);
        viewPager = view.findViewById(R.id.viewPager);

        if (topicName != null) {
            tvTopicName.setText(topicName);
        }

        btnBack.setOnClickListener(v -> {
            if (getActivity() != null) {

                getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                ((MainActivity) getActivity()).backToM002Screen();
            }
        });

        btnRotation.setOnClickListener(v -> {
            toggleRotation();
        });
    }

    private void toggleRotation() {
        isRotationEnabled = !isRotationEnabled;

        if (getActivity() != null) {
            if (isRotationEnabled) {
                // Cho phép xoay tự do
                getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
            } else {
                // Khóa ở chế độ hiện tại
                int currentOrientation = getResources().getConfiguration().orientation;
                if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
                    getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                } else {
                    getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                }
            }
        }

        updateRotationIcon();
    }

    private void updateRotationIcon() {
        if (isRotationEnabled) {
            btnRotation.setImageResource(R.drawable.ic_rotation);
            btnRotation.setAlpha(1.0f);  // Đậm khi BẬT
        } else {
            btnRotation.setImageResource(R.drawable.ic_rotation);
            btnRotation.setAlpha(0.4f);  // Mờ khi TẮT
        }
    }

    private void setupViewPager() {
        if (storyList == null || storyList.isEmpty()) {
            return;
        }

        // Set adapter
        StoryPagerAdapter adapter = new StoryPagerAdapter(storyList);
        viewPager.setAdapter(adapter);

        // Set vị trí ban đầu
        viewPager.setCurrentItem(currentPosition, false);

        // Update chỉ số truyện khi vuốt
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                updateStoryIndex(position);
            }
        });

        // Update chỉ số ban đầu
        updateStoryIndex(currentPosition);
    }

    private void updateStoryIndex(int position) {
        if (storyList != null) {
            String index = (position + 1) + "/" + storyList.size();
            tvStoryIndex.setText(index);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Reset về portrait khi thoát fragment
        if (getActivity() != null) {
            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }
}