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
import com.example.jerem.appnghenhac.activity.PlayNhacActivity;
import com.example.jerem.appnghenhac.fragment.Fragment_Trang_Chu;
import com.example.jerem.appnghenhac.model.Playlist;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterPlaylist extends RecyclerView.Adapter<AdapterPlaylist.ViewHolder>  {
    Context context;
    ArrayList<Playlist> playlists;

    public AdapterPlaylist(Context context, ArrayList<Playlist> playlists) {
        this.context = context;
        this.playlists = playlists;
    }

    @NonNull
    @Override
    public AdapterPlaylist.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.layout_custom_playlist,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterPlaylist.ViewHolder holder, final int position) {

        Picasso.with(context)
                .load(playlists.get(position).getHinhPlaylist().trim())
                .into(holder.imgplaylist);
        holder.txttenplaylist.setText(playlists.get(position).getTenPlaylist());
        holder.frame_playlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"vi tri : "+position,Toast.LENGTH_LONG).show();
                Intent intent=new Intent(context,PlayNhacActivity.class);
                intent.putExtra("listcakhuc",Fragment_Trang_Chu.baiHats);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return playlists.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
       ImageView imgplaylist ;
       TextView txttenplaylist;
       FrameLayout frame_playlist;
        public ViewHolder(View itemView) {
            super(itemView);
            imgplaylist=itemView.findViewById(R.id.imgplaylist);
            txttenplaylist=itemView.findViewById(R.id.txttenplaylist);
            frame_playlist=itemView.findViewById(R.id.frame_playlist);
        }
    }
}
