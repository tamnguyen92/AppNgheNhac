package com.example.jerem.appnghenhac.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.jerem.appnghenhac.R;
import com.example.jerem.appnghenhac.adapter.AdapterPlaynhacList;
import com.example.jerem.appnghenhac.fragment.Fragment_sub_play_music;
import com.example.jerem.appnghenhac.model.Album;
import com.example.jerem.appnghenhac.model.BaiHat;
import com.example.jerem.appnghenhac.model.Object_Json;
import com.example.jerem.appnghenhac.model.Playlist;
import com.example.jerem.appnghenhac.model.QuangCao;
import com.example.jerem.appnghenhac.model.Result;
import com.example.jerem.appnghenhac.service.APIService;
import com.example.jerem.appnghenhac.service.DataService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachBaiHatActivity extends AppCompatActivity {
CoordinatorLayout coordinatorLayout;
CollapsingToolbarLayout collapsingToolbarLayout;
Toolbar toolbar;
RecyclerView recyclerViewdanhsachbaihat;
FloatingActionButton floatingActionButton;
ImageView imgdanhsachcakhuc,imgdanhsachcakhuc2;
    AdapterPlaynhacList adapterPlaynhac;
QuangCao q=null;
    Intent intent;
    ArrayList<QuangCao> quangCaoArrayList;
    ArrayList<BaiHat> baiHatArrayList;
    DataService dataService;
    String linkHinh="";
    String tentitle="";
    Album album=null;
    Result result=null;
  LinearLayout linearlayouttrangchu;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_bai_hat);
         intent=getIntent();
        addControll();
        addFragment();
        init();
        getDataIntent();


    }

    private void setData() {
        if(baiHatArrayList.size()>0){
            collapsingToolbarLayout.setTitle(tentitle);
            Picasso.with(this).load(linkHinh).into(imgdanhsachcakhuc);
            Picasso.with(this).load(linkHinh).into(imgdanhsachcakhuc2);


            adapterPlaynhac=new AdapterPlaynhacList(this,baiHatArrayList);
            RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
            //RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);  dang horizontai,false la ko roll

            recyclerViewdanhsachbaihat.setHasFixedSize(true);
            recyclerViewdanhsachbaihat.setLayoutManager(layoutManager);
            recyclerViewdanhsachbaihat.setAdapter(adapterPlaynhac);
            adapterPlaynhac.notifyDataSetChanged();
        }
    }

    private void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);


    }

    private void addControll() {
        quangCaoArrayList=new ArrayList<>();
        baiHatArrayList=new ArrayList<>();
        dataService=APIService.getService();
        linearlayouttrangchu=findViewById(R.id.linearlayouttrangchu);
        linearlayouttrangchu.setVisibility(View.GONE);
        collapsingToolbarLayout=findViewById(R.id.collapsingToolbar);
        coordinatorLayout=findViewById(R.id.coordinatorLayout);
        toolbar=findViewById(R.id.toolbardanhsach);
        imgdanhsachcakhuc=findViewById(R.id.imgdanhsachcakhuc);
        imgdanhsachcakhuc2=findViewById(R.id.imgdanhsachcakhuc2);
        recyclerViewdanhsachbaihat=findViewById(R.id.recycleViewdanhsachbaihat);
        floatingActionButton=findViewById(R.id.floattingactionButton);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(intent.hasExtra("album")){
                    album=intent.getParcelableExtra("album");
                    xulyThemalbumyeuthich(album);
                }
            }
        });
    }

    private void xulyThemalbumyeuthich(Album album) {
        boolean kt=Object_Json.checkTonTai(1,album.getIdAlbum().intValue());
        if(kt){
            Toast.makeText(this, "ALBUM : "+album.getTenAlbum()+" đã tồn tại trong ds album yêu thích !!", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, " Thêm Album "+album.getTenAlbum()+" vào ds yêu thích thành công", Toast.LENGTH_SHORT).show();
            themBaiHatyeuthich(album);
            updateluotthich(album);
        }
    }
    private void updateluotthich(final Album album) {
        Call<Result> callback=dataService.Update_luotthichalbum(album.getIdAlbum().intValue());
        callback.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                result=response.body();
                if(result!=null){
                    if(result.getResult()==true){
                        Log.d("updateluotthich", "updateluotthich "+album.getTenAlbum()+" thành công !!");

                    }else {
                        Log.d("updateluotthich", "updateluotthich "+album.getTenAlbum()+" thất bại !!");
                    }
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {

            }
        });
    }

    private void themBaiHatyeuthich(final Album album) {
        if( Object_Json.dsAlbumYeuThich.size()<100){
            if(TrangChuActivity.tk !=null){
                Call<Result> callback=dataService.ThemAlbumYeuThich(TrangChuActivity.tk.getIdTaiKhoan(),album.getIdAlbum().intValue());
                callback.enqueue(new Callback<Result>() {
                    @Override
                    public void onResponse(Call<Result> call, Response<Result> response) {
                        result=response.body();
                        if(result!=null){
                            if(result.getResult()==true){
                                Object_Json.dsAlbumYeuThich.add(album);
                                Toast.makeText(DanhSachBaiHatActivity.this, "thêm bài hát "+album.getTenAlbum()+" vào ds yêu thích thành công !!", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(DanhSachBaiHatActivity.this, "thêm bài hát "+album.getTenAlbum()+" vào ds yêu thích KHÔNG thành công !!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Result> call, Throwable t) {

                    }
                });
            }

        }else {
            Toast.makeText(DanhSachBaiHatActivity.this, "Danh sách bài hát yêu thích chỉ giới hạn 100 bài hát nạp thêm Vip để có thể yêu thích nhiều bài hát hơn", Toast.LENGTH_SHORT).show();
        }
    }
    private void getDataAlbums(int idAlbum) {
        //Log.d("ALBUM", "getDataAlbums: "+idAlbum);
        Call<List<BaiHat>>callback=dataService.Getdata_BaiHatTheoAlbum(idAlbum);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                baiHatArrayList= (ArrayList<BaiHat>) response.body();
                Log.d("TAM_LOG","BAIHAT LIST "+baiHatArrayList.get(0).getTenbaihat());
                if(baiHatArrayList.size()>0){
                    setData();
                }
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }
    private void getDataPlaylist(int idPlaylist) {
        //Log.d("ALBUM", "getDataAlbums: "+idAlbum);
        Call<List<BaiHat>>callback=dataService.Getdata_BaiHatTheoPlaylist(idPlaylist);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                baiHatArrayList= (ArrayList<BaiHat>) response.body();
                Log.d("TAM_LOG","BAIHAT LIST "+baiHatArrayList.get(0).getTenbaihat());
                if(baiHatArrayList.size()>0){
                    setData();
                }
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(adapterPlaynhac !=null){
        //    Log.d("BAIHATDANGPLAY", "Play: "+TrangChuActivity.baiHat.getIdBaihat());
            adapterPlaynhac.notifyDataSetChanged();
        }
        addFragment();

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

        if(intent.hasExtra("listcakhuc") && intent.hasExtra("position")){
          quangCaoArrayList=intent.getParcelableArrayListExtra("listcakhuc");
          int vitri=intent.getIntExtra("position",-1);
          if(vitri>=0){
              q=quangCaoArrayList.get(vitri);
                  BaiHat baiHat=new BaiHat(q.getIdBaihat(), q.getTenbaihat(),q.getHinhanhBaihat(),q.getLinkBaibat(),q.getIdCasiBaihat(),q.getIdAlbumBaihat(), q.getIdPlaylistBaihat(),q.getLuotthichBaihat(),q.getLuotngheBaihat(),q.getThoigian(),q.getTencasiBaihat());
                  baiHatArrayList.add(baiHat);


              linkHinh=q.getHinhanhBaihat();
              tentitle=q.getTenbaihat();
              setData();
          }
        }if (intent.hasExtra("album")){

            album=intent.getParcelableExtra("album");
            if(album!=null){
                linkHinh=album.getHinhAlbum();
                tentitle=album.getTenAlbum();
                getDataAlbums(album.getIdAlbum());
            }
        }
        if (intent.hasExtra("playlist")){
            Playlist playlist=null;
            playlist=intent.getParcelableExtra("playlist");
            if(playlist!=null){
                linkHinh=playlist.getHinhPlaylist();
                tentitle=playlist.getTenPlaylist();
                getDataPlaylist(playlist.getIdPlaylist());
            }
        }
    }
}
