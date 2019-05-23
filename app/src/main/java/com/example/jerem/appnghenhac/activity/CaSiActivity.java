package com.example.jerem.appnghenhac.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.jerem.appnghenhac.R;
import com.example.jerem.appnghenhac.adapter.AdapterAlbum;
import com.example.jerem.appnghenhac.adapter.AdapterBaihat;
import com.example.jerem.appnghenhac.model.Album;
import com.example.jerem.appnghenhac.model.BaiHat;
import com.example.jerem.appnghenhac.model.CaSi;
import com.example.jerem.appnghenhac.service.APIService;
import com.example.jerem.appnghenhac.service.DataService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CaSiActivity extends AppCompatActivity {
    CoordinatorLayout coordinatorLayoutCasi;
    CollapsingToolbarLayout collapsingToolbarLayoutCasi;
    Toolbar toolbarCasi;
    ImageView imgHinhlonCasy;
    CircleImageView imgCircle_casi;
    ProgressBar progressBarbaihat,progressBarAlbum;

    CaSi caSi=null;
    DataService dataService;

    ArrayList<BaiHat>baiHats;
    ArrayList<Album>albums;

    AdapterBaihat adapterBaihat;
    AdapterAlbum adapterAlbum;

    RecyclerView recyclerViewAlbum,recyclerViewBaiHat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ca_si);
        getDataIntent();
        addControll();
        init();
        getDataBaiHat();
        getDataAlbum();
    }

    private void getDataAlbum() {
        if(caSi!=null){
            Call<List<Album>> callback=dataService.Getdata_AlbumTheoCaSy(caSi.getIdCasi());
            callback.enqueue(new Callback<List<Album>>() {
                @Override
                public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {

                    albums= (ArrayList<Album>) response.body();
//                Log.d("TAM_LOG","ALBUMS LIST "+albums.get(0).getTenAlbum());
                    if(albums.size()>0){
                        progressBarAlbum.setVisibility(View.INVISIBLE);
                        RecyclerView.LayoutManager layoutManager=new GridLayoutManager(CaSiActivity.this,2);
                        adapterAlbum=new AdapterAlbum(CaSiActivity.this,albums);
                        recyclerViewAlbum.setHasFixedSize(true);
                        recyclerViewAlbum.setLayoutManager(layoutManager);
                        recyclerViewAlbum.setAdapter(adapterAlbum);
                        adapterAlbum.notifyDataSetChanged();
                    }
                }

                @Override
                public void onFailure(Call<List<Album>> call, Throwable t) {

                }
            });
        }
    }

    private void getDataBaiHat() {
        if(caSi!=null){
            Call<List<BaiHat>> callback=dataService.Getdata_BaiHatTheoCaSy(caSi.getIdCasi());
            callback.enqueue(new Callback<List<BaiHat>>() {
                @Override
                public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                    baiHats= (ArrayList<BaiHat>) response.body();
//                Log.d("TAM_LOG","ALBUMS LIST "+albums.get(0).getTenAlbum());
                    if(baiHats.size()>0){
                        progressBarbaihat.setVisibility(View.INVISIBLE);
                        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(CaSiActivity.this);
                        adapterBaihat=new AdapterBaihat(CaSiActivity.this,baiHats,R.layout.layout_custom_baihat_horizontal);
                        recyclerViewBaiHat.setHasFixedSize(true);
                        recyclerViewBaiHat.setLayoutManager(layoutManager);
                        recyclerViewBaiHat.setAdapter(adapterBaihat);
                        adapterBaihat.notifyDataSetChanged();
                    }
                }

                @Override
                public void onFailure(Call<List<BaiHat>> call, Throwable t) {

                }
            });
        }
    }

    private void init() {
        setSupportActionBar(toolbarCasi);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarCasi.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        collapsingToolbarLayoutCasi.setExpandedTitleColor(Color.WHITE);
        collapsingToolbarLayoutCasi.setCollapsedTitleTextColor(Color.WHITE);

        if(caSi!=null){
            collapsingToolbarLayoutCasi.setTitle(caSi.getTencasy());
            Picasso.with(this).load(caSi.getHinhanhCasi()).into(imgCircle_casi);
            Picasso.with(this).load(caSi.getHinhanhlonCasi()).into(imgHinhlonCasy);
        }
    }

    private void getDataIntent() {
        Intent intent=getIntent();
        if(intent.hasExtra("casy")){
            caSi=intent.getParcelableExtra("casy");
        }

    }

    private void addControll() {
        progressBarAlbum=findViewById(R.id.progressBarAlbum);
        progressBarbaihat=findViewById(R.id.progressBarbaihat);

        progressBarAlbum.setVisibility(View.VISIBLE);
        progressBarbaihat.setVisibility(View.VISIBLE);

        dataService=APIService.getService();
        recyclerViewBaiHat=findViewById(R.id.recyclerViewBaiHat);
        recyclerViewAlbum=findViewById(R.id.recyclerViewAlbum);
        coordinatorLayoutCasi=findViewById(R.id.coordinatorLayoutCasi);
        collapsingToolbarLayoutCasi=findViewById(R.id.collapsingToolbarLayoutCasi);
        toolbarCasi=findViewById(R.id.toolbarCasi);
        imgHinhlonCasy=findViewById(R.id.imgHinhlonCasy);
        imgCircle_casi=findViewById(R.id.imgCircle_casi);

        baiHats=new ArrayList<>();
        albums=new ArrayList<>();
    }
}
