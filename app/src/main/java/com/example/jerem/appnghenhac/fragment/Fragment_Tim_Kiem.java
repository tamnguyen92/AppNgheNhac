package com.example.jerem.appnghenhac.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jerem.appnghenhac.R;
import com.example.jerem.appnghenhac.activity.TrangChuActivity;
import com.example.jerem.appnghenhac.adapter.AdapterAlbum;
import com.example.jerem.appnghenhac.adapter.AdapterBaihat;
import com.example.jerem.appnghenhac.adapter.AdapterCasi;
import com.example.jerem.appnghenhac.adapter.AdapterPlaylist;
import com.example.jerem.appnghenhac.model.Album;
import com.example.jerem.appnghenhac.model.BaiHat;
import com.example.jerem.appnghenhac.model.CaSi;
import com.example.jerem.appnghenhac.model.Playlist;
import com.example.jerem.appnghenhac.service.APIService;
import com.example.jerem.appnghenhac.service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Tim_Kiem extends Fragment {
    View view;
    TextView txtbaihat,txtnghesi,txtalbum,txtplaylist;
    RecyclerView lstTimkiemBaihat;
    AdapterBaihat adapterBaihat;
    ArrayList<BaiHat>baiHatsTimKiem;

    RecyclerView lstTimkiemNgheSi;
    ArrayList<CaSi>casisTimKiem;
    AdapterCasi adapterCasi;

    RecyclerView lstTimkiemPlaylist,lstTimkiemAlbum;
    ArrayList<Album>albums;
    ArrayList<Playlist>playlists;
    AdapterAlbum adapterAlbum;
    AdapterPlaylist adapterPlaylist;

    DataService dataService;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.layout_frament_tim_kiem,container,false);
        addControll();
        return view;
    }

    private void addControll() {
        dataService=APIService.getService();
        lstTimkiemBaihat=view.findViewById(R.id.lstTimkiemBaihat);
        baiHatsTimKiem=new ArrayList<>();

        lstTimkiemNgheSi=view.findViewById(R.id.lstTimkiemNgheSi);
        casisTimKiem=new ArrayList<>();

        lstTimkiemAlbum=view.findViewById(R.id.lstTimkiemAlbum);
        albums=new ArrayList<>();

        lstTimkiemPlaylist=view.findViewById(R.id.lstTimkiemPlaylist);
        playlists=new ArrayList<>();

        txtbaihat=view.findViewById(R.id.txtbaihat);
        txtnghesi=view.findViewById(R.id.txtnghesi);
        txtalbum=view.findViewById(R.id.txtalbum);
        txtplaylist=view.findViewById(R.id.txtplaylist);

        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getActivity());
        adapterBaihat=new AdapterBaihat(getActivity(),baiHatsTimKiem,R.layout.layout_custom_baihat_timkiem);
        lstTimkiemBaihat.setHasFixedSize(true);
        lstTimkiemBaihat.setLayoutManager(layoutManager);
        lstTimkiemBaihat.setAdapter(adapterBaihat);
        adapterBaihat.notifyDataSetChanged();

        RecyclerView.LayoutManager layoutManagerCasi=new LinearLayoutManager(getActivity());
        adapterCasi=new AdapterCasi(getActivity(),casisTimKiem,R.layout.layout_custom_casi_horizontal,0);
        lstTimkiemNgheSi.setHasFixedSize(true);
        lstTimkiemNgheSi.setLayoutManager(layoutManagerCasi);
        lstTimkiemNgheSi.setAdapter(adapterCasi);
        adapterCasi.notifyDataSetChanged();


        RecyclerView.LayoutManager layoutManagerAlbum=new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL, false);
        adapterAlbum=new AdapterAlbum(getActivity(),albums);
        lstTimkiemAlbum.setHasFixedSize(true);
        lstTimkiemAlbum.setLayoutManager(layoutManagerAlbum);
        lstTimkiemAlbum.setAdapter(adapterAlbum);
        adapterAlbum.notifyDataSetChanged();


        RecyclerView.LayoutManager layoutManagerPlaylist=new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL, false);
        adapterPlaylist=new AdapterPlaylist(getActivity(),playlists);
        lstTimkiemPlaylist.setHasFixedSize(true);
        lstTimkiemPlaylist.setLayoutManager(layoutManagerPlaylist);
        lstTimkiemPlaylist.setAdapter(adapterPlaylist);
        adapterPlaylist.notifyDataSetChanged();
    }

    public void TimKiem(CharSequence s){

        if(s.length()>=2){
            Log.d("TEXTCHANE", "onTextChanged: "+s);
            TimKiemBaiHat(s.toString());
            TimKiemCasi(s.toString());
            TimKiemAlbum(s.toString());
            TimKiemPlaylist(s.toString());
        }else {
            baiHatsTimKiem=new ArrayList<>();
            casisTimKiem=new ArrayList<>();
            albums=new ArrayList<>();
            playlists=new ArrayList<>();

            txtalbum.setVisibility(View.GONE);
            txtbaihat.setVisibility(View.GONE);
            txtnghesi.setVisibility(View.GONE);
            txtplaylist.setVisibility(View.GONE);

            Log.d("TEXTCHANE", "size "+baiHatsTimKiem.size());
            Log.d("TEXTCHANE", "size "+casisTimKiem.size());
            Log.d("TEXTCHANE", "size "+albums.size());
            adapterBaihat.TimKiemBaiHat(baiHatsTimKiem);
            adapterCasi.TimKiemCaSy(casisTimKiem);
            adapterAlbum.TimKiemAlbum(albums);
            adapterPlaylist.TimKiemPlaylist(playlists);
        }
        //if(s.length() != 0 && s.length()< 2){
//            Log.d("TEXT", "onTextChanged: "+s);
//            baiHatsTimKiem=new ArrayList<>();
//            casisTimKiem=new ArrayList<>();
//            albums=new ArrayList<>();
//            playlists=new ArrayList<>();
//
//            adapterBaihat.TimKiemBaiHat(baiHatsTimKiem);
//            adapterCasi.TimKiemCaSy(casisTimKiem);
//            adapterAlbum.TimKiemAlbum(albums);
//            adapterPlaylist.TimKiemPlaylist(playlists);
//        }
    }

    private void TimKiemAlbum(String s) {
        Call<List<Album>> callback=dataService.Getdata_TimKiemAlbum(s);
        callback.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                albums= (ArrayList<Album>) response.body();
                 Log.d("TAM_LOGSEARCH","BAIHAT LIST "+albums.size());
                if(albums!=null){
                    adapterAlbum.TimKiemAlbum(albums);
                }
                if(albums.size()>0){
                    txtalbum.setVisibility(View.VISIBLE);
                }else {
                    txtalbum.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {

            }
        });
    }
    private void TimKiemPlaylist(String s) {
        Call<List<Playlist>> callback=dataService.Getdata_TimKiemPlaylist(s);
        callback.enqueue(new Callback<List<Playlist>>() {
            @Override
            public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {
                playlists= (ArrayList<Playlist>) response.body();
                // Log.d("TAM_LOG","BAIHAT LIST "+baiHats.get(0).getTenbaihat());
                if(playlists!=null){
                    adapterPlaylist.TimKiemPlaylist(playlists);
                }
                if(playlists.size()>0){
                    txtplaylist.setVisibility(View.VISIBLE);
                }else {
                    txtplaylist.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<List<Playlist>> call, Throwable t) {

            }
        });

    }
    private void TimKiemBaiHat(String s) {
        Call<List<BaiHat>> callback=dataService.Getdata_TimKiemBaiHat(s);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                baiHatsTimKiem= (ArrayList<BaiHat>) response.body();
               // Log.d("TAM_LOG","BAIHAT LIST "+baiHats.get(0).getTenbaihat());
                if(baiHatsTimKiem != null){
                   adapterBaihat.TimKiemBaiHat(baiHatsTimKiem);
                }
                if(baiHatsTimKiem.size()>0){
                    txtbaihat.setVisibility(View.VISIBLE);
                }else {
                    txtbaihat.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }

    private void TimKiemCasi(String s) {
        Call<List<CaSi>> callback=dataService.Getdata_TimKiemCaSi(s);
        callback.enqueue(new Callback<List<CaSi>>() {
            @Override
            public void onResponse(Call<List<CaSi>> call, Response<List<CaSi>> response) {
                casisTimKiem= (ArrayList<CaSi>) response.body();
                // Log.d("TAM_LOG","BAIHAT LIST "+baiHats.get(0).getTenbaihat());
                if(casisTimKiem !=null){
                    adapterCasi.TimKiemCaSy(casisTimKiem);
                }
                if(casisTimKiem.size()>0){
                    txtnghesi.setVisibility(View.VISIBLE);
                }else {
                    txtnghesi.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<List<CaSi>> call, Throwable t) {

            }
        });
    }
}
