package com.example.jerem.appnghenhac.PlayMusic;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.jerem.appnghenhac.activity.PlayNhacActivity;

import java.io.IOException;
import java.text.SimpleDateFormat;

public class PlayMusics implements View.OnTouchListener,SeekBar.OnSeekBarChangeListener {
    public static MediaPlayer mPlayer;
   public static SeekBar seekBar;
    public  static TextView txtviewtimesong,txtviewtongtimesong;
    public static int mediaFileLengthInMilliseconds;
   public static  String url;
    public static SimpleDateFormat simpleDateFormat=new SimpleDateFormat("mm:ss");
    public static Runnable runnable;
    public static Handler handler;


    public PlayMusics(SeekBar seekBar,String url,TextView txtviewtimesong,TextView txtviewtongtimesong) {
        this.seekBar=seekBar;
        this.url=url;
        this.txtviewtongtimesong=txtviewtongtimesong;
        this.txtviewtimesong=txtviewtimesong;
        this.seekBar.setOnTouchListener(this);

    }

    public  void Play()
    {


        if(mPlayer != null){
            mPlayer.reset();
            mPlayer.release();
            mPlayer=null;
        }
            try {
                mPlayer =new MediaPlayer();
                mPlayer.setDataSource(url);
                mPlayer.prepareAsync();
                mPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {

                        mPlayer.start();

                        primarySeekBarProgressUpdater();
                        mediaFileLengthInMilliseconds = mPlayer.getDuration();
                        seekBar.setMax(mediaFileLengthInMilliseconds);
                        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("mm:ss");
                        txtviewtongtimesong.setText(simpleDateFormat.format(mediaFileLengthInMilliseconds));
                    }
                });

                mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        if (mPlayer != null) {
                            try {
                                 Log.d("LOG_TAM", "stop: ");
                                 handler.removeCallbacks(runnable);
                                 mPlayer.reset();
                                 mPlayer.release();
                                 mPlayer = null;
                                 Play();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
                mPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
                    @Override
                    public void onBufferingUpdate(MediaPlayer mp, int percent) {
                        Log.e("onBufferingUpdate", "" + percent);
int phantram=(int)percent*mPlayer.getDuration()/100;
                        seekBar.setSecondaryProgress(phantram);
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }



    }

    public static void primarySeekBarProgressUpdater() {
//            txtviewtimesong.setText(simpleDateFormat.format(mPlayer.getCurrentPosition()));
//            seekBar.setProgress((int)(((float)mPlayer.getCurrentPosition()/mediaFileLengthInMilliseconds)*100)); // This math construction give a percentage of "was playing"/"song length"
//          //  if (mPlayer.isPlaying()) {
//                final Runnable notification = new Runnable() {
//                    public void run() {
//                        primarySeekBarProgressUpdater();
//                        if (mPlayer.getCurrentPosition()==mPlayer.getDuration()){
//                            handler.removeCallbacks(this);
//                        }
//                    }
//                };
//                handler.postDelayed(notification,500);
//
//         //   }

        txtviewtimesong.setText(simpleDateFormat.format(mPlayer.getCurrentPosition()));
        seekBar.setProgress(mPlayer.getCurrentPosition());
       // seekBar.setProgress((int)(((float)mPlayer.getCurrentPosition()/mediaFileLengthInMilliseconds)*100));
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

    @Override
    public boolean onTouch(View v, MotionEvent event) {

            /** Seekbar onTouch event handler. Method which seeks MediaPlayer to seekBar primary progress position*/
            if(mPlayer!=null){
                if(mPlayer.isPlaying()){
                    SeekBar sb = (SeekBar)v;
                  //  int playPositionInMillisecconds = (mediaFileLengthInMilliseconds / 100) * sb.getProgress();
                    mPlayer.seekTo(seekBar.getProgress());
                    txtviewtimesong.setText(simpleDateFormat.format(seekBar.getProgress()));
                }

            }

        return false;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (fromUser) {
            if (mPlayer != null) {
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


}
