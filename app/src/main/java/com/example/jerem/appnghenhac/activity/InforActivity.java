package com.example.jerem.appnghenhac.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.jerem.appnghenhac.R;
import com.example.jerem.appnghenhac.adapter.AdapterAlbum;
import com.example.jerem.appnghenhac.adapter.AdapterBaihat;
import com.example.jerem.appnghenhac.adapter.AdapterCasi;
import com.example.jerem.appnghenhac.adapter.AdapterChuDe;
import com.example.jerem.appnghenhac.adapter.AdapterPlaylist;
import com.example.jerem.appnghenhac.model.Album;
import com.example.jerem.appnghenhac.model.BaiHat;
import com.example.jerem.appnghenhac.model.CaSi;
import com.example.jerem.appnghenhac.model.ChuDe;
import com.example.jerem.appnghenhac.model.Playlist;
import com.example.jerem.appnghenhac.service.APIService;
import com.example.jerem.appnghenhac.service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InforActivity extends AppCompatActivity {
Toolbar tool_bar_infor;
RecyclerView lstInfor;
ProgressBar ProgressBarInfor;
int dataIntent=-1;
DataService dataService;
ImageView imgtimkieminfor;
TextView txttitle;

ArrayList<ChuDe>chuDes;
AdapterChuDe adapterChuDe;

ArrayList<CaSi>caSis;
AdapterCasi adapterCasi;

    ArrayList<Album>albums;
    AdapterAlbum adapterAlbum;

    ArrayList<Playlist>playlists;
    AdapterPlaylist adapterPlaylist;

    ArrayList<BaiHat>baiHats;
    AdapterBaihat adapterBaihat;
String title="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infor);
        dataService=APIService.getService();
        GetIntent();
        addControll();

    }


    private void addControll() {
        tool_bar_infor=findViewById(R.id.tool_bar_infor);
        lstInfor=findViewById(R.id.lstInfor);
        imgtimkieminfor=findViewById(R.id.imgtimkieminfor);
        ProgressBarInfor=findViewById(R.id.ProgressBarInfor);
        ProgressBarInfor.setVisibility(View.VISIBLE);
        txttitle=findViewById(R.id.txttitle);
        imgtimkieminfor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent intent=new Intent(InforActivity.this,SearchActivity.class);
              startActivity(intent);
            }
        });
        setSupportActionBar(tool_bar_infor);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(title);
        txttitle.setText(title);
        tool_bar_infor.setTitleTextColor(Color.WHITE);
        tool_bar_infor.setSubtitleTextColor(Color.WHITE);
        tool_bar_infor.setNavigationIcon(getResources().getDrawable(R.drawable.arrow));
        tool_bar_infor.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void GetIntent() {
        Intent intent=getIntent();
        if(intent.hasExtra("chude")){
            title="Chủ Đề";

            dataIntent=intent.getIntExtra("chude",-1);
            if(dataIntent>=0){
               chuDes=new ArrayList<>();
               getdataChude();
            }
        }
        if(intent.hasExtra("casi")){
            title="Nghệ Sỹ";
            dataIntent=intent.getIntExtra("casi",-1);
            if(dataIntent>=0){
                caSis=new ArrayList<>();
                getdataCasi();
            }
        }
        if(intent.hasExtra("playlist")){
            title="PlayList";
            dataIntent=intent.getIntExtra("playlist",-1);
            if(dataIntent>=0){
                playlists=new ArrayList<>();
                getdataPlaylist();
            }
        }
        if(intent.hasExtra("Album")){
            title="Album";
            dataIntent=intent.getIntExtra("Album",-1);
            if(dataIntent>=0){
                Log.d("TAM_LOG_INFOR","ALBUMS LIST ");
                albums=new ArrayList<>();
                getdataAlbum();
            }
        }
        if(intent.hasExtra("baihatnghenhieu")){
            title="Top Bài Hát trong tuần";
            dataIntent=intent.getIntExtra("baihatnghenhieu",-1);
            if(dataIntent>=0){
                baiHats=new ArrayList<>();
                getdataBaiHat();
            }
        }
        if(intent.hasExtra("albumnghenhieu")){
            title="Top Album trong tuần";
            dataIntent=intent.getIntExtra("albumnghenhieu",-1);
            if(dataIntent>=0){

                albums=new ArrayList<>();
                getdataAlbumnghenhieu();
            }
        }
        if(intent.hasExtra("baihatthichnhieu")){
            title="Top Bài hát yêu thích trong tuần";
            dataIntent=intent.getIntExtra("baihatthichnhieu",-1);
            if(dataIntent>=0){
                baiHats=new ArrayList<>();
                getdataBaiHatThichnhieu();
            }
        }
    }

    private void getdataBaiHatThichnhieu() {
        Call<List<BaiHat>> callback=dataService.Getdata_BaiHatLuotThich();
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                baiHats= (ArrayList<BaiHat>) response.body();

                if(baiHats.size()>0){
                    ProgressBarInfor.setVisibility(View.INVISIBLE);
                    RecyclerView.LayoutManager layoutManager=new GridLayoutManager(InforActivity.this,3);
                    adapterBaihat=new AdapterBaihat(InforActivity.this,baiHats,R.layout.layout_custom_baihat_vertical);
                    lstInfor.setHasFixedSize(true);
                    lstInfor.setLayoutManager(layoutManager);
                    lstInfor.setAdapter(adapterBaihat);
                    adapterBaihat.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }

    private void getdataBaiHat() {
        Call<List<BaiHat>> callback=dataService.Getdata_BaiHatLuotNghe();
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                baiHats= (ArrayList<BaiHat>) response.body();

                if(baiHats.size()>0){
                    ProgressBarInfor.setVisibility(View.INVISIBLE);
                    RecyclerView.LayoutManager layoutManager=new GridLayoutManager(InforActivity.this,3);
                    adapterBaihat=new AdapterBaihat(InforActivity.this,baiHats,R.layout.layout_custom_baihat_vertical);
                    lstInfor.setHasFixedSize(true);
                    lstInfor.setLayoutManager(layoutManager);
                    lstInfor.setAdapter(adapterBaihat);
                    adapterBaihat.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }

    private void getdataAlbumnghenhieu() {
        Call<List<Album>> callback=dataService.Getdata_AlbumLuotNghe();
        callback.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                albums= (ArrayList<Album>) response.body();

                if(albums.size()>0){
                    ProgressBarInfor.setVisibility(View.INVISIBLE);
                    RecyclerView.LayoutManager layoutManager=new GridLayoutManager(InforActivity.this,2);
                    adapterAlbum=new AdapterAlbum(InforActivity.this,albums);
                    lstInfor.setHasFixedSize(true);
                    lstInfor.setLayoutManager(layoutManager);
                    lstInfor.setAdapter(adapterAlbum);
                    adapterAlbum.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {

            }
        });
    }

    private void getdataAlbum() {
        Call<List<Album>> callback=dataService.Getdata_AlbumAll();
        callback.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                albums= (ArrayList<Album>) response.body();

                if(albums.size()>0){
                    ProgressBarInfor.setVisibility(View.INVISIBLE);
                    RecyclerView.LayoutManager layoutManager=new GridLayoutManager(InforActivity.this,2);
                    adapterAlbum=new AdapterAlbum(InforActivity.this,albums);
                    lstInfor.setHasFixedSize(true);
                    lstInfor.setLayoutManager(layoutManager);
                    lstInfor.setAdapter(adapterAlbum);
                    adapterAlbum.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {

            }
        });
    }

    private void getdataPlaylist() {
        Call<List<Playlist>> callback=dataService.Getdata_PlaylistAll();
        callback.enqueue(new Callback<List<Playlist>>() {
            @Override
            public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {
                playlists= (ArrayList<Playlist>) response.body();

                if(playlists.size()>0){
                    ProgressBarInfor.setVisibility(View.INVISIBLE);
                    RecyclerView.LayoutManager layoutManager=new GridLayoutManager(InforActivity.this,2);
                    adapterPlaylist=new AdapterPlaylist(InforActivity.this,playlists);
                    lstInfor.setHasFixedSize(true);
                    lstInfor.setLayoutManager(layoutManager);
                    lstInfor.setAdapter(adapterPlaylist);
                    adapterPlaylist.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Playlist>> call, Throwable t) {

            }
        });
    }

    private void getdataCasi() {
        Call<List<CaSi>> callback=dataService.Getdata_CasiAll();
        callback.enqueue(new Callback<List<CaSi>>() {
            @Override
            public void onResponse(Call<List<CaSi>> call, Response<List<CaSi>> response) {
                caSis= (ArrayList<CaSi>) response.body();

                if(caSis.size()>0){
                    ProgressBarInfor.setVisibility(View.INVISIBLE);
                    RecyclerView.LayoutManager layoutManager=new GridLayoutManager(InforActivity.this,3);
                    adapterCasi=new AdapterCasi(InforActivity.this,caSis,R.layout.layout_custom_casi,1);
                    lstInfor.setHasFixedSize(true);
                    lstInfor.setLayoutManager(layoutManager);
                    lstInfor.setAdapter(adapterCasi);
                    adapterCasi.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<CaSi>> call, Throwable t) {

            }
        });
    }

    private void getdataChude() {
        Call<List<ChuDe>> callback=dataService.Getdata_ChuDeAll();
        callback.enqueue(new Callback<List<ChuDe>>() {
            @Override
            public void onResponse(Call<List<ChuDe>> call, Response<List<ChuDe>> response) {
                chuDes= (ArrayList<ChuDe>) response.body();

                if(chuDes.size()>0){
                    ProgressBarInfor.setVisibility(View.INVISIBLE);
                    RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(InforActivity.this);
                    //RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);  dang horizontai,false la ko roll
                    adapterChuDe=new AdapterChuDe(InforActivity.this,chuDes);
                    lstInfor.setHasFixedSize(true);
                    lstInfor.setLayoutManager(layoutManager);
                    lstInfor.setAdapter(adapterChuDe);
                    adapterChuDe.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<ChuDe>> call, Throwable t) {

            }
        });
    }
}
