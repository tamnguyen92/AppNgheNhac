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
import android.widget.Toast;

import com.example.jerem.appnghenhac.R;
import com.example.jerem.appnghenhac.activity.DanhSachBaiHatActivity;
import com.example.jerem.appnghenhac.model.Album;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterAlbum extends RecyclerView.Adapter<AdapterAlbum.ViewHolder>  {
    Context context;
    ArrayList<Album>albums;

    public AdapterAlbum(Context context, ArrayList<Album> albums) {
        this.context = context;
        this.albums = albums;
    }

    @NonNull

    @Override
    public AdapterAlbum.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.layout_custom_album,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterAlbum.ViewHolder holder, final int position) {
        Picasso.with(context)
                .load(albums.get(position).getHinhAlbum().trim())
                .into(holder.imghinhalbum);
        holder.txttenalbum.setText(albums.get(position).getTenAlbum());
        holder.txttencasialbum.setText(albums.get(position).getTencasy());
       holder.txtluotnghealbum.setText(albums.get(position).getLuotngheAlbum()+"");

        holder.frame_album.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,DanhSachBaiHatActivity.class);
                intent.putExtra("album",albums.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return albums.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imghinhalbum;
        TextView txttenalbum,txttencasialbum,txtluotnghealbum;
        FrameLayout frame_album;
        public ViewHolder(View itemView) {
            super(itemView);
            imghinhalbum=itemView.findViewById(R.id.imghinhalbum);
            txttenalbum=itemView.findViewById(R.id.txttenalbum);
            txttencasialbum=itemView.findViewById(R.id.txttencasialbum);
            txtluotnghealbum=itemView.findViewById(R.id.txtluotnghealbum);
            frame_album=itemView.findViewById(R.id.frame_album);
        }
    }
}
