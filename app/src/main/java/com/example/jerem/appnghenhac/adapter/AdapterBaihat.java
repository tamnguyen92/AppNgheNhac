package com.example.jerem.appnghenhac.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jerem.appnghenhac.R;
import com.example.jerem.appnghenhac.activity.PlayNhacActivity;
import com.example.jerem.appnghenhac.model.BaiHat;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterBaihat extends RecyclerView.Adapter<AdapterBaihat.ViewHolder> {
    Context context;
    ArrayList<BaiHat>baiHats;

    public AdapterBaihat(Context context, ArrayList<BaiHat> baiHats) {
        this.context = context;
        this.baiHats = baiHats;
    }

    @NonNull

    @Override
    public AdapterBaihat.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.layout_custom_baihat,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterBaihat.ViewHolder holder, final int positions) {
        Picasso.with(context)
                .load(baiHats.get(positions).getHinhanhBaihat().trim())
                .into(holder.imghinhbaihat);
        holder.txttenbaihat.setText(baiHats.get(positions).getTenbaihat());
        holder.txttencasi.setText(baiHats.get(positions).getTencasiBaihat());
        holder.txtluotnghe.setText(baiHats.get(positions).getLuotngheBaihat()+"");

        holder.frame_baihat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,PlayNhacActivity.class);
                intent.putExtra("cakhuc",baiHats.get(positions));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return baiHats.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //imghinhbaihat txttenbaihat txttencasi frame_baihat,txtluotnghe
        ImageView imghinhbaihat;
        TextView txttenbaihat,txttencasi,txtluotnghe;
        FrameLayout frame_baihat;
        public ViewHolder(View itemView) {
            super(itemView);
            imghinhbaihat=itemView.findViewById(R.id.imghinhbaihat);
            txttenbaihat=itemView.findViewById(R.id.txttenbaihat);
            txttencasi=itemView.findViewById(R.id.txttencasi);
            txtluotnghe=itemView.findViewById(R.id.txtluotnghe);
            frame_baihat=itemView.findViewById(R.id.frame_baihat);
        }
    }
}
