package com.example.jerem.appnghenhac.activity;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.speech.RecognizerIntent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.jerem.appnghenhac.R;
import com.example.jerem.appnghenhac.adapter.AdapterMainViewPage;
import com.example.jerem.appnghenhac.designe.BuilderManager;
import com.example.jerem.appnghenhac.fragment.Fragment_Tim_Kiem;
import com.example.jerem.appnghenhac.fragment.Fragment_Trang_Chu;
import com.example.jerem.appnghenhac.fragment.Fragment_sub_play_music;
import com.example.jerem.appnghenhac.model.Album;
import com.example.jerem.appnghenhac.model.BaiHat;
import com.example.jerem.appnghenhac.model.Object_Json;
import com.example.jerem.appnghenhac.model.TaiKhoan;
import com.example.jerem.appnghenhac.service.APIService;
import com.example.jerem.appnghenhac.service.DataService;

import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.TextOutsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
//  <item name="android:windowTranslucentStatus">true</item>
//        <item name="android:windowTranslucentNavigation">true</item>
//        <item name="android:statusBarColor">@color/colortran</item>
public class TrangChuActivity extends AppCompatActivity {
    public static boolean isplaying=false;
    public static BaiHat baiHat=null;
    public static TaiKhoan taiKhoan=null;

    private BoomMenuButton bmb1,bmb;
    int[] colors={R.color.maudo,R.color.maucam,R.color.mauvang,R.color.mauxanh,R.color.mauluc,R.color.maulam,R.color.maucham,R.color.mautim
            ,R.color.mautim1,R.color.mau3,R.color.mau4,R.color.mau5,R.color.mau6};
    String[] title={"Tất cả chủ đề","Tất cả ca sỹ","Tất cả thể loại","Tất cả play list","Tất cả Album","Top ca khúc được nghe nhiều nhất"," Top Các album nghe nhiều nhất","Top 100 ca khúc","Top ca khúc được yêu thích"};

    String[] titleHamSub={"xem thông tin của bạn","Các ca khúc bạn đã yêu thích !!","Các album bạn đã yêu thích !!","thông tin các ca khúc bạn đã dowloard !","các ca khúc nghe gần đây !","Đăng xuất khỏi ứng dụng !!"};
    TabLayout myTabLayout;
    ViewPager myViewPager;

    LinearLayout frame;
   public FrameLayout layoutTrangchu;
   public LinearLayout layoutSearch;
    Random rand = new Random();
    Object_Json object_json =null;
    public  static TaiKhoan tk=null;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    int PERMISSION_CODE =10000;
    ImageButton imghinhmicro;
LinearLayout linearlayouttrangchu;
DataService dataService;
 EditText txtsearch;
    Fragment_Tim_Kiem fragment_tim_kiem;
    private final int REQ_CODE_SPEECH_INPUT = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu);
        getdataShare();
        addControll();
        init();
        addPermission();
    }
    private void getdataShare() {
        dataService=APIService.getService();
         object_json = new Object_Json(this);
         tk=object_json.GetTaiKhoan();

         if(tk!=null){
             getDataBaiHatYeuThich(tk.getIdTaiKhoan());
             getDataAlbumYeuThich(tk.getIdTaiKhoan());
         }
         object_json.GetBaiHatLichsu();
         object_json.GetBaiHatDownloard();
    }

    private void getDataAlbumYeuThich(Integer idTaiKhoan) {
        Call<List<Album>> callback=dataService.Getdata_AlbumYeuThich(idTaiKhoan);
        callback.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                Object_Json.dsAlbumYeuThich= (ArrayList<Album>) response.body();
                if(  Object_Json.dsAlbumYeuThich.size()>0){
                    Log.d("dsAlbumYeuThich","dsAlbumYeuThich: "+Object_Json.dsBaiHatYeuThich.size());
                    object_json.SaveAlbumYeuThich(Object_Json.dsAlbumYeuThich);
                }
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {

            }
        });
    }

    private void getDataBaiHatYeuThich(int mataikhoan) {
        Call<List<BaiHat>> callback=dataService.Getdata_BaiHatYeuThich(mataikhoan);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                Object_Json.dsBaiHatYeuThich= (ArrayList<BaiHat>) response.body();

                if(  Object_Json.dsBaiHatYeuThich.size()>0){
                    Log.d("dsBaiHatYeuThich","dsBaiHatYeuThich: "+Object_Json.dsBaiHatYeuThich.size());
                    object_json.SaveBaiHatYeuThich(Object_Json.dsBaiHatYeuThich);
                }
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }

    public void addHamButton(String user){
        String[] titleHam={user,"Ca khúc yêu thích","Album yêu thích","các ca khúc dowloard","Lịch sử lượt nghe","Đăng xuất"};
        assert bmb != null;
        bmb.setButtonEnum(ButtonEnum.Ham);
        for (int i = 0; i < bmb.getPiecePlaceEnum().pieceNumber(); i++){
//            int n = rand.nextInt(12);
            BuilderManager builderManager=new BuilderManager();
            HamButton.Builder builderham=builderManager.getHamButtonBuilderWithDifferentPieceColor(R.drawable.playlist,titleHam[i], titleHamSub[i],colors[i]);
            builderham.listener(new OnBMClickListener() {
                @Override
                public void onBoomButtonClick(int index) {

                    Log.d("CLICK", "onBoomButtonClick: "+index);
                     XulyClickHam(index);
                }
            });
            bmb.addBuilder(builderham);
        }
    }

    private void XulyClickHam(int index) {
        if(index>=0){
            switch (index){
                case 0:
                    Intent intentchude=new Intent(this,InforActivity.class);
                    intentchude.putExtra("chude",0);

                    startActivity(intentchude);
                    break;
                case 1:
                    Intent intentbaihatyeuthich=new Intent(this,UserActivity.class);
                    intentbaihatyeuthich.putExtra("baihatyeuthich",1);
                    startActivity(intentbaihatyeuthich);
                    break;
                case 2:
                    Intent intentalbumyeuthich=new Intent(this,UserActivity.class);
                    intentalbumyeuthich.putExtra("albumyeuthich",1);
                    startActivity(intentalbumyeuthich);
                    break;
                case 3:
                    Intent intentbaihatdown=new Intent(this,UserActivity.class);
                    intentbaihatdown.putExtra("downloard",3);
                    startActivity(intentbaihatdown);
                    break;
                case 4:
                    Intent intentbaihatlichsu=new Intent(this,UserActivity.class);
                    intentbaihatlichsu.putExtra("baihatlichsu",4);
                    startActivity(intentbaihatlichsu);
                    break;
                case 5:
                    Intent intentbaihatnghenhieu=new Intent(this,LoginActivity.class);

                    startActivity(intentbaihatnghenhieu);
                    break;

            }
        }
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

        imghinhmicro=findViewById(R.id.imghinh);
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
        bmb = (BoomMenuButton) findViewById(R.id.bmb);
        if(tk!=null){
            addHamButton(tk.getUsername());
        }else {
            addHamButton("username");
        }
        for (int i = 0; i < bmb1.getPiecePlaceEnum().pieceNumber(); i++) {
            BuilderManager builderManager=new BuilderManager();
            TextOutsideCircleButton.Builder builder=builderManager.getTextOutsideCircleButtonBuilderWithDifferentPieceColor(title[i],R.drawable.playlist,colors[i]);
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
        imghinhmicro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                promptSpeechInput();
            }
        });
    }
    private void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_prompt));
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                 //   fragment_tim_kiem.TimKiem(result.get(0));
                    txtsearch.setText(result.get(0));
                }
                break;
            }

        }
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
