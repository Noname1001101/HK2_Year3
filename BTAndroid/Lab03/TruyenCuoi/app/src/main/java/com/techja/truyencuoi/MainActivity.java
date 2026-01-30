package com.techja.truyencuoi;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private String topicName;
    private ArrayList<StoryEntity> storyList;
    private StoryEntity currentStory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showFrg(new M000SplashFrg());
    }

    private void showFrg(Fragment frg) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.ln_main, frg, null)
                .commit();
    }

    public void gotoM001Screen() {
        showFrg(new M001Screen());
    }

    public void gotoM002Screen(String topicName) {
        this.topicName = topicName;
        showFrg(new M002StoryList());
    }

    public String getTopicName() {
        return topicName;
    }

    public void backToM001Screen() {
        gotoM001Screen();
    }

    public void backToM002Screen() {
        gotoM002Screen(topicName);
    }

    public void gotoM003Screen(ArrayList<StoryEntity> listStory, StoryEntity story) {
        this.storyList = listStory;
        this.currentStory = story;
        showFrg(new M003ScreenReadStory());
    }

    public ArrayList<StoryEntity> getStoryList() {
        return storyList;
    }

    public StoryEntity getCurrentStory() {
        return currentStory;
    }
}