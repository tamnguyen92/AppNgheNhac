package com.example.jerem.appnghenhac.model;

import android.content.Context;
import android.content.SharedPreferences;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Object_Json {
    public Context context;
    public static ArrayList<BaiHat>dsBaiHatYeuThich=new ArrayList<>();
    public Object_Json(Context context) {
        this.context = context;
    }
    public void SaveAccount(TaiKhoan taiKhoan)
    {
        SharedPreferences sharedPreferences=context.getSharedPreferences("login_data",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        Gson gson=new Gson();
        String json=gson.toJson(taiKhoan);
        editor.putString("taikhoan",json);
        editor.apply();
    }
    public TaiKhoan GetTaiKhoan()
    {
        TaiKhoan taiKhoan=null;
        SharedPreferences sharedPreferences=context.getSharedPreferences("login_data",Context.MODE_PRIVATE);
        Gson gson=new Gson();
        String json=sharedPreferences.getString("taikhoan",null);
        Type type=new TypeToken<TaiKhoan>(){}.getType();
        taiKhoan=gson.fromJson(json,type);
        return taiKhoan;
    }

    public void SaveBaiHatYeuThich(List<BaiHat> dsbaihat)
    {
        SharedPreferences sharedPreferences=context.getSharedPreferences("baihatyeuthich",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        Gson gson=new Gson();
        String json=gson.toJson(dsbaihat);
        editor.putString("dsbaihat",json);
        editor.apply();
    }
    public void BaiHatYeuThich()
    {
        dsBaiHatYeuThich.clear();
        SharedPreferences sharedPreferences=context.getSharedPreferences("baihatyeuthich",Context.MODE_PRIVATE);
        Gson gson=new Gson();
        String json=sharedPreferences.getString("dsbaihat",null);
        Type type=new TypeToken<ArrayList<BaiHat>>(){}.getType();
        dsBaiHatYeuThich=gson.fromJson(json,type);
    }

}
