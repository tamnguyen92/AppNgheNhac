package com.example.jerem.appnghenhac.activity;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.jerem.appnghenhac.R;
import com.example.jerem.appnghenhac.adapter.AdapterMainViewPage;
import com.example.jerem.appnghenhac.designe.BuilderManager;
import com.example.jerem.appnghenhac.fragment.Fragment_Tim_Kiem;
import com.example.jerem.appnghenhac.fragment.Fragment_Trang_Chu;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.TextOutsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class TrangChuActivity extends AppCompatActivity {
    private BoomMenuButton bmb1;
    int[] colors={R.color.maudo,R.color.maucam,R.color.mauvang,R.color.mauxanh,R.color.mauluc,R.color.maulam,R.color.maucham,R.color.mautim
            ,R.color.mautim1,R.color.mau3,R.color.mau4,R.color.mau5,R.color.mau6};
    TabLayout myTabLayout;
    ViewPager myViewPager;
    LinearLayout frame;
    int PERMISSION_CODE =10000;
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
        myTabLayout=findViewById(R.id.myTabLayout);
        myViewPager=findViewById(R.id.myViewPager);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        frame=findViewById(R.id.frame);
        bmb1=findViewById(R.id.bmb1);

        for (int i = 0; i < bmb1.getPiecePlaceEnum().pieceNumber(); i++) {
            BuilderManager builderManager=new BuilderManager();
            TextOutsideCircleButton.Builder builder=builderManager.getTextOutsideCircleButtonBuilderWithDifferentPieceColor(i+"tam kute alibaba nguyen van tam dep trai",R.drawable.apple,colors[i]);
            builder.listener(new OnBMClickListener() {
                @Override
                public void onBoomButtonClick(int index) {

                    Log.d("CLICK", "onBoomButtonClick: "+index);
                    //onBoomButtonClick(index);
                }
            });
            bmb1 . addBuilder (builder);
        }

    }


    private void init() {

        // code viewpager va tablayouy
        AdapterMainViewPage adapterMainViewPage=new AdapterMainViewPage(getSupportFragmentManager());
        adapterMainViewPage.addFragment(new Fragment_Trang_Chu(),"Trang Chu");
        adapterMainViewPage.addFragment(new Fragment_Tim_Kiem(),"Tim Kiem");

        myViewPager.setAdapter(adapterMainViewPage);
        myTabLayout.setupWithViewPager(myViewPager);

        myTabLayout.getTabAt(0).setIcon(R.drawable.icontrangchu);
        myTabLayout.getTabAt(1).setIcon(R.drawable.iconsearch);

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
