package com.example.jerem.appnghenhac.PlayMusic;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.cleveroad.audiovisualization.AudioVisualization;
import com.cleveroad.audiovisualization.VisualizerDbmHandler;
import com.example.jerem.appnghenhac.CallbackPlay;
import com.example.jerem.appnghenhac.R;
import com.example.jerem.appnghenhac.activity.ExamActivity;
import com.example.jerem.appnghenhac.activity.PlayNhacActivity;
import com.example.jerem.appnghenhac.activity.TrangChuActivity;
import com.example.jerem.appnghenhac.fragment.Fragment_play_nhac;
import com.example.jerem.appnghenhac.model.BaiHat;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

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
//    public static  CallbackPlay callbackPlay;

    public static void Play(final String url, final SeekBar seekBar, final TextView txtviewtongtimesong, final CallbackPlay callbackPlay, final Context context){
        TrangChuActivity.isplaying=true;
        //baiHat=listBaihat.get(position);
        TrangChuActivity.baiHat=listBaihat.get(position);
        Log.d("BAI HAT DANG PLAY", "Play: "+TrangChuActivity.baiHat.getIdBaihat());
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
                    totalTimeLengthInMilliseconds = mPlayer.getDuration();
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
                        bufferingUpdate=(int)percent*mPlayer.getDuration()/100;
                        if(PlayNhacActivity.btnplay !=null){
                            if(percent >15){
                                PlayNhacActivity.btnplay.setImageResource(R.drawable.pause);
                            }
                        }
                        if(seekBar!=null){
                            seekBar.setSecondaryProgress(bufferingUpdate);
                        }
                    }

                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
