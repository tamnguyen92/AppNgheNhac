package com.example.jerem.appnghenhac.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.jerem.appnghenhac.PlayMusic.PlayMusic2;
import com.example.jerem.appnghenhac.R;
import com.example.jerem.appnghenhac.activity.PlayNhacActivity;
import com.example.jerem.appnghenhac.activity.TrangChuActivity;
import com.example.jerem.appnghenhac.model.Album;
import com.example.jerem.appnghenhac.model.BaiHat;
import com.example.jerem.appnghenhac.model.Object_Json;
import com.example.jerem.appnghenhac.model.Result;
import com.example.jerem.appnghenhac.service.APIService;
import com.example.jerem.appnghenhac.service.DataService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//txttenbaihatplay,txttencasiplay,txtsothutu
public class AdapterPlaynhacList extends RecyclerView.Adapter<AdapterPlaynhacList.ViewHolder>{
    Context context;
    ArrayList<BaiHat>baiHats;
    DataService dataService;
    Result result=null;
    public AdapterPlaynhacList(Context context, ArrayList<BaiHat> baiHats) {
        dataService=APIService.getService();
        this.context = context;
        this.baiHats = baiHats;

    }

    @NonNull
    @Override
    public AdapterPlaynhacList.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.layout_custom_play_danhsach_baihat2,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterPlaynhacList.ViewHolder holder, final int position) {
        int stt=position+1;
        BaiHat baiHat=baiHats.get(position);
        holder.txttencasiplay.setText(baiHat.getTencasiBaihat());
        holder.txttenbaihatplay.setText(baiHat.getTenbaihat());
        holder.txtsothutu.setText(stt+"");

        if(TrangChuActivity.baiHat !=null){
            Log.d("BAIHATDANGPLAY", "Play: "+TrangChuActivity.baiHat.getIdBaihat()+"-"+baiHats.get(position).getIdBaihat());
            if(baiHats.get(position).getIdBaihat().intValue() == TrangChuActivity.baiHat.getIdBaihat().intValue()){
                Log.d("BAIHATDANGPLAY", "Play:bang nhau "+TrangChuActivity.baiHat.getIdBaihat()+"-"+baiHats.get(position).getIdBaihat());
                holder.imggif.setVisibility(View.VISIBLE);
                Glide.with(context)
                        .load(R.drawable.gif_radio1)// you may not need this
                        .into(holder.imggif);
                holder.txtsothutu.setVisibility(View.INVISIBLE);

            }  else {
                Log.d("BAIHATDANGPLAY", "Play: khong bang nhau "+TrangChuActivity.baiHat.getIdBaihat()+"-"+baiHats.get(position).getIdBaihat());
                holder.imggif.setImageResource(R.drawable.replay_black);
                holder.imggif.setVisibility(View.INVISIBLE);
                holder.txtsothutu.setVisibility(View.VISIBLE);
            }

        }
        holder.fram_danhsachbaihat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,PlayNhacActivity.class);
                intent.putExtra("listcakhuc",baiHats);
                PlayMusic2.position=position;
                context.startActivity(intent);
            }
        });
        holder.imglove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean kt=Object_Json.checkTonTai(0,baiHats.get(position).getIdBaihat());
                if(kt){
                    Toast.makeText(context, ""+baiHats.get(position).getTenbaihat()+"đã tồn tại trong ds bai hat yeu thich", Toast.LENGTH_SHORT).show();
                }else {

                    themBaiHatyeuthich(baiHats.get(position));
                    updateluotthich(baiHats.get(position));
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
                                Object_Json.dsBaiHatYeuThich.add(baihat);
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

    @Override
    public int getItemCount() {
        return baiHats.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
      TextView txttenbaihatplay,txttencasiplay,txtsothutu;
      LinearLayout fram_danhsachbaihat;
      ImageView imggif,imglove;
        public ViewHolder(View itemView) {
            super(itemView);
            txttencasiplay=itemView.findViewById(R.id.txttencasiplay); fram_danhsachbaihat=itemView.findViewById(R.id.fram_danhsachbaihat);
            txttenbaihatplay=itemView.findViewById(R.id.txttenbaihatplay); txtsothutu=itemView.findViewById(R.id.txtsothutu);
            imggif=itemView.findViewById(R.id.imggif);
            imglove=itemView.findViewById(R.id.imglove);

        }
    }
}
