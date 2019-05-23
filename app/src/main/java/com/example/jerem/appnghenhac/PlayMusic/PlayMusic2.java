package com.example.jerem.appnghenhac.PlayMusic;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.TextView;

import com.cleveroad.audiovisualization.AudioVisualization;
import com.cleveroad.audiovisualization.VisualizerDbmHandler;
import com.example.jerem.appnghenhac.InterFace.CallbackPlay;
import com.example.jerem.appnghenhac.R;
import com.example.jerem.appnghenhac.activity.PlayNhacActivity;
import com.example.jerem.appnghenhac.activity.TrangChuActivity;
import com.example.jerem.appnghenhac.fragment.Fragment_sub_play_music;
import com.example.jerem.appnghenhac.model.BaiHat;
import com.example.jerem.appnghenhac.model.Object_Json;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;

public class PlayMusic2 {
    public static MediaPlayer mPlayer;
    public static int modePlay=0; // 0.laplai ,1 phat tiep, 2.tron bai
    public static int position=0;
   // public static BaiHat baiHat=null;
    public static int totalTimeLengthInMilliseconds=0;
    public static SimpleDateFormat simpleDateFormat=new SimpleDateFormat("mm:ss");
    public static int bufferingUpdate=0;
    public static ArrayList<BaiHat> listBaihat=new ArrayList<>();
    public static boolean startplaying=false;
    public static AudioVisualization audioVisualization=null;
    public static VisualizerDbmHandler handlerVisualizer=null;
   public static Object_Json object_json;
//    public static  CallbackPlay callbackPlay;

    public static void Play(final String url, final SeekBar seekBar, final TextView txtviewtongtimesong, final CallbackPlay callbackPlay, final Context context){
        object_json =new Object_Json(context);
        TrangChuActivity.isplaying=true;
        //baiHat=listBaihat.get(position);
        TrangChuActivity.baiHat=listBaihat.get(position);
        if(Fragment_sub_play_music.fragment_sub_play_music!=null){
            ((Fragment_sub_play_music)Fragment_sub_play_music.fragment_sub_play_music).CapnhatLayout();
        }
        Log.d("BAI_HAT_DANG_PLAY", "Play: "+TrangChuActivity.baiHat.getIdBaihat());
        if(mPlayer != null){
            mPlayer.stop();
            mPlayer.reset();
            startplaying=false;
        }

        try {
            mPlayer =new MediaPlayer();

            Log.d("LOG_TAMm", "Play: "+url);
            mPlayer.setDataSource(url);
           handlerVisualizer = VisualizerDbmHandler.Factory.newVisualizerHandler(context,mPlayer.getAudioSessionId());
            audioVisualization.linkTo( handlerVisualizer);
            audioVisualization.onResume();
            mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {

                    mPlayer.start();
                    startplaying=true;

                    Log.d("LOG_TAM", "Play:start "+mPlayer.getDuration());
                    if(mPlayer.getDuration() == -1){
                         double thoigian= Double.parseDouble(TrangChuActivity.baiHat.getThoigian());
                         int phut=(int)thoigian%10;
                         int giay= (int) (thoigian*100 - phut*100);
                        Log.d("THOIGIAN",thoigian+" phut"+"="+phut+" giay "+giay);
                        totalTimeLengthInMilliseconds = (phut*60 + giay)*1000;
                        PlayNhacActivity.btnplay.setImageResource(R.drawable.pause_white);

                    }else {
                        totalTimeLengthInMilliseconds = mPlayer.getDuration();
                    }

                    if(txtviewtongtimesong!=null){
                        txtviewtongtimesong.setText(PlayMusic2.simpleDateFormat.format(PlayMusic2.totalTimeLengthInMilliseconds));
                    }
                    if(seekBar!=null){
                        seekBar.setMax(totalTimeLengthInMilliseconds);
                    }
                }
            });

            mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    Log.d("LOG_TAMm", "completion 2: ");
                   callbackPlay.complete();

                }
            });
            mPlayer.prepareAsync();
            mPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
                @Override
                public void onBufferingUpdate(MediaPlayer mp, int percent) {
                    Log.e("onBufferingUpdate", "" + percent);
                    if(startplaying){
                        if(mPlayer.getDuration()==-1){
                            if(seekBar!=null){
                                seekBar.setSecondaryProgress(totalTimeLengthInMilliseconds);
                            }
                        }else {
                            bufferingUpdate=(int)percent*mPlayer.getDuration()/100;
                            if(PlayNhacActivity.btnplay !=null){
                                if(percent >15){
                                    PlayNhacActivity.btnplay.setImageResource(R.drawable.pause_white);
                                }
                            }
                            if(seekBar!=null){
                                seekBar.setSecondaryProgress(bufferingUpdate);
                            }
                        }

                    }

                }
            });
            xulythembaihatLichSu(listBaihat.get(position));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void xulythembaihatLichSu(BaiHat baiHat) {
        if(Object_Json.dsBaiHatLichSu.size()== 50){
            Object_Json.dsBaiHatLichSu.remove(49);
        }else {
            if(Object_Json.dsBaiHatLichSu.size()>2){
                int index=Object_Json.dsBaiHatLichSu.size()-1;
                if(Object_Json.dsBaiHatLichSu.get(0).getIdBaihat().intValue()== baiHat.getIdBaihat().intValue()){

                }else {
                    Collections.reverse(Object_Json.dsBaiHatLichSu);
                    Object_Json.dsBaiHatLichSu.add(baiHat);
                    Collections.reverse(Object_Json.dsBaiHatLichSu);
                    object_json.SaveBaiHatLichsu( Object_Json.dsBaiHatLichSu);
                    Log.d("xulythembaihatLichSu", "xulythembaihatLichSu: "+Object_Json.dsBaiHatLichSu.size());
                }

            }else {
                Collections.reverse(Object_Json.dsBaiHatLichSu);
                Object_Json.dsBaiHatLichSu.add(baiHat);
                Collections.reverse(Object_Json.dsBaiHatLichSu);
                object_json.SaveBaiHatLichsu( Object_Json.dsBaiHatLichSu);
                Log.d("xulythembaihatLichSu", "xulythembaihatLichSu: "+Object_Json.dsBaiHatLichSu.size());
            }

        }
    }
}
