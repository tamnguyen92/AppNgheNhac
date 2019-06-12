package com.example.jerem.appnghenhac.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.jerem.appnghenhac.R;
import com.example.jerem.appnghenhac.model.BaiHat;
import com.example.jerem.appnghenhac.model.Object_Json;
import com.example.jerem.appnghenhac.model.TaiKhoan;
import com.example.jerem.appnghenhac.service.APIService;
import com.example.jerem.appnghenhac.service.DataService;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.blurry.Blurry;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
DataService dataService;
EditText txtusername,txtpassword;
ArrayList<TaiKhoan>taiKhoans;
Button btnlogin;
FrameLayout layoutprogress;
LinearLayout linearLayout;
ImageView imggif;
public Object_Json object_json=new Object_Json(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        addControll();
    }

    private void addControll() {
        dataService=APIService.getService();
        taiKhoans=new ArrayList<>();
        linearLayout=findViewById(R.id.linearLayout);
        imggif=findViewById(R.id.imggif);
        txtpassword=findViewById(R.id.txtpassword);
        txtusername=findViewById(R.id.txtusername);
        layoutprogress=findViewById(R.id.layoutprogress);
        layoutprogress.setVisibility(View.GONE);
        Glide.with(this)
                .load(R.drawable.gif_radio1)// you may not need this
                .into(imggif);
        txtpassword.setText("tam123");
        txtusername.setText("tam123");
        btnlogin=findViewById(R.id.btnlogin);

        btnlogin.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id){
            case R.id.btnlogin:
                layoutprogress.setVisibility(View.VISIBLE);
                xulyLogin();
                break;
        }
    }

    private void xulyLogin() {
        String user=txtusername.getText().toString();
        String pass=txtpassword.getText().toString();
        if(user == null || user.equals("") ||pass == null || pass.equals("")){
            Toast.makeText(this, "dien day du username & password", Toast.LENGTH_SHORT).show();
        }else {
            login(user,pass);
        }
    }

    private void login(String user,String pass) {
        Call<List<TaiKhoan>> callback=dataService.Login(user,pass);
        callback.enqueue(new Callback<List<TaiKhoan>>() {
            @Override
            public void onResponse(Call<List<TaiKhoan>> call, Response<List<TaiKhoan>> response) {
                taiKhoans=(ArrayList<TaiKhoan>) response.body();
                if(taiKhoans.size()>0){
                    Toast.makeText(LoginActivity.this, "dang nhap thanh cong", Toast.LENGTH_SHORT).show();
                    TrangChuActivity.taiKhoan=taiKhoans.get(0);
                    object_json.SaveAccount(taiKhoans.get(0));
                    layoutprogress.setVisibility(View.GONE);
                    Intent intent=new Intent(LoginActivity.this,TrangChuActivity.class);
                    startActivity(intent);
                    finish();
//                    TaiKhoan tk=object_json.GetTaiKhoan();
//                    if(tk !=null){
//                        Toast.makeText(LoginActivity.this, "thong tin "+tk.getUsername(), Toast.LENGTH_LONG).show();
//                    }
                }else {
                    Toast.makeText(LoginActivity.this, "sai username hoac password", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<TaiKhoan>> call, Throwable t) {

            }
        });
    }
}
