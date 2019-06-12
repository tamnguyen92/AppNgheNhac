package com.example.jerem.appnghenhac.fragment;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jerem.appnghenhac.PlayMusic.PlayMusic2;
import com.example.jerem.appnghenhac.R;
import com.example.jerem.appnghenhac.activity.PlayNhacActivity;
import com.example.jerem.appnghenhac.activity.TrangChuActivity;
import com.example.jerem.appnghenhac.model.BaiHat;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class Fragment_sub_play_music extends Fragment {
    View view;
    public static Fragment fragment_sub_play_music=null;
   LinearLayout linearsublayout,linearLayoutnho,linearLayoutlon;
    CircleImageView imghinhsublayout,imghinhsublayoutnho;
    TextView txttenbaihatsublayout,txttencasisublayout;
    BaiHat baiHat;
    ImageView btnplaysub,btnpresub,btnnextsub;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.layout_fragment_sub_play_music,container,false);
        fragment_sub_play_music=this;
        Log.d("Fragment_sub_play_music", "onCreateView: ");
        addControll();
        return view;
    }
    public void xulyPlay() {
        if(PlayNhacActivity.isplaying==true){
            if(PlayMusic2.mPlayer.isPlaying()){
                btnplaysub.setImageResource(R.drawable.pause);
            }
        }else {
            btnplaysub.setImageResource(R.drawable.play_button);
        }
    }

    @Override
    public void onResume() {

        super.onResume();
    }
    public void CapnhatLayout(){
        baiHat=TrangChuActivity.baiHat;
        Picasso.with(getActivity()).load(baiHat.getHinhanhBaihat()).into(imghinhsublayout);
        Picasso.with(getActivity()).load(baiHat.getHinhanhBaihat()).into(imghinhsublayoutnho);


        RotateAnimation rotate = new RotateAnimation(0, 359, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(20000);
        rotate.setRepeatCount(ValueAnimator.INFINITE);
        rotate.setRepeatMode(ValueAnimator.INFINITE);
        rotate.setFillAfter(true);
        rotate.setInterpolator(new LinearInterpolator());

        imghinhsublayout.startAnimation(rotate);

        RotateAnimation rotate2 = new RotateAnimation(0, 359, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate2.setDuration(20000);
        rotate2.setRepeatCount(ValueAnimator.INFINITE);
        rotate2.setRepeatMode(ValueAnimator.INFINITE);
        rotate2.setFillAfter(true);
        rotate2.setInterpolator(new LinearInterpolator());

        imghinhsublayoutnho.startAnimation(rotate2);
        txttenbaihatsublayout.setText(baiHat.getTenbaihat());
        txttencasisublayout.setText(baiHat.getTencasiBaihat());
    }
    private void addControll() {
        linearLayoutnho=view.findViewById(R.id.linearLayoutnho);
        linearLayoutlon=view.findViewById(R.id.linearLayoutlon);
        imghinhsublayoutnho=view.findViewById(R.id.imghinhsublayoutnho);

        linearsublayout=view.findViewById(R.id.linearsublayout);
        imghinhsublayout=view.findViewById(R.id.imghinhsublayout);
        txttenbaihatsublayout=view.findViewById(R.id.txttenbaihatsublayout);
        txttencasisublayout=view.findViewById(R.id.txttencasisublayout);
        btnplaysub=view.findViewById(R.id.btnplaysub);
        if(PlayMusic2.mPlayer.isPlaying()){
            btnplaysub.setImageResource(R.drawable.pause);
        }else {
            btnplaysub.setImageResource(R.drawable.play_button);
        }

        btnnextsub=view.findViewById(R.id.btnnextsub);
        btnpresub=view.findViewById(R.id.btnpresub);
        btnnextsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((PlayNhacActivity)PlayNhacActivity.playNhacActivity).xulynext();
                xulyPlay();
            }
        });
        btnpresub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((PlayNhacActivity)PlayNhacActivity.playNhacActivity).xulypre();
                xulyPlay();
            }
        });
        btnplaysub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((PlayNhacActivity)PlayNhacActivity.playNhacActivity).xulyPlay();
                xulyPlay();
            }
        });
        linearsublayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),PlayNhacActivity.class);
                intent.putExtra("hello","hello");
                startActivity(intent);
            }
        });
        imghinhsublayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent=new Intent(getActivity(),PlayNhacActivity.class);
//                intent.putExtra("hello","hello");
//                startActivity(intent);
                linearLayoutlon.setVisibility(View.GONE);
                linearLayoutnho.setVisibility(View.VISIBLE);
            }
        });
        imghinhsublayoutnho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayoutlon.setVisibility(View.VISIBLE);
                linearLayoutnho.setVisibility(View.GONE);
            }
        });
        if(TrangChuActivity.baiHat !=null)
        {
            if(TrangChuActivity.baiHat.getIdBaihat()>0){
                CapnhatLayout();

            }
        }

    }




}
