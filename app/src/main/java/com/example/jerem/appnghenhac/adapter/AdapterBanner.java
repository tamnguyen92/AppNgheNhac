package com.example.jerem.appnghenhac.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jerem.appnghenhac.R;
import com.example.jerem.appnghenhac.activity.DanhSachBaiHatActivity;
import com.example.jerem.appnghenhac.model.QuangCao;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterBanner extends PagerAdapter {
    Context context;
    ArrayList<QuangCao> quangCaoArrayList;

    public AdapterBanner(Context context, ArrayList<QuangCao> quangCaoArrayList) {
        this.context = context;
        this.quangCaoArrayList = quangCaoArrayList;
    }

    @Override
    public int getCount() {
        return quangCaoArrayList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view= inflater.inflate(R.layout.layout_custom_banner,null);

        RelativeLayout frame_banner=view.findViewById(R.id.frame_banner);
        ImageView imageview_banner=view.findViewById(R.id.imageview_banner);
        ImageView imageview_custom_banner=view.findViewById(R.id.imageview_custom_banner);
        TextView txttitlebaihat=view.findViewById(R.id.txttitlebaihat);
        TextView txtnoidungbaihat=view.findViewById(R.id.txtnoidungbaihat);

        Picasso.with(context)
                .load(quangCaoArrayList.get(position).getHinhQuangcao().trim())
                .into(imageview_custom_banner);
        Picasso.with(context)
                .load(quangCaoArrayList.get(position).getHinhanhBaihat().trim())
                .into(imageview_banner);
        txttitlebaihat.setText(quangCaoArrayList.get(position).getTenbaihat());
        txtnoidungbaihat.setText(" Ca khúc mới nhất vừa mới phát hành của ca sỹ : "+quangCaoArrayList.get(position).getTencasiBaihat());

        frame_banner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Toast.makeText(context,"vi tri : "+position,Toast.LENGTH_LONG).show();
                Intent intent=new Intent(context,DanhSachBaiHatActivity.class);
                intent.putExtra("listcakhuc",quangCaoArrayList);
                intent.putExtra("position",position);
                context.startActivity(intent);
            }
        });
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
