package com.example.jerem.appnghenhac.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.jerem.appnghenhac.InterFace.DowloadComplete;
import com.example.jerem.appnghenhac.R;
import com.example.jerem.appnghenhac.adapter.AdapterAlbum;
import com.example.jerem.appnghenhac.adapter.AdapterBaiHatYeuThich;
import com.example.jerem.appnghenhac.adapter.AdapterBaihat;
import com.example.jerem.appnghenhac.adapter.AdapterPlaynhac;
import com.example.jerem.appnghenhac.fragment.Fragment_sub_play_music;
import com.example.jerem.appnghenhac.model.BaiHat;
import com.example.jerem.appnghenhac.model.Object_Json;

import java.util.ArrayList;
import java.util.Collections;

public class UserActivity extends AppCompatActivity implements DowloadComplete {
ProgressBar ProgressBarUser;
Toolbar tool_bar_user;
TextView txttitlebaihatyeuthich;
ImageView imgtimkieminfor;

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;


RecyclerView lst_user_list;
AdapterBaiHatYeuThich adapterBaiHatYeuThich;
    AdapterBaihat adapterBaihat;
AdapterAlbum adapterAlbum;
LinearLayout linearlayouttrangchu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_user);

        linearlayouttrangchu=findViewById(R.id.linearlayouttrangchu);
        linearlayouttrangchu.setVisibility(View.GONE);

        ProgressBarUser=findViewById(R.id.ProgressBarUser);
        tool_bar_user=findViewById(R.id.tool_bar_user);
        txttitlebaihatyeuthich=findViewById(R.id.txttitlebaihatyeuthich);
        imgtimkieminfor=findViewById(R.id.imgtimkieminfor);
        lst_user_list=findViewById(R.id.lsbaihatyeuthich);

        addFragment();

        imgtimkieminfor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(UserActivity.this,SearchActivity.class);
                startActivity(intent);
            }
        });
        setSupportActionBar(tool_bar_user);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tool_bar_user.setTitleTextColor(Color.WHITE);
        tool_bar_user.setSubtitleTextColor(Color.WHITE);
        tool_bar_user.setNavigationIcon(getResources().getDrawable(R.drawable.arrow));
        tool_bar_user.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getDataIntent();
    }
    private void addFragment() {
        if(TrangChuActivity.isplaying==true){

            linearlayouttrangchu.setVisibility(View.VISIBLE);
            fragmentManager =getSupportFragmentManager();
            fragmentTransaction=fragmentManager.beginTransaction();
            Fragment_sub_play_music fragment_sub_play_music=new Fragment_sub_play_music();

            fragmentTransaction.replace(R.id.linearlayouttrangchu,fragment_sub_play_music);
            fragmentTransaction.commit();
        }
    }

    private void getDataIntent() {
        Intent intent=getIntent();
        if(intent.hasExtra("baihatyeuthich")){
            getSupportActionBar().setTitle("Bài hát yêu thích");
            txttitlebaihatyeuthich.setText("Bài hát yêu thích");
            if(Object_Json.dsBaiHatYeuThich.size()>0){
             Collections.reverse(Object_Json.dsBaiHatYeuThich);
                RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
                adapterBaiHatYeuThich=new AdapterBaiHatYeuThich(this,Object_Json.dsBaiHatYeuThich,R.layout.layout_custom_baihat_yeuthich);
                lst_user_list.setHasFixedSize(true);
                lst_user_list.setLayoutManager(layoutManager);
                lst_user_list.setAdapter(adapterBaiHatYeuThich);
                adapterBaiHatYeuThich.notifyDataSetChanged();
                ProgressBarUser.setVisibility(View.GONE);
                Collections.reverse(Object_Json.dsAlbumYeuThich);
            }
        }
        if(intent.hasExtra("albumyeuthich")){
            getSupportActionBar().setTitle("Album yêu thích");
            txttitlebaihatyeuthich.setText("Album yêu thích");
            if(Object_Json.dsAlbumYeuThich.size()>0){
              Collections.reverse(Object_Json.dsAlbumYeuThich);
                RecyclerView.LayoutManager layoutManager=new GridLayoutManager(this,2);
                adapterAlbum=new AdapterAlbum(this,Object_Json.dsAlbumYeuThich);
                lst_user_list.setHasFixedSize(true);
                lst_user_list.setLayoutManager(layoutManager);
                lst_user_list.setAdapter(adapterAlbum);
                adapterAlbum.notifyDataSetChanged();
                ProgressBarUser.setVisibility(View.GONE);
                Collections.reverse(Object_Json.dsAlbumYeuThich);
            }
        }if(intent.hasExtra("downloard")){
            getSupportActionBar().setTitle("Downloard ");
            txttitlebaihatyeuthich.setText("Downloard ");
            if(Object_Json.dsBaiHatDownloard.size()>0){
                RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(UserActivity.this);
                adapterBaihat=new AdapterBaihat(UserActivity.this,Object_Json.dsBaiHatDownloard,R.layout.layout_custom_baihat_lichsu);
                lst_user_list.setHasFixedSize(true);
                lst_user_list.setLayoutManager(layoutManager);
                lst_user_list.setAdapter(adapterBaihat);
                adapterBaihat.notifyDataSetChanged();
                ProgressBarUser.setVisibility(View.GONE);

            }
        }
        if(intent.hasExtra("baihatlichsu")){
            getSupportActionBar().setTitle("Lịch sữ ");
            txttitlebaihatyeuthich.setText("Lịch sữ ");
            if(Object_Json.dsBaiHatLichSu.size()>0){
                RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(UserActivity.this);
                adapterBaihat=new AdapterBaihat(UserActivity.this,Object_Json.dsBaiHatLichSu,R.layout.layout_custom_baihat_lichsu);
                lst_user_list.setHasFixedSize(true);
                lst_user_list.setLayoutManager(layoutManager);
                lst_user_list.setAdapter(adapterBaihat);
                adapterBaihat.notifyDataSetChanged();
                ProgressBarUser.setVisibility(View.GONE);

            }
        }
    }

    @Override
    protected void onResume() {
        addFragment();
        if(adapterBaihat!=null){
            adapterBaihat.notifyDataSetChanged();
        }
        if(adapterAlbum!=null){
            adapterAlbum.notifyDataSetChanged();
        }
        if(adapterBaiHatYeuThich!=null){
            adapterBaiHatYeuThich.notifyDataSetChanged();
        }
        super.onResume();
    }

    @Override
    public void dowload_complete(String url, int position) {
        if(adapterBaihat !=null){
            adapterBaihat.notifyDataSetChanged();
        }
    }
}
