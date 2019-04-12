package com.example.jerem.appnghenhac.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jerem.appnghenhac.R;
import com.example.jerem.appnghenhac.model.ChuDe;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterChuDe extends RecyclerView.Adapter<AdapterChuDe.ViewHolder> {
    Context context;
    ArrayList<ChuDe>chuDes;

    public AdapterChuDe(Context context, ArrayList<ChuDe> chuDes) {
        this.context = context;
        this.chuDes = chuDes;
    }

    @NonNull
    @Override
    public AdapterChuDe.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.layout_custom_chude,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterChuDe.ViewHolder holder, final int position) {
        Picasso.with(context)
                .load(chuDes.get(position).getHinhChude().trim())
                .into(holder.imgchude);
        holder.txtchude.setText(chuDes.get(position).getTenChude());
        holder.frame_chude.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"vi tri : "+position,Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return chuDes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgchude;
        TextView txtchude;
        FrameLayout frame_chude;
        public ViewHolder(View itemView) {
            super(itemView);
            imgchude=itemView.findViewById(R.id.imgchude);
            txtchude=itemView.findViewById(R.id.txttenchude);
            frame_chude=itemView.findViewById(R.id.frame_chude);
        }
    }
}
