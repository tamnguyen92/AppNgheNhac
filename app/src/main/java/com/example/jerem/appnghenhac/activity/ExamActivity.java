package com.example.jerem.appnghenhac.activity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.speech.RecognitionListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cleveroad.audiovisualization.AudioVisualization;
import com.cleveroad.audiovisualization.DbmHandler;
import com.cleveroad.audiovisualization.SpeechRecognizerDbmHandler;
import com.cleveroad.audiovisualization.VisualizerDbmHandler;
import com.example.jerem.appnghenhac.R;

import java.io.IOException;

public class ExamActivity extends AppCompatActivity {
    private AudioVisualization audioVisualization;
    private VisualizerDbmHandler handler;
    MediaPlayer mp =null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);
        audioVisualization = findViewById(R.id.visualizer_view);
        if(mp !=null){
            mp.stop();
            mp.reset();

        }
        mp=new MediaPlayer();

        mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mp.setDataSource("https://c1-sd-vdc.nixcdn.com/Sony_Audio38/MakeMe-BritneySpearsGEazy-4512767.mp3?st=aLzbF4plinBqOJGuKoTByw&e=1554468420");
            handler = VisualizerDbmHandler.Factory.newVisualizerHandler(ExamActivity.this,mp.getAudioSessionId());
            audioVisualization.linkTo(handler);
            audioVisualization.onResume();
        } catch (IOException e) {
            e.printStackTrace();
        }
mp.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {
        if(percent==100){

        }
    }
});
        mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(final MediaPlayer mp) {


                mp.start();

            }
        });
        mp.prepareAsync();


    }

    @Override
    protected void onStart() {
        super.onStart();

//        if(mp !=null){
//            mp.stop();
//            mp.reset();
//            mp.release();
//        }
//         mp = MediaPlayer.create(this,R.raw.thecu);
//        mp.setLooping(true);
//        handler = VisualizerDbmHandler.Factory.newVisualizerHandler(this,mp);
//        mp.start();
    }

    @Override
    public void onResume() {
        super.onResume();
        audioVisualization.onResume();


    }

    @Override
    public void onPause() {

        super.onPause();
        audioVisualization.onPause();
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
        audioVisualization.release();
    }
}
