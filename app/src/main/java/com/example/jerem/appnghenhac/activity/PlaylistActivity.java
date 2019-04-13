package com.example.jerem.appnghenhac.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.example.jerem.appnghenhac.R;
import com.example.jerem.appnghenhac.adapter.AdapterPlaylist;
import com.example.jerem.appnghenhac.model.ChuDe;
import com.example.jerem.appnghenhac.model.Playlist;
import com.example.jerem.appnghenhac.service.APIService;
import com.example.jerem.appnghenhac.service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlaylistActivity extends AppCompatActivity {
Toolbar toolbarPlaylist;
ProgressBar progressBarPlaylist;
RecyclerView recyclerViewPlaylist;
ChuDe chuDe=null;
DataService dataService;
String title="";
ArrayList<Playlist>playlists;
AdapterPlaylist adapterPlaylist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);
        addControll();
        getDataIntent();
        addTollBar();

    }

    private void getDataIntent() {
        Intent intent=getIntent();
        if(intent.hasExtra("chude")){
            chuDe=intent.getParcelableExtra("chude");
            if (chuDe!=null){
                title=chuDe.getTenChude();
                getDataPlaylist(chuDe.getIdChude());
            }
        }

    }

    private void getDataPlaylist(int idChude) {
        Call<List<Playlist>> callback=dataService.Getdata_PlaylistTheoChude(idChude);
        callback.enqueue(new Callback<List<Playlist>>() {
            @Override
            public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {
                playlists= (ArrayList<Playlist>) response.body();
               // Log.d("TAM_LOG","BAIHAT LIST "+baiHatArrayList.get(0).getTenbaihat());
                if(playlists.size()>0){
                    progressBarPlaylist.setVisibility(View.INVISIBLE);
                    setData();
                }
            }

            @Override
            public void onFailure(Call<List<Playlist>> call, Throwable t) {

            }
        });
    }

    private void setData() {
        adapterPlaylist=new AdapterPlaylist(this,playlists);
        RecyclerView.LayoutManager layoutManager=new GridLayoutManager(this,2);
        recyclerViewPlaylist.setHasFixedSize(true);
        recyclerViewPlaylist.setLayoutManager(layoutManager);
        recyclerViewPlaylist.setAdapter(adapterPlaylist);
        adapterPlaylist.notifyDataSetChanged();
    }

    private void addControll() {
         progressBarPlaylist=findViewById(R.id.ProgressBarPlaylist);
         progressBarPlaylist.setVisibility(View.VISIBLE);
        playlists=new ArrayList<>();
        dataService=APIService.getService();
        toolbarPlaylist=findViewById(R.id.toolbarPlaylist);
        recyclerViewPlaylist=findViewById(R.id.recyclerViewPlaylist);

    }

    private void addTollBar() {
        setSupportActionBar(toolbarPlaylist);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(title);
        toolbarPlaylist.setTitleTextColor(Color.WHITE);
        toolbarPlaylist.setSubtitleTextColor(Color.WHITE);
        toolbarPlaylist.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
