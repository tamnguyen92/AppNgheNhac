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

import com.example.jerem.appnghenhac.R;
import com.example.jerem.appnghenhac.activity.TrangChuActivity;
import com.example.jerem.appnghenhac.adapter.AdapterBaihat;
import com.example.jerem.appnghenhac.adapter.AdapterCasi;
import com.example.jerem.appnghenhac.model.BaiHat;
import com.example.jerem.appnghenhac.model.CaSi;
import com.example.jerem.appnghenhac.service.APIService;
import com.example.jerem.appnghenhac.service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Tim_Kiem extends Fragment {
    View view;
    RecyclerView lstTimkiemBaihat;
    AdapterBaihat adapterBaihat;
    ArrayList<BaiHat>baiHatsTimKiem;

    RecyclerView lstTimkiemNgheSi;
    ArrayList<CaSi>casisTimKiem;
    AdapterCasi adapterCasi;

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


        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getActivity());
        adapterBaihat=new AdapterBaihat(getActivity(),baiHatsTimKiem,R.layout.layout_custom_baihat_timkiem);
        lstTimkiemBaihat.setHasFixedSize(true);
        lstTimkiemBaihat.setLayoutManager(layoutManager);
        lstTimkiemBaihat.setAdapter(adapterBaihat);
        adapterBaihat.notifyDataSetChanged();

        RecyclerView.LayoutManager layoutManagerAlbum=new LinearLayoutManager(getActivity());
        adapterCasi=new AdapterCasi(getActivity(),casisTimKiem,R.layout.layout_custom_casi_horizontal,0);
        lstTimkiemNgheSi.setHasFixedSize(true);
        lstTimkiemNgheSi.setLayoutManager(layoutManagerAlbum);
        lstTimkiemNgheSi.setAdapter(adapterCasi);
        adapterCasi.notifyDataSetChanged();
    }

    public void TimKiem(CharSequence s){
        if(s.length() != 0 && s.length()>=2){
            Log.d("TEXT", "onTextChanged: "+s);
            TimKiemBaiHat(s.toString());
            TimKiemCasi(s.toString());
        }if(s.length() != 0 && s.length()< 2){
            Log.d("TEXT", "onTextChanged: "+s);
            baiHatsTimKiem.clear();
            casisTimKiem.clear();
            adapterBaihat.TimKiemBaiHat(baiHatsTimKiem);
        }
    }

    private void TimKiemBaiHat(String s) {
        Call<List<BaiHat>> callback=dataService.Getdata_TimKiemBaiHat(s);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                baiHatsTimKiem= (ArrayList<BaiHat>) response.body();
               // Log.d("TAM_LOG","BAIHAT LIST "+baiHats.get(0).getTenbaihat());
                if(baiHatsTimKiem.size()>0){
                   adapterBaihat.TimKiemBaiHat(baiHatsTimKiem);
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
                if(casisTimKiem.size()>0){
                    adapterCasi.TimKiemCaSy(casisTimKiem);
                }
            }

            @Override
            public void onFailure(Call<List<CaSi>> call, Throwable t) {

            }
        });
    }
}
