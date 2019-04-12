package com.example.jerem.appnghenhac.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.jerem.appnghenhac.CallbackPlay;
import com.example.jerem.appnghenhac.PlayMusic.PlayMusic2;
import com.example.jerem.appnghenhac.R;
import com.example.jerem.appnghenhac.model.BaiHat;

import java.util.ArrayList;

//txttenbaihatplay,txttencasiplay,txtsothutu
public class AdapterPlaynhac extends RecyclerView.Adapter<AdapterPlaynhac.ViewHolder>{
    Context context;
    ArrayList<BaiHat>baiHats;
    CallbackPlay callbackPlay=null;

    public AdapterPlaynhac(Context context, ArrayList<BaiHat> baiHats, CallbackPlay callbackPlay) {
        this.context = context;
        this.baiHats = baiHats;
        this.callbackPlay=callbackPlay;
    }

    @NonNull
    @Override
    public AdapterPlaynhac.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.layout_custom_play_danhsach_baihat,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterPlaynhac.ViewHolder holder, final int position) {
        int stt=position+1;
        BaiHat baiHat=baiHats.get(position);
        holder.txttencasiplay.setText(baiHat.getTencasiBaihat());
        holder.txttenbaihatplay.setText(baiHat.getTenbaihat());
        holder.txtsothutu.setText(stt+"");
        if(position==PlayMusic2.position){
            holder.imggif.setVisibility(View.VISIBLE);
            Glide.with(context)
                    .load(R.drawable.gif_radio)// you may not need this
                    .into(holder.imggif);
             holder.txtsothutu.setVisibility(View.INVISIBLE);
        }else {

            holder.imggif.setImageResource(R.drawable.replay_black);
            holder.imggif.setVisibility(View.INVISIBLE);
            holder.txtsothutu.setVisibility(View.VISIBLE);
        }
        holder.fram_danhsachbaihat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(callbackPlay!=null){
                   PlayMusic2.position=position;
                   callbackPlay.play_nhac_tu_ds();
               }
            }
        });

    }

    @Override
    public int getItemCount() {
        return baiHats.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
      TextView txttenbaihatplay,txttencasiplay,txtsothutu;
      LinearLayout fram_danhsachbaihat;
      ImageView imggif;
        public ViewHolder(View itemView) {
            super(itemView);
            txttencasiplay=itemView.findViewById(R.id.txttencasiplay); fram_danhsachbaihat=itemView.findViewById(R.id.fram_danhsachbaihat);
            txttenbaihatplay=itemView.findViewById(R.id.txttenbaihatplay); txtsothutu=itemView.findViewById(R.id.txtsothutu);
            imggif=itemView.findViewById(R.id.imggif);

        }
    }
}
