package com.example.jerem.appnghenhac.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.jerem.appnghenhac.R;
import com.example.jerem.appnghenhac.activity.CaSiActivity;
import com.example.jerem.appnghenhac.model.CaSi;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterCasi extends RecyclerView.Adapter<AdapterCasi.ViewHolder> {
    Context context;
    ArrayList<CaSi> caSis;
    int layout;
    int mau;
    public AdapterCasi(Context context, ArrayList<CaSi> caSis,int layout,int mau) {
        this.context = context;
        this.caSis = caSis;
        this.layout=layout;
        this.mau=mau;
    }

    @NonNull
    @Override
    public AdapterCasi.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(layout,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterCasi.ViewHolder holder, final int position) {
        Picasso.with(context)
                .load(caSis.get(position).getHinhanhCasi().trim())
                .into(holder.imgCircle_casi);
        holder.txttencasi_casi.setText(caSis.get(position).getTencasy());
        if(mau==0){
            holder.txttencasi_casi.setTextColor(Color.parseColor("#424242"));
        }else {
            holder.txttencasi_casi.setTextColor(Color.parseColor("#F5F5F5"));
        }
        holder.frameCasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,CaSiActivity.class);
                intent.putExtra("casy",caSis.get(position));
                context.startActivity(intent);
            }
        });
    }
    public void TimKiemCaSy(ArrayList<CaSi> caSisTimKiem)
    {
        caSis.clear();
        if(caSisTimKiem !=null){

            caSis=caSisTimKiem;
            notifyDataSetChanged();
        }
    }
    @Override
    public int getItemCount() {
        return caSis.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        FrameLayout frameCasi;
        TextView txttencasi_casi;
        CircleImageView imgCircle_casi;
        public ViewHolder(View itemView) {
            super(itemView);
            frameCasi=itemView.findViewById(R.id.frameCasi);
            txttencasi_casi=itemView.findViewById(R.id.txttencasi_casi);
            imgCircle_casi=itemView.findViewById(R.id.imgCircle_casi);
        }
    }
}
