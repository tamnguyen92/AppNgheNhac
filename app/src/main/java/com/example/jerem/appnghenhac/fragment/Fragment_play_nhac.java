package com.example.jerem.appnghenhac.fragment;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.cleveroad.audiovisualization.AudioVisualization;
import com.cleveroad.audiovisualization.DbmHandler;
import com.cleveroad.audiovisualization.SpeechRecognizerDbmHandler;
import com.cleveroad.audiovisualization.VisualizerDbmHandler;
import com.example.jerem.appnghenhac.PlayMusic.PlayMusic2;
import com.example.jerem.appnghenhac.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class Fragment_play_nhac extends Fragment {
    View view;

    CircleImageView circleImageView;
    TextView txttencasi1,txttenbaihat1;
    ImageView imgdisc;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.layout_fragment_play_nhac,container,false);
        circleImageView=view.findViewById(R.id.imgCircle);
        txttenbaihat1=view.findViewById(R.id.txttenbaihat1);
        txttencasi1=view.findViewById(R.id.txttencasi1);
        imgdisc=view.findViewById(R.id.imgdisc);

        RotateAnimation rotate = new RotateAnimation(0, 359, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(20000);
        rotate.setRepeatCount(ValueAnimator.INFINITE);
        rotate.setRepeatMode(ValueAnimator.INFINITE);
        rotate.setFillAfter(true);
        rotate.setInterpolator(new LinearInterpolator());

        circleImageView.startAnimation(rotate);
//        Animation firstAnimation = AnimationUtils.loadAnimation(getActivity(),R.anim.animation_scale);
//        imgdisc.startAnimation(firstAnimation);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
      //  audioVisualization = (AudioVisualization) glAudioVisualizationView;
    }

    public void Playnhac(String hinhanh,String tenbaihat,String tencasi) {
        Picasso.with(getActivity()).load(hinhanh).into(circleImageView);
        txttenbaihat1.setText(tenbaihat);
        txttencasi1.setText(tencasi);
    }
    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {

        super.onPause();
    }
    @Override
    public void onDestroyView() {

        super.onDestroyView();
    }
}
