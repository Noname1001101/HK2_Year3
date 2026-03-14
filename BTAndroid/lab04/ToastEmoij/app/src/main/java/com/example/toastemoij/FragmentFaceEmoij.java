package com.example.toastemoij;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class FragmentFaceEmoij extends Fragment implements View.OnClickListener {
    private static final int[] ids = {R.id.iv_face1, R.id.iv_face2, R.id.iv_face3, R.id.iv_face4,
            R.id.iv_face5, R.id.iv_face6, R.id.iv_face7, R.id.iv_face8, R.id.iv_face9};
    private Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.m001_frg_face_emoij, container, false);
        initViews(rootView);
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        mContext = context;
        super.onAttach(context);
    }

    private void initViews(View v) {
        for (int id : ids) {
            v.findViewById(id).setOnClickListener(this);
        }
        v.findViewById(R.id.ib_dice).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RandomEmoij();
            }
        });
    }

    @Override
    public void onClick(View v) {
        ImageView ivFace = (ImageView) v;
        showToast(ivFace.getDrawable());
    }

    private void showToast(Drawable drawable) {
        Toast toast = new Toast(mContext);
        ImageView ivEmoij = new ImageView(mContext);
        ivEmoij.setImageDrawable(drawable);
        toast.setView(ivEmoij);
        toast.show();
    }

    public void RandomEmoij(){
        if (getView() == null) return;
        java.util.Random random = new java.util.Random();

        int randomIndex = random.nextInt(ids.length);

        int randomId = ids[randomIndex];
        ImageView randomImageView = getView().findViewById(randomId);
        if (randomImageView != null) {
            showToast(randomImageView.getDrawable());
        }
    }
}