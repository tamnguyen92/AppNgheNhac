package com.example.jerem.appnghenhac.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jerem.appnghenhac.R;
import com.example.jerem.appnghenhac.adapter.AdapterMainViewPage;
import com.example.jerem.appnghenhac.designe.BuilderManager;
import com.example.jerem.appnghenhac.fragment.Fragment_Tim_Kiem;
import com.example.jerem.appnghenhac.fragment.Fragment_Trang_Chu;
import com.example.jerem.appnghenhac.fragment.Fragment_sub_play_music;
import com.example.jerem.appnghenhac.model.BaiHat;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.TextOutsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class TrangChuActivity extends AppCompatActivity {
    public static boolean isplaying=false;
    public static BaiHat baiHat=null;

    private BoomMenuButton bmb1;
    int[] colors={R.color.maudo,R.color.maucam,R.color.mauvang,R.color.mauxanh,R.color.mauluc,R.color.maulam,R.color.maucham,R.color.mautim
            ,R.color.mautim1,R.color.mau3,R.color.mau4,R.color.mau5,R.color.mau6};
    String[] title={"Tất cả chủ đề","Tất cả ca sỹ","Tất cả thể loại","Tất cả play list","Tất cả Album","Top ca khúc được nghe nhiều nhất"," Top Các album nghe nhiều nhất","Top 100 ca khúc","Top ca khúc được yêu thích"};

    TabLayout myTabLayout;
    ViewPager myViewPager;

    LinearLayout frame;
   public FrameLayout layoutTrangchu;
   public LinearLayout layoutSearch;

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    int PERMISSION_CODE =10000;
LinearLayout linearlayouttrangchu;
//   LinearLayout linearsublayout
//            imghinhsublayout
//    txttenbaihatsublayout,txttencasisublayout
 EditText txtsearch;
    Fragment_Tim_Kiem fragment_tim_kiem;
    float dX, dY;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu);
        addControll();
        init();
        addPermission();
    }
    public boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }
    private void addPermission() {
        String[] PERMISSIONS = {
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE,
                    android.Manifest.permission.RECORD_AUDIO,
                    android.Manifest.permission.MODIFY_AUDIO_SETTINGS

            };

            if(!hasPermissions(this, PERMISSIONS)){
                ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_CODE);
            }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == PERMISSION_CODE && grantResults.length>=1 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
        {
            Toast.makeText(this, "cap day du quyen", Toast.LENGTH_SHORT).show();

        }else
        {

        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void addControll() {


        txtsearch=findViewById(R.id.txtsearch);
        linearlayouttrangchu=findViewById(R.id.linearlayouttrangchu);
        linearlayouttrangchu.setVisibility(View.GONE);
        myTabLayout=findViewById(R.id.myTabLayout);
        myViewPager=findViewById(R.id.myViewPager);
        layoutSearch=findViewById(R.id.layoutSearch);
        layoutTrangchu=(FrameLayout) findViewById(R.id.layoutTrangchu);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        frame=findViewById(R.id.frame);
        bmb1=findViewById(R.id.bmb1);

        for (int i = 0; i < bmb1.getPiecePlaceEnum().pieceNumber(); i++) {
            BuilderManager builderManager=new BuilderManager();
            TextOutsideCircleButton.Builder builder=builderManager.getTextOutsideCircleButtonBuilderWithDifferentPieceColor(title[i],R.drawable.apple,colors[i]);
            builder.listener(new OnBMClickListener() {
                @Override
                public void onBoomButtonClick(int index) {

                    Log.d("CLICK", "onBoomButtonClick: "+index);
                    XulyClick(index);
                }
            });
            bmb1 . addBuilder (builder);
        }

        myTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                myViewPager.setCurrentItem(tab.getPosition());
                if (tab.getPosition() == 0) {
                    layoutSearch.setVisibility(View.GONE);
                    layoutTrangchu.setVisibility(View.VISIBLE);
                    toolbar.setVisibility(View.VISIBLE);
                }if (tab.getPosition() == 1) {
                    layoutSearch.setVisibility(View.VISIBLE);
                    layoutTrangchu.setVisibility(View.GONE);
                    toolbar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private void XulyClick(int index) {
        if(index>=0){
            switch (index){
                case 0:
                    Intent intentchude=new Intent(this,InforActivity.class);
                    intentchude.putExtra("chude",0);

                    startActivity(intentchude);
                    break;
                case 1:
                    Intent intentcasi=new Intent(this,InforActivity.class);
                    intentcasi.putExtra("casi",1);
                    startActivity(intentcasi);
                    break;
                case 2:break;
                case 3:
                    Intent intentplaylist=new Intent(this,InforActivity.class);
                    intentplaylist.putExtra("playlist",3);
                    startActivity(intentplaylist);
                    break;
                case 4:
                    Intent intentalbum=new Intent(this,InforActivity.class);
                    intentalbum.putExtra("Album",4);
                    startActivity(intentalbum);
                    break;
                case 5:
                    Intent intentbaihatnghenhieu=new Intent(this,InforActivity.class);
                    intentbaihatnghenhieu.putExtra("baihatnghenhieu",5);
                    startActivity(intentbaihatnghenhieu);
                    break;
                case 6:
                    Intent intentalbumnghenhieu=new Intent(this,InforActivity.class);
                    intentalbumnghenhieu.putExtra("albumnghenhieu",6);
                    startActivity(intentalbumnghenhieu);
                    break;
                case 7:break;
                case 8:
                    Intent intentbaihatthichnhieu=new Intent(this,InforActivity.class);
                    intentbaihatthichnhieu.putExtra("baihatthichnhieu",8);
                    startActivity(intentbaihatthichnhieu);
                    break;
            }
        }
    }

    @Override
    protected void onResume() {
        if(isplaying==true){
            linearlayouttrangchu.setVisibility(View.VISIBLE);
            fragmentManager =getSupportFragmentManager();
            fragmentTransaction=fragmentManager.beginTransaction();
            Fragment_sub_play_music fragment_sub_play_music=new Fragment_sub_play_music();

            fragmentTransaction.replace(R.id.linearlayouttrangchu,fragment_sub_play_music);
            fragmentTransaction.commit();
        }
        super.onResume();
    }

    private void init() {

        fragment_tim_kiem=new Fragment_Tim_Kiem();
        // code viewpager va tablayouy
        AdapterMainViewPage adapterMainViewPage=new AdapterMainViewPage(getSupportFragmentManager());
        adapterMainViewPage.addFragment(new Fragment_Trang_Chu(),"Home");
        adapterMainViewPage.addFragment(fragment_tim_kiem,"Search");

        myViewPager.setAdapter(adapterMainViewPage);
        myTabLayout.setupWithViewPager(myViewPager);

        myTabLayout.getTabAt(0).setIcon(R.drawable.home_icon);
        myTabLayout.getTabAt(1).setIcon(R.drawable.search_black);

        fragment_tim_kiem= (Fragment_Tim_Kiem) adapterMainViewPage.getItem(1);

        txtsearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
               fragment_tim_kiem.TimKiem(s);
            }
        });
        //tiep
//        String s="https://jeremy02.herokuapp.com/Thanh Xuan Cua Em La Anh - Truong Linh D.mp3";
//        URI uri = null;
//        try {
//            uri = new URI(s.replace(" ", "%20"));
//            Toast.makeText(this,uri+"",Toast.LENGTH_LONG).show();
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        }


    }


}
