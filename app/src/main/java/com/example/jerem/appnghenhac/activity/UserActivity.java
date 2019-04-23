package com.example.jerem.appnghenhac.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.jerem.appnghenhac.R;

public class UserActivity extends AppCompatActivity {
ProgressBar ProgressBarUser;
Toolbar tool_bar_user;
TextView txttitlebaihatyeuthich;
ImageView imgtimkieminfor;
RecyclerView lsbaihatyeuthich;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        ProgressBarUser=findViewById(R.id.ProgressBarUser);
        tool_bar_user=findViewById(R.id.tool_bar_user);
        txttitlebaihatyeuthich=findViewById(R.id.txttitlebaihatyeuthich);
        imgtimkieminfor=findViewById(R.id.imgtimkieminfor);
        lsbaihatyeuthich=findViewById(R.id.lsbaihatyeuthich);


    }
}
