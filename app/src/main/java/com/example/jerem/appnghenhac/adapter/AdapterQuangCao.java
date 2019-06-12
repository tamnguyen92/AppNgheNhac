package com.example.jerem.appnghenhac.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.jerem.appnghenhac.R;
import com.example.jerem.appnghenhac.activity.DanhSachBaiHatActivity;
import com.example.jerem.appnghenhac.model.QuangCao;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterQuangCao extends  RecyclerView.Adapter<AdapterQuangCao.ViewHolder>  {
    Context context;
    ArrayList<QuangCao> quangCaoArrayList;

    public AdapterQuangCao(Context context, ArrayList<QuangCao> quangCaoArrayList) {
        this.context = context;
        this.quangCaoArrayList = quangCaoArrayList;
    }

    @NonNull
    @Override
    public AdapterQuangCao.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.layout_custom_banner,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterQuangCao.ViewHolder holder, final int position) {
        Picasso.with(context)
                .load(quangCaoArrayList.get(position).getHinhQuangcao().trim())
                .into(holder.imageview_custom_banner);
        Picasso.with(context)
                .load(quangCaoArrayList.get(position).getHinhanhBaihat().trim())
                .into(holder.imageview_banner);
        holder.txttitlebaihat.setText(quangCaoArrayList.get(position).getTenbaihat());
        holder.txtnoidungbaihat.setText(" Ca khúc mới nhất vừa mới phát hành của ca sỹ : "+quangCaoArrayList.get(position).getTencasiBaihat());

        holder.frame_banner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  Toast.makeText(context,"vi tri : "+position,Toast.LENGTH_LONG).show();
                Intent intent=new Intent(context,DanhSachBaiHatActivity.class);
                intent.putExtra("listcakhuc",quangCaoArrayList);
                intent.putExtra("position",position);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return quangCaoArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout frame_banner;
        ImageView imageview_banner;
        ImageView imageview_custom_banner;
        TextView txttitlebaihat;
        TextView txtnoidungbaihat;
        public ViewHolder(View itemView) {
            super(itemView);
             frame_banner=itemView.findViewById(R.id.frame_banner);
             imageview_banner=itemView.findViewById(R.id.imageview_banner);
             imageview_custom_banner=itemView.findViewById(R.id.imageview_custom_banner);
             txttitlebaihat=itemView.findViewById(R.id.txttitlebaihat);
             txtnoidungbaihat=itemView.findViewById(R.id.txtnoidungbaihat);
        }
    }
}
