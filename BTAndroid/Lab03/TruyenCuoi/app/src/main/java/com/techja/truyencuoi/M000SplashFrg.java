package com.techja.truyencuoi;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import androidx.fragment.app.Fragment;

public class M000SplashFrg extends Fragment {

    private ProgressBar progressBar;
    private Handler handler = new Handler();
    private int progressStatus = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.m000_frg_splash, container, false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        progressBar = view.findViewById(R.id.progressBar1);

        // Animate progress bar từ 0 đến 100 trong 2 giây
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (progressStatus < 100) {
                    progressStatus += 1;

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(progressStatus);
                        }
                    });

                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                // Sau khi loading xong, chuyển màn hình
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        gotoM001Screen();
                    }
                });
            }
        }).start();
    }

    private void gotoM001Screen() {
        if (getActivity() != null) {
            ((MainActivity) getActivity()).gotoM001Screen();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        handler.removeCallbacksAndMessages(null);
    }
}