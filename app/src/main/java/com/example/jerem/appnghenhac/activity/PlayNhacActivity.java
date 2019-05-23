package com.example.jerem.appnghenhac.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.cleveroad.audiovisualization.VisualizerDbmHandler;
import com.example.jerem.appnghenhac.InterFace.CallbackPlay;
import com.example.jerem.appnghenhac.PlayMusic.PlayMusic2;
import com.example.jerem.appnghenhac.R;
import com.example.jerem.appnghenhac.adapter.AdapterViewPageListNhac;
import com.example.jerem.appnghenhac.fragment.Fragment_play_dansach_baihat;
import com.example.jerem.appnghenhac.fragment.Fragment_play_nhac;
import com.example.jerem.appnghenhac.model.BaiHat;

import java.util.Random;

import static com.example.jerem.appnghenhac.PlayMusic.PlayMusic2.mPlayer;
import static com.example.jerem.appnghenhac.PlayMusic.PlayMusic2.simpleDateFormat;

public class PlayNhacActivity extends AppCompatActivity implements View.OnClickListener,View.OnTouchListener,SeekBar.OnSeekBarChangeListener,CallbackPlay {
BaiHat baiHat;

public  static AdapterViewPageListNhac adapterViewPageListNhac;

Fragment_play_dansach_baihat fragment_play_dansach_baihat;
Fragment_play_nhac fragment_play_nhac;

