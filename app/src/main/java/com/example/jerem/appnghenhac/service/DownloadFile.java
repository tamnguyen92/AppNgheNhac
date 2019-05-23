package com.example.jerem.appnghenhac.service;

import android.os.AsyncTask;
import android.util.Log;

import com.example.jerem.appnghenhac.InterFace.DowloadComplete;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class DownloadFile extends AsyncTask<String, Integer, String> {
    DowloadComplete dowloadComplete;
    int position=0;
    public DownloadFile( DowloadComplete dowloadComplete) {
        this.dowloadComplete=dowloadComplete;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        Log.d("DOWLOARD_MUSIC", "onProgressUpdate: "+values[0]);
      //  mprBar.setProgress(values[0]);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        dowloadComplete.dowload_complete(s,position);
    }

    @Override
    protected String doInBackground(String... url) {
        position=Integer.parseInt(url[2]);
        int count;
        try {
            URL url1 = new URL(url[0]);
            URLConnection conexion = url1.openConnection();
            conexion.connect();
            int lenghtOfFile = conexion.getContentLength();
            InputStream input = new BufferedInputStream(url1.openStream());
            OutputStream output = new FileOutputStream(url[1]);
            byte data[] = new byte[1024];
            long total = 0;
            System.out.println("downloading.............");
            while ((count = input.read(data)) != -1) {
                total += count;
                publishProgress((int)((total/(float)lenghtOfFile)*100));
                output.write(data, 0, count);
            }
            output.flush();
            output.close();
            input.close();
            return url[1];
        } catch (Exception e) {
        }

        return "error";
    }
}
