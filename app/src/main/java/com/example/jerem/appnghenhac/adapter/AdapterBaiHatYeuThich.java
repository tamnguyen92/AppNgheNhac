package com.example.jerem.appnghenhac.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
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

import com.example.jerem.appnghenhac.InterFace.DowloadComplete;
import com.example.jerem.appnghenhac.PlayMusic.PlayMusic2;
import com.example.jerem.appnghenhac.R;
import com.example.jerem.appnghenhac.activity.PlayNhacActivity;
import com.example.jerem.appnghenhac.model.BaiHat;
import com.example.jerem.appnghenhac.model.Object_Json;
import com.example.jerem.appnghenhac.service.DownloadFile;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterBaiHatYeuThich extends RecyclerView.Adapter<AdapterBaiHatYeuThich.ViewHolder>implements DowloadComplete {
    Context context;
    ArrayList<BaiHat> baiHats;
    int layout;
    Object_Json object_json;
    public AdapterBaiHatYeuThich(Context context, ArrayList<BaiHat> baiHats, int layout) {
        this.context = context;
        this.baiHats = baiHats;
        this.layout=layout;
        object_json=new Object_Json(context);
    }
    @NonNull
    @Override
    public AdapterBaiHatYeuThich.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(layout,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterBaiHatYeuThich.ViewHolder holder, final int positions) {
        Log.d("VITRI", "onClick: ten"+baiHats.get(positions).getTenbaihat());
        Picasso.with(context)
                .load(baiHats.get(positions).getHinhanhBaihat().trim())
                .into(holder.imghinhbaihat);
        holder.txttenbaihat.setText(baiHats.get(positions).getTenbaihat());
        holder.txttencasi.setText(baiHats.get(positions).getTencasiBaihat());
        holder.txtluotnghe.setText(baiHats.get(positions).getLuotngheBaihat()+"");
        holder.imgdowloard.setVisibility(View.VISIBLE);
        holder.imgdowloard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaiHat baiHat=baiHats.get(positions);
                boolean kt=Object_Json.checkTonTai(2,baiHats.get(positions).getIdBaihat());
                if(kt){
                    Toast.makeText(context, ""+baiHats.get(positions).getTenbaihat()+"bài hát này đã downloard", Toast.LENGTH_SHORT).show();
                }else {
                   if(baiHat!=null){
                       dowloard(baiHat,positions);
                   }
                }
            }
        });
        holder.frame_baihat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("VITRI", "onClick: "+positions);
                Intent intent=new Intent(context,PlayNhacActivity.class);
                PlayMusic2.position=positions;
                intent.putExtra("listcakhuc",baiHats);
                context.startActivity(intent);
            }
        });
    }
    private void dowloard(BaiHat baiHat,int positions) {
        File folder = new File(Environment.getExternalStorageDirectory() +
                File.separator + "EnvyMusic");
        boolean success = true;
        if (!folder.exists()) {
            success = folder.mkdirs();
            if(success){
                Toast.makeText(context, "floder tao thanh cong", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(context, "floder tao KO thanh cong", Toast.LENGTH_SHORT).show();
            }

        }else {
            Toast.makeText(context, "floder da ton tai", Toast.LENGTH_SHORT).show();
            DownloadFile downloadFile=new DownloadFile(this);
            String path=Environment.getExternalStorageDirectory() +
                    File.separator + "EnvyMusic/"+baiHat.getIdBaihat()+"-"+baiHat.getTenbaihat();
            Log.d("PATH_MUSIC", path);
            downloadFile.execute(baiHat.getLinkBaibat(),path,positions+"");
        }

    }

    @Override
    public int getItemCount() {
        return baiHats.size();
    }

    @Override
    public void dowload_complete(String url, int position) {

        BaiHat baiHatdowloard=baiHats.get(position);
        Toast.makeText(context, "Downloard bài hát "+baiHatdowloard.getTenbaihat()+" thanh cong", Toast.LENGTH_SHORT).show();
        if(baiHatdowloard!=null){
            baiHatdowloard.setLinkBaibat(url);
            Object_Json.dsBaiHatDownloard.add(baiHatdowloard);
            object_json.SaveBaiHatDownloard(Object_Json.dsBaiHatDownloard);
        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView imghinhbaihat;
        ImageView imgdowloard;
        TextView txttenbaihat,txttencasi,txtluotnghe;
        FrameLayout frame_baihat;
        public ViewHolder(View itemView) {
            super(itemView);
            imghinhbaihat=itemView.findViewById(R.id.imghinhbaihat);
            imgdowloard=itemView.findViewById(R.id.imgdowloard);
            txttenbaihat=itemView.findViewById(R.id.txttenbaihat);
            txttencasi=itemView.findViewById(R.id.txttencasi);
            txtluotnghe=itemView.findViewById(R.id.txtluotnghe);
            frame_baihat=itemView.findViewById(R.id.frame_baihat);
        }
    }
}