  public static  boolean isplaying=true;
  Toolbar toolbarPlaynhac;
    Intent intent;
  ViewPager myViewPagerplaynhac;
public   SeekBar seekbarsong;
  public TextView txtviewtongtimesong,txtviewtimesong;
  ImageView btnsuffle,btnpre,btnnext,btnrepeat;
  public static ImageView btnplay;
  Random random;
public  static AppCompatActivity playNhacActivity;
    public  Runnable runnable=null;
    public  Handler handler=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_nhac);
        playNhacActivity=this;
        StrictMode.ThreadPolicy policy= new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        addControll();
        LayDuLieuIntent();
        playNhac();
    }
    public void playNhacfromlist(){
        huytientrinh();
        PlayMusic2.Play(PlayMusic2.listBaihat.get(PlayMusic2.position).getLinkBaibat().trim(),seekbarsong,txtviewtongtimesong,this,this);
        Log.d("LOG_TAM", "playNhac: "+PlayMusic2.listBaihat.get(PlayMusic2.position).getLinkBaibat().trim());
        primarySeekBarProgressUpdater();
        Fragment_play_dansach_baihat.adapterPlaynhac.notifyDataSetChanged();
        eventClick();
    }

    public void playNhac() {

        if(intent.hasExtra("hello")){
            seekbarsong.setMax(PlayMusic2.totalTimeLengthInMilliseconds);
            seekbarsong.setSecondaryProgress(PlayMusic2.bufferingUpdate);
            txtviewtongtimesong.setText(simpleDateFormat.format(PlayMusic2.totalTimeLengthInMilliseconds));
            primarySeekBarProgressUpdater();
            Fragment_play_dansach_baihat.adapterPlaynhac.notifyDataSetChanged();
            eventClick();
            showButton();

        }
        if(intent.hasExtra("cakhuc") || intent.hasExtra("listcakhuc")){
            if(PlayMusic2.listBaihat.size()>0)
            {
                huytientrinh();
                PlayMusic2.Play(PlayMusic2.listBaihat.get(PlayMusic2.position).getLinkBaibat().trim(),seekbarsong,txtviewtongtimesong,this,this);
                Log.d("LOG_TAM", "playNhac: "+PlayMusic2.listBaihat.get(PlayMusic2.position).getLinkBaibat().trim());
                primarySeekBarProgressUpdater();
                eventClick();
                if(Fragment_play_dansach_baihat.adapterPlaynhac!=null){
                Fragment_play_dansach_baihat.adapterPlaynhac.notifyDataSetChanged();
                }
            }
        }

//        PlayMusic2.mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//            @Override
//            public void onCompletion(MediaPlayer mp) {
//
//                   handler.removeCallbacks(runnable);
////                PlayMusic2.Play(PlayMusic2.listBaihat.get(PlayMusic2.position).getLinkBaibat().trim(),seekbarsong,txtviewtongtimesong,txtviewtimesong);
////                 primarySeekBarProgressUpdater();
//            }
//        });

    }

    private void showButton() {
        if(PlayMusic2.mPlayer.isPlaying()){
            btnplay.setImageResource(R.drawable.pause_white);
        }else {
            btnplay.setImageResource(R.drawable.play_button_white);
        }

        if(PlayMusic2.modePlay==1){
            btnsuffle.setImageResource(R.drawable.shuffle_white);
            btnrepeat.setImageResource(R.drawable.replay_black);
        } if(PlayMusic2.modePlay==2){
            btnsuffle.setImageResource(R.drawable.shuffle_black);
            btnrepeat.setImageResource(R.drawable.replay_white);
        }if(PlayMusic2.modePlay==0){
            btnsuffle.setImageResource(R.drawable.shuffle_white);
            btnrepeat.setImageResource(R.drawable.replay_white);
        }
    }

    public void huytientrinh(){
        if(handler!=null && runnable!=null){
            handler.removeCallbacks(runnable);
        }
}

    private void checkMode(String command) {
        if(command.equals("complete")){
            if(PlayMusic2.modePlay==0){

            }
            if(PlayMusic2.modePlay==1){
                if(PlayMusic2.position==(PlayMusic2.listBaihat.size()-1))
                {
                    PlayMusic2.position=0;
                }
                else if(PlayMusic2.position >=0 && PlayMusic2.position<(PlayMusic2.listBaihat.size()-1)){
                    PlayMusic2.position ++;
                }
            }if(PlayMusic2.modePlay==2){
                PlayMusic2.position = random.nextInt(PlayMusic2.listBaihat.size());
            }
        }
        if(command.equals("next")){
            if(PlayMusic2.modePlay==0){

            }
            if(PlayMusic2.modePlay==1){
                if(PlayMusic2.position==(PlayMusic2.listBaihat.size()-1))
                {
                    PlayMusic2.position=0;
                }
                else if(PlayMusic2.position >=0 && PlayMusic2.position<(PlayMusic2.listBaihat.size()-1)){
                    PlayMusic2.position ++;
                }
            }if(PlayMusic2.modePlay==2){
                PlayMusic2.position = random.nextInt(PlayMusic2.listBaihat.size());
            }
        }if(command.equals("pre")){
            if(PlayMusic2.modePlay==0){

            }
            if(PlayMusic2.modePlay==1){
                if(PlayMusic2.position==0)
                {
                    PlayMusic2.position=PlayMusic2.listBaihat.size()-1;
                }
                else if(PlayMusic2.position >=0 && PlayMusic2.position<=(PlayMusic2.listBaihat.size()-1)){
                    PlayMusic2.position --;
                }
            }if(PlayMusic2.modePlay==2){
                PlayMusic2.position = random.nextInt(PlayMusic2.listBaihat.size());
            }
        }
            Log.d("LOG_TAMm", "check mode: "+PlayMusic2.position);
                huytientrinh();
                eventClick();
                PlayMusic2.Play(PlayMusic2.listBaihat.get(PlayMusic2.position).getLinkBaibat().trim(),seekbarsong,txtviewtongtimesong,this,this);
                primarySeekBarProgressUpdater();
                Fragment_play_dansach_baihat.adapterPlaynhac.notifyDataSetChanged();
    }

    public void primarySeekBarProgressUpdater() {
            if(PlayMusic2.startplaying){
                txtviewtimesong.setText(simpleDateFormat.format(mPlayer.getCurrentPosition()));
                seekbarsong.setProgress(mPlayer.getCurrentPosition());
            }
        handler = new Handler();
        runnable=new Runnable() {
            @Override
            public void run() {
                Log.d("LOG_TAM", "run: ");
                primarySeekBarProgressUpdater();
            }
        };
        handler.postDelayed(runnable,500);
    }



    private void addControll() {
        PlayMusic2.audioVisualization = findViewById(R.id.visualizer_view);
        random=new Random();
        toolbarPlaynhac=findViewById(R.id.toolbarPlaynhac);
        myViewPagerplaynhac=findViewById(R.id.myViewPagerplaynhac);
        seekbarsong=findViewById(R.id.seekbarsong);
        txtviewtongtimesong=findViewById(R.id.txtviewtongtimesong);
        txtviewtimesong=findViewById(R.id.txtviewtimesong);
        btnsuffle=findViewById(R.id.btnsuffle);
        btnpre=findViewById(R.id.btnpre);
        btnplay=findViewById(R.id.btnplay);
        btnnext=findViewById(R.id.btnnext);
        btnrepeat=findViewById(R.id.btnrepeat);


        setSupportActionBar(toolbarPlaynhac);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Trang Chủ");
        toolbarPlaynhac.setTitleTextColor(Color.WHITE);
        toolbarPlaynhac.setSubtitleTextColor(Color.WHITE);
        toolbarPlaynhac.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnsuffle.setOnClickListener(this); btnpre.setOnClickListener(this); btnplay.setOnClickListener(this);
        btnnext.setOnClickListener(this);btnrepeat.setOnClickListener(this);

        seekbarsong.setOnTouchListener(this);
        addViewPage();


    }

    private void eventClick() {
        final Handler handlerloadAnh=new Handler();
        handlerloadAnh.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(adapterViewPageListNhac.getItem(1)!=null){
                    if(PlayMusic2.listBaihat.size()>0){
                        fragment_play_nhac.Playnhac(PlayMusic2.listBaihat.get(PlayMusic2.position).getHinhanhBaihat(),PlayMusic2.listBaihat.get(PlayMusic2.position).getTenbaihat(),PlayMusic2.listBaihat.get(PlayMusic2.position).getTencasiBaihat());
                        handlerloadAnh.removeCallbacks(this);
                    }else {
                        handlerloadAnh.postDelayed(this,300);
                    }
                }
            }
        }, 500);
    }

    private void addViewPage() {
        adapterViewPageListNhac=new AdapterViewPageListNhac(getSupportFragmentManager());
        fragment_play_dansach_baihat=new Fragment_play_dansach_baihat();
        fragment_play_nhac=new Fragment_play_nhac();
        adapterViewPageListNhac.addFragment(fragment_play_dansach_baihat);
        adapterViewPageListNhac.addFragment(fragment_play_nhac);
        myViewPagerplaynhac.setAdapter(adapterViewPageListNhac);
        fragment_play_nhac= (Fragment_play_nhac) adapterViewPageListNhac.getItem(1);
    }

    private void LayDuLieuIntent() {

         intent=getIntent();

        if(intent != null){

            if (intent.hasExtra("hello")){
                PlayMusic2.handlerVisualizer = VisualizerDbmHandler.Factory.newVisualizerHandler(this,PlayMusic2.mPlayer.getAudioSessionId());
                PlayMusic2.audioVisualization.linkTo(PlayMusic2.handlerVisualizer);
                PlayMusic2.audioVisualization.onResume();
            }
            if(intent.hasExtra("cakhuc")){
                PlayMusic2.position=0;
                PlayMusic2.listBaihat.clear();
                baiHat=intent.getParcelableExtra("cakhuc");
                PlayMusic2.listBaihat.add(baiHat);
                Log.d("TAM_LOG", "====================================");
                Toast.makeText(this,baiHat.getLinkBaibat().trim(), Toast.LENGTH_SHORT).show();
            }
            if(intent.hasExtra("listcakhuc")){

                PlayMusic2.listBaihat.clear();
                Log.d("TAM_LOG", "====================================");
                PlayMusic2.listBaihat=intent.getParcelableArrayListExtra("listcakhuc");
                for(BaiHat b : PlayMusic2.listBaihat){
                    Log.d("TAM_LOG", "LayDuLieuIntent: "+b.getTenbaihat());
                }
            }
        }

    }

    @Override
    public void onClick(View v) {
     int id=v.getId();
     switch (id){
         case R.id.btnsuffle:
             btnsuffle.setImageResource(R.drawable.shuffle_black);
             btnrepeat.setImageResource(R.drawable.replay_white);
             PlayMusic2.modePlay=2;
             break;
         case R.id.btnpre:
             xulypre();
             break;
         case R.id.btnplay:
             xulyPlay();
             break;
         case R.id.btnnext:
             xulynext();
             break;
         case R.id.btnrepeat:
            PlayMusic2.modePlay=1;
             btnsuffle.setImageResource(R.drawable.shuffle_white);
             btnrepeat.setImageResource(R.drawable.replay_black);
             break;
     }
    }

    public void xulyPlay() {
        if(isplaying==true){
            if(PlayMusic2.mPlayer.isPlaying()){
                btnplay.setImageResource(R.drawable.play_button_white);
                PlayMusic2.mPlayer.pause();
                isplaying=!isplaying;

            }
        }else {
            btnplay.setImageResource(R.drawable.pause_white);
            PlayMusic2.mPlayer.start();
            isplaying=!isplaying;
        }
    }
    public void xulynext(){
        checkMode("next");
    }
    public void xulypre(){
        checkMode("pre");
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(PlayMusic2.mPlayer!=null){
            if(PlayMusic2.mPlayer.isPlaying()){
                SeekBar sb = (SeekBar)v;
                PlayMusic2.mPlayer.seekTo(seekbarsong.getProgress());
                txtviewtimesong.setText(PlayMusic2.simpleDateFormat.format(seekbarsong.getProgress()));
            }

        }

        return false;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (fromUser) {
            if (PlayMusic2.mPlayer != null) {
                if (progress <= seekBar.getSecondaryProgress()) {
                } else {
                    seekBar.setProgress(progress);
                }

            }
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }


    @Override
    public void complete() {
        Log.d("LOG_TAMm", "completion 2:callback ");
        checkMode("complete");
    }

    @Override
    public void play_nhac_tu_ds() {

    }

    @Override
    public void onResume() {
        super.onResume();
        PlayMusic2.audioVisualization.onResume();


    }

    @Override
    public void onPause() {

        super.onPause();
        PlayMusic2.audioVisualization.onPause();
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
        PlayMusic2.audioVisualization.release();
    }
}
