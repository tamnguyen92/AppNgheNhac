package com.example.jerem.appnghenhac.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jerem.appnghenhac.R;
import com.example.jerem.appnghenhac.activity.PlayNhacActivity;
import com.example.jerem.appnghenhac.activity.TrangChuActivity;
import com.example.jerem.appnghenhac.model.BaiHat;
import com.example.jerem.appnghenhac.model.Object_Json;
import com.example.jerem.appnghenhac.model.Result;
import com.example.jerem.appnghenhac.service.APIService;
import com.example.jerem.appnghenhac.service.DataService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterBaihat extends RecyclerView.Adapter<AdapterBaihat.ViewHolder> {
    Context context;
    ArrayList<BaiHat>baiHats;
    int layout;
    DataService dataService;
    Result result=null;
    int chon=-1;
    public AdapterBaihat(Context context, ArrayList<BaiHat> baiHats, int layout) {
        this.context = context;
        this.baiHats = baiHats;
        this.layout=layout;
        dataService=APIService.getService();
    }

    @NonNull

    @Override
    public AdapterBaihat.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(layout,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterBaihat.ViewHolder holder, final int positions) {
        Log.d("VITRI", "onClick: ten"+baiHats.get(positions).getTenbaihat());
        Picasso.with(context)
                .load(baiHats.get(positions).getHinhanhBaihat().trim())
                .into(holder.imghinhbaihat);
        holder.txttenbaihat.setText(baiHats.get(positions).getTenbaihat());
        holder.txttencasi.setText(baiHats.get(positions).getTencasiBaihat());
        holder.txtluotnghe.setText(baiHats.get(positions).getLuotngheBaihat()+"");

        holder.frame_baihat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("VITRI", "onClick: "+positions);
                Intent intent=new Intent(context,PlayNhacActivity.class);
                intent.putExtra("cakhuc",baiHats.get(positions));
                context.startActivity(intent);
            }
        });
        if(positions==chon){
            holder.imglove.setVisibility(View.GONE);
        }else {
            holder.imglove.setVisibility(View.VISIBLE);
        }
        holder.imglove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              chon=positions;
              notifyDataSetChanged();
                boolean kt=Object_Json.checkTonTai(0,baiHats.get(positions).getIdBaihat());
                if(kt){
                    Toast.makeText(context, ""+baiHats.get(positions).getTenbaihat()+"đã tồn tại trong ds bai hat yeu thich", Toast.LENGTH_SHORT).show();
                }else {

                    themBaiHatyeuthich(baiHats.get(positions));
                    updateluotthich(baiHats.get(positions));
                }

            }
        });
    }
    private void updateluotthich(final BaiHat baihat) {
        Call<Result> callback=dataService.Update_luotthichbaihat(baihat.getIdBaihat().intValue());
        callback.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                result=response.body();
                if(result!=null){
                    if(result.getResult()==true){
                        Log.d("updateluotthich", "updateluotthich "+baihat.getTenbaihat()+" thành công !!");

                    }else {
                        Log.d("updateluotthich", "updateluotthich "+baihat.getTenbaihat()+" thất bại !!");
                    }
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {

            }
        });
    }

    private void themBaiHatyeuthich(final BaiHat baihat) {

        if( Object_Json.dsBaiHatYeuThich.size()<100){
            if(TrangChuActivity.tk !=null){
                Call<Result> callback=dataService.ThemBaiHatYeuThich(TrangChuActivity.tk.getIdTaiKhoan(),baihat.getIdBaihat().intValue());
                callback.enqueue(new Callback<Result>() {
                    @Override
                    public void onResponse(Call<Result> call, Response<Result> response) {
                        result=response.body();
                        if(result!=null){
                            if(result.getResult()==true){
                                Collections.reverse( Object_Json.dsBaiHatYeuThich);
                                Object_Json.dsBaiHatYeuThich.add(baihat);
                                Collections.reverse( Object_Json.dsBaiHatYeuThich);
                                Toast.makeText(context, "thêm bài hát "+baihat.getTenbaihat()+" vào ds yêu thích thành công !!", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(context, "thêm bài hát "+baihat.getTenbaihat()+" vào ds yêu thích KHÔNG thành công !!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Result> call, Throwable t) {

                    }
                });
            }

        }else {
            Toast.makeText(context, "Danh sách bài hát yêu thích chỉ giới hạn 100 bài hát nạp thêm Vip để có thể yêu thích nhiều bài hát hơn", Toast.LENGTH_SHORT).show();
        }
    }
public void TimKiemBaiHat(ArrayList<BaiHat>baiHatsTimKiem)
{
    baiHats.clear();
    if(baiHatsTimKiem !=null){

        baiHats=baiHatsTimKiem;
        for(BaiHat b : baiHats){
            Log.d("VITRI", "onClick: ten"+b.getTenbaihat());
        }
        notifyDataSetChanged();
    }
}
    @Override
    public int getItemCount() {
        return baiHats.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //imghinhbaihat txttenbaihat txttencasi frame_baihat,txtluotnghe
        ImageView imghinhbaihat,imglove;
        TextView txttenbaihat,txttencasi,txtluotnghe;
        FrameLayout frame_baihat;
        public ViewHolder(View itemView) {
            super(itemView);
            imghinhbaihat=itemView.findViewById(R.id.imghinhbaihat);
            imglove=itemView.findViewById(R.id.imglove);
            txttenbaihat=itemView.findViewById(R.id.txttenbaihat);
            txttencasi=itemView.findViewById(R.id.txttencasi);
            txtluotnghe=itemView.findViewById(R.id.txtluotnghe);
            frame_baihat=itemView.findViewById(R.id.frame_baihat);
        }
    }
}
