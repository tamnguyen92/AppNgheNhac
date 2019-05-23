package com.example.jerem.appnghenhac.activity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Environment;
import android.os.Handler;
import android.speech.RecognitionListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.cleveroad.audiovisualization.AudioVisualization;
import com.cleveroad.audiovisualization.DbmHandler;
import com.cleveroad.audiovisualization.SpeechRecognizerDbmHandler;
import com.cleveroad.audiovisualization.VisualizerDbmHandler;
import com.example.jerem.appnghenhac.InterFace.DowloadComplete;
import com.example.jerem.appnghenhac.R;
import com.example.jerem.appnghenhac.service.DownloadFile;

import java.io.File;
import java.io.IOException;

public class ExamActivity extends AppCompatActivity implements DowloadComplete {
MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);
     //   dowloard();
        String path=Environment.getExternalStorageDirectory() +
                File.separator + "EnvyMusic/"+"sorry.mp3";
        playMusic(path);

    }

    private void playMusic(String Uri) {
        if(mediaPlayer != null){
            mediaPlayer.stop();
            mediaPlayer.reset();
        }



        try {
            mediaPlayer =new MediaPlayer();
            mediaPlayer.setDataSource(Uri);

            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mediaPlayer.start();
                    Log.w("DOWLOARD_MUSIC", "Duration: "+mediaPlayer.getDuration());
                }
            });

            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    Log.w("DOWLOARD_MUSIC", "Complete" );
                }
            });

            mediaPlayer.prepareAsync();
            mediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
                @Override
                public void onBufferingUpdate(MediaPlayer mp, int percent) {
                    Log.w("DOWLOARD_MUSIC", "onBufferingUpdate: "+percent );
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void dowloard() {
        File folder = new File(Environment.getExternalStorageDirectory() +
                File.separator + "EnvyMusic");
        boolean success = true;
        if (!folder.exists()) {
            success = folder.mkdirs();
            if(success){
                Toast.makeText(this, "floder tao thanh cong", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "floder tao KO thanh cong", Toast.LENGTH_SHORT).show();
            }

        }else {
            Toast.makeText(this, "floder da ton tai", Toast.LENGTH_SHORT).show();
            DownloadFile downloadFile=new DownloadFile(this);
            String path=Environment.getExternalStorageDirectory() +
                    File.separator + "EnvyMusic/"+"sorry.mp3";
            Log.d("PATH_MUSIC", path);
            downloadFile.execute("https://jeremy02.herokuapp.com/Sorry%20(Acoustic).mp3",path);
        }

    }



    @Override
    public void dowload_complete(String url, int position) {

    }
}
