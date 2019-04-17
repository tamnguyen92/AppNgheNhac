package com.example.jerem.appnghenhac.fragment;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jerem.appnghenhac.R;
import com.example.jerem.appnghenhac.activity.PlayNhacActivity;
import com.example.jerem.appnghenhac.activity.TrangChuActivity;
import com.example.jerem.appnghenhac.model.BaiHat;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class Fragment_sub_play_music extends Fragment {
    View view;
   LinearLayout linearsublayout;
    CircleImageView imghinhsublayout;
    TextView txttenbaihatsublayout,txttencasisublayout;
    BaiHat baiHat;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.layout_fragment_sub_play_music,container,false);
        addControll();
        return view;
    }

    private void addControll() {
        linearsublayout=view.findViewById(R.id.linearsublayout);
        imghinhsublayout=view.findViewById(R.id.imghinhsublayout);
        txttenbaihatsublayout=view.findViewById(R.id.txttenbaihatsublayout);
        txttencasisublayout=view.findViewById(R.id.txttencasisublayout);

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
                Intent intent=new Intent(getActivity(),PlayNhacActivity.class);
                intent.putExtra("hello","hello");
                startActivity(intent);
            }
        });
        if(TrangChuActivity.baiHat !=null)
        {
            if(TrangChuActivity.baiHat.getIdBaihat()>0){
                baiHat=TrangChuActivity.baiHat;
                Picasso.with(getActivity()).load(baiHat.getHinhanhBaihat()).into(imghinhsublayout);

                RotateAnimation rotate = new RotateAnimation(0, 359, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                rotate.setDuration(20000);
                rotate.setRepeatCount(ValueAnimator.INFINITE);
                rotate.setRepeatMode(ValueAnimator.INFINITE);
                rotate.setFillAfter(true);
                rotate.setInterpolator(new LinearInterpolator());

                imghinhsublayout.startAnimation(rotate);
                txttenbaihatsublayout.setText(baiHat.getTenbaihat());
                txttencasisublayout.setText(baiHat.getTencasiBaihat());

            }
        }

    }


}
