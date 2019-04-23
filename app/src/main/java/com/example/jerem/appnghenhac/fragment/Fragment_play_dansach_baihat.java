package com.example.jerem.appnghenhac.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jerem.appnghenhac.CallbackPlay;
import com.example.jerem.appnghenhac.PlayMusic.PlayMusic2;
import com.example.jerem.appnghenhac.R;
import com.example.jerem.appnghenhac.activity.PlayNhacActivity;
import com.example.jerem.appnghenhac.adapter.AdapterPlaynhac;

public class Fragment_play_dansach_baihat extends Fragment implements CallbackPlay {
    View view;
    RecyclerView lstdanhsachplaynhac;
    public  static AdapterPlaynhac adapterPlaynhac;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.layout_fragment_play_dansach_baihat,container,false);
        addControll();
        addData();
        return view;
    }

    private void addData() {
        if(PlayMusic2.listBaihat.size()>0){
            adapterPlaynhac=new AdapterPlaynhac(getActivity(),PlayMusic2.listBaihat,this);
            RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getActivity());
            //RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);  dang horizontai,false la ko roll

            lstdanhsachplaynhac.setHasFixedSize(true);
            lstdanhsachplaynhac.setLayoutManager(layoutManager);
            lstdanhsachplaynhac.setAdapter(adapterPlaynhac);
            adapterPlaynhac.notifyDataSetChanged();
        }

    }

    private void addControll() {
        lstdanhsachplaynhac=view.findViewById(R.id.lstdanhsachplaynhac);

    }

    @Override
    public void complete() {

    }

    @Override
    public void play_nhac_tu_ds() {
        ((PlayNhacActivity)getActivity()).playNhacfromlist();
    }
}
