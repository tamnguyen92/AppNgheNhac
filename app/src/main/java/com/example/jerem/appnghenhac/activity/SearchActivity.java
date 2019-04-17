package com.example.jerem.appnghenhac.activity;

import android.app.FragmentTransaction;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.jerem.appnghenhac.R;
import com.example.jerem.appnghenhac.fragment.Fragment_Tim_Kiem;

public class SearchActivity extends AppCompatActivity {
    FragmentManager fragmentManager;
    android.support.v4.app.FragmentTransaction fragmentTransaction;
  FrameLayout framesearch;
    Fragment_Tim_Kiem fragment_tim_kiem;
  EditText txtsearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        framesearch=findViewById(R.id.framesearch);
        txtsearch=findViewById(R.id.txtsearch);
         fragment_tim_kiem=new Fragment_Tim_Kiem();
        fragmentManager=getSupportFragmentManager();
        fragmentTransaction =fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.framesearch,fragment_tim_kiem);
        fragmentTransaction.commit();

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

    }
}
