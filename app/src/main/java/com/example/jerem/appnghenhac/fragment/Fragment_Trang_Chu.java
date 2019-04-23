package com.example.jerem.appnghenhac.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.jerem.appnghenhac.R;
import com.example.jerem.appnghenhac.activity.PlayNhacActivity;
import com.example.jerem.appnghenhac.activity.TrangChuActivity;
import com.example.jerem.appnghenhac.adapter.AdapterAlbum;
import com.example.jerem.appnghenhac.adapter.AdapterBaihat;
import com.example.jerem.appnghenhac.adapter.AdapterBanner;
import com.example.jerem.appnghenhac.adapter.AdapterCasi;
import com.example.jerem.appnghenhac.adapter.AdapterChuDe;
import com.example.jerem.appnghenhac.adapter.AdapterPlaylist;
import com.example.jerem.appnghenhac.model.Album;
import com.example.jerem.appnghenhac.model.BaiHat;
import com.example.jerem.appnghenhac.model.CaSi;
import com.example.jerem.appnghenhac.model.ChuDe;
import com.example.jerem.appnghenhac.model.Object_Json;
import com.example.jerem.appnghenhac.model.Playlist;
import com.example.jerem.appnghenhac.model.QuangCao;
import com.example.jerem.appnghenhac.model.TaiKhoan;
import com.example.jerem.appnghenhac.service.APIService;
import com.example.jerem.appnghenhac.service.DataService;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Trang_Chu extends Fragment {
    View view;
    ViewPager viewPager;
    ArrayList<QuangCao>Banners;

    Button btnclick;

    Object_Json object_json;
    TaiKhoan tk;

    CircleIndicator circleIndicator;
    AdapterBanner adapterBanner;
    Runnable runnable;
    Handler handler;
    int currenitem;

    DataService dataService;

    //PLAYLIST
    RecyclerView lstPlaylist;
    Button btnplaylistmore;
    AdapterPlaylist adapterPlaylist;
    ArrayList<Playlist> playlists;

    //CHUDE
    ArrayList<ChuDe>chuDes;
    RecyclerView lstchude;
    AdapterChuDe adapterChuDe;

    //BAIHAT
    public static ArrayList<BaiHat>baiHats;
    RecyclerView lstBaiHat;
    AdapterBaihat adapterBaihat;

    //ALBUM
    ArrayList<Album>albums;
    RecyclerView lstAlbum;
    AdapterAlbum adapterAlbum;

    //CASY
    ArrayList<CaSi>caSis;
    RecyclerView lstCasi;
    AdapterCasi adapterCasi;

    //GOIY
    ArrayList<BaiHat>baiHatsgoiy;
    RecyclerView lstGoiy;
    AdapterBaihat adapterBaihatgoiy;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.layout_frament_trang_chu,container,false);
        getdataShare();
        addControll();
        getDataQuangCao();
        getDataPlaylist();
        getDataChuDe();
        getData10BaiHat();
        getDataCasi();
        getDataAlbums();
        getDataBiHatGoiy();
        return view;
    }

    private void getdataShare() {
        object_json=new Object_Json(getActivity());
        tk=object_json.GetTaiKhoan();
    }

    private void getDataBiHatGoiy() {
        Call<List<BaiHat>> callback=dataService.Getdata_BaiHatGoiy();
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                baiHatsgoiy= (ArrayList<BaiHat>) response.body();
                Log.d("TAM_LOG","BAIHAT LIST "+baiHats.get(0).getTenbaihat());
                if(baiHatsgoiy.size()>0){
                    RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getActivity());
                    adapterBaihatgoiy=new AdapterBaihat(getActivity(),baiHatsgoiy,R.layout.layout_custom_baihat_timkiem);
                    lstGoiy.setHasFixedSize(true);
                    lstGoiy.setLayoutManager(layoutManager);
                    lstGoiy.setAdapter(adapterBaihatgoiy);
                    adapterBaihatgoiy.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }

    private void getDataCasi() {
       Call<List<CaSi>> callback=dataService.Getdata_Casi();
       callback.enqueue(new Callback<List<CaSi>>() {
           @Override
           public void onResponse(Call<List<CaSi>> call, Response<List<CaSi>> response) {
               caSis= (ArrayList<CaSi>) response.body();
//                Log.d("TAM_LOG","ALBUMS LIST "+albums.get(0).getTenAlbum());
                if(caSis.size()>0){
                    RecyclerView.LayoutManager layoutManager=new GridLayoutManager(getActivity(),3);
                    adapterCasi=new AdapterCasi(getActivity(),caSis,R.layout.layout_custom_casi,0);
                    lstCasi.setHasFixedSize(true);
                    lstCasi.setLayoutManager(layoutManager);
                    lstCasi.setAdapter(adapterCasi);
                    adapterCasi.notifyDataSetChanged();
                }
           }

           @Override
           public void onFailure(Call<List<CaSi>> call, Throwable t) {

           }
       });

    }

    private void addControll() {

        caSis=new ArrayList<>();
        Banners=new ArrayList<>();
        playlists=new ArrayList<>();
        chuDes=new ArrayList<>();
        baiHats=new ArrayList<>();
        albums=new ArrayList<>();
        baiHatsgoiy=new ArrayList<>();

        lstGoiy=view.findViewById(R.id.lstGoiy);
        lstPlaylist=view.findViewById(R.id.lstPlaylist);
        lstchude=view.findViewById(R.id.lstChude);
        lstBaiHat=view.findViewById(R.id.lstBaiHat);
        lstAlbum=view.findViewById(R.id.lstAlbum);
        lstCasi=view.findViewById(R.id.lstCasi);

        dataService=APIService.getService();

        viewPager=view.findViewById(R.id.wiewPager_trangchu);
        circleIndicator=view.findViewById(R.id.indicator_trangchu);
        btnplaylistmore=view.findViewById(R.id.btnplaylistmore);

        btnclick=view.findViewById(R.id.btnclick);
        btnclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),PlayNhacActivity.class);
                intent.putExtra("hello","hello");
                startActivity(intent);
            }
        });
    }
    private void getDataAlbums() {
        Call<List<Album>> callback=dataService.Getdata_Album();
        callback.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                albums= (ArrayList<Album>) response.body();
                Log.d("TAM_LOG","ALBUMS LIST "+albums.get(0).getTenAlbum());
                if(albums.size()>0){
                    RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getActivity(),LinearLayout.HORIZONTAL,false);
                    adapterAlbum=new AdapterAlbum(getActivity(),albums);
                    lstAlbum.setHasFixedSize(true);
                    lstAlbum.setLayoutManager(layoutManager);
                    lstAlbum.setAdapter(adapterAlbum);
                    adapterAlbum.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {

            }
        });
    }
    private void getDataChuDe() {
        Call<List<ChuDe>> callback=dataService.Getdata_ChuDe();
        callback.enqueue(new Callback<List<ChuDe>>() {
            @Override
            public void onResponse(Call<List<ChuDe>> call, Response<List<ChuDe>> response) {
                chuDes= (ArrayList<ChuDe>) response.body();
                Log.d("TAM_LOG","CHUDE LIST "+chuDes.get(0).getTenChude());
                if(chuDes.size()>0){
                    RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getActivity());
                    //RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);  dang horizontai,false la ko roll
                    adapterChuDe=new AdapterChuDe(getActivity(),chuDes);
                    lstchude.setHasFixedSize(true);
                    lstchude.setLayoutManager(layoutManager);
                    lstchude.setAdapter(adapterChuDe);
                    adapterChuDe.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<ChuDe>> call, Throwable t) {

            }
        });
    }

    private void getDataPlaylist() {
        Call<List<Playlist>> callback=dataService.Getdata_Playlist();
        callback.enqueue(new Callback<List<Playlist>>() {
            @Override
            public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {
                playlists= (ArrayList<Playlist>) response.body();
                Log.d("TAM_LOG","PLAYLIST LIST "+playlists.get(0).getTenPlaylist());
                if(playlists.size()>0){
                    RecyclerView.LayoutManager layoutManager=new GridLayoutManager(getActivity(),2);
                    adapterPlaylist=new AdapterPlaylist(getActivity(),playlists);
                    lstPlaylist.setHasFixedSize(true);
                    lstPlaylist.setLayoutManager(layoutManager);
                    lstPlaylist.setAdapter(adapterPlaylist);
                    adapterPlaylist.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Playlist>> call, Throwable t) {

            }
        });
    }

    private void getData10BaiHat() {
        Call<List<BaiHat>> callback=dataService.Getdata_BaiHat();
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                baiHats= (ArrayList<BaiHat>) response.body();
                Log.d("TAM_LOG","BAIHAT LIST "+baiHats.get(0).getTenbaihat());
                if(baiHats.size()>0){
                    RecyclerView.LayoutManager layoutManager=new GridLayoutManager(getActivity(),3);
                    adapterBaihat=new AdapterBaihat(getActivity(),baiHats,R.layout.layout_custom_baihat);
                    lstBaiHat.setHasFixedSize(true);
                    lstBaiHat.setLayoutManager(layoutManager);
                    lstBaiHat.setAdapter(adapterBaihat);
                    adapterBaihat.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }

    private void getDataQuangCao() {

        Call<List<QuangCao>> callback=dataService.Getdata_Banner();
        callback.enqueue(new Callback<List<QuangCao>>() {
            @Override
            public void onResponse(Call<List<QuangCao>> call, Response<List<QuangCao>> response) {
                Banners= (ArrayList<QuangCao>) response.body();
                Log.d("TAM_LOG","QUANG CAO LIST "+Banners.get(0).getTenbaihat());

                if(Banners.size()>0){
                    adapterBanner=new AdapterBanner(getActivity(),Banners);
                    viewPager.setAdapter(adapterBanner);
                    circleIndicator.setViewPager(viewPager);
                    handler=new Handler();
                    runnable=new Runnable() {
                        @Override
                        public void run() {
                           currenitem=viewPager.getCurrentItem();
                           currenitem++;
                           if(currenitem >= viewPager.getAdapter().getCount()){
                               currenitem=0;
                           }
                           viewPager.setCurrentItem(currenitem,true);
                           handler.postDelayed(runnable,4500);
                        }
                    };
                    handler.postDelayed(runnable,4500);
                }
            }

            @Override
            public void onFailure(Call<List<QuangCao>> call, Throwable t) {
                Log.d("TAM_LOG",t.toString());
            }
        });
    }
}
