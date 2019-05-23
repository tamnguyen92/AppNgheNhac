package com.example.jerem.appnghenhac.model;

import android.content.Context;
import android.content.SharedPreferences;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.example.jerem.appnghenhac.activity.TrangChuActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Object_Json {
    public Context context;
    public static ArrayList<BaiHat>dsBaiHatYeuThich=new ArrayList<>();
    public static ArrayList<BaiHat>dsBaiHatLichSu=new ArrayList<>();
    public static ArrayList<Album>dsAlbumYeuThich=new ArrayList<>();
    public static ArrayList<BaiHat>dsBaiHatDownloard=new ArrayList<>();
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
    public void SaveAlbumYeuThich(List<Album> dsalbum)
    {
        SharedPreferences sharedPreferences=context.getSharedPreferences("albumyeuthich",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        Gson gson=new Gson();
        String json=gson.toJson(dsalbum);
        editor.putString("dsalbum",json);
        editor.apply();
    }
    public void SaveBaiHatLichsu(ArrayList<BaiHat> dsbaihat)
    {
        SharedPreferences sharedPreferences=context.getSharedPreferences("baihatlichsu",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        Gson gson=new Gson();
        String json=gson.toJson(dsbaihat);
        editor.putString("dsbaihatlichsu",json);
        editor.apply();
    }
    public ArrayList<BaiHat> GetBaiHatLichsu()
    {
        SharedPreferences sharedPreferences=context.getSharedPreferences("baihatlichsu",Context.MODE_PRIVATE);
        Gson gson=new Gson();
        String json="";
        json=sharedPreferences.getString("dsbaihatlichsu",null);
        if(json!=null){
            Type type=new TypeToken<ArrayList<BaiHat>>(){}.getType();
            dsBaiHatLichSu=gson.fromJson(json,type);
            return dsBaiHatLichSu;
        }
        return dsBaiHatLichSu;

    }
    public void SaveBaiHatDownloard(ArrayList<BaiHat> dsbaihat)
    {
        SharedPreferences sharedPreferences=context.getSharedPreferences("baihatdownloard"+TrangChuActivity.taiKhoan.getIdTaiKhoan(),Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        Gson gson=new Gson();
        String json=gson.toJson(dsbaihat);
        editor.putString("dsbaihatdownloard"+TrangChuActivity.taiKhoan.getIdTaiKhoan(),json);
        editor.apply();
    }
    public ArrayList<BaiHat> GetBaiHatDownloard()
    {
        SharedPreferences sharedPreferences=context.getSharedPreferences("baihatdownloard"+TrangChuActivity.taiKhoan.getIdTaiKhoan(),Context.MODE_PRIVATE);
        Gson gson=new Gson();
        String json="";
        json=sharedPreferences.getString("dsbaihatdownloard"+TrangChuActivity.taiKhoan.getIdTaiKhoan(),null);
        if(json!=null){
            Type type=new TypeToken<ArrayList<BaiHat>>(){}.getType();
            dsBaiHatDownloard=gson.fromJson(json,type);
            return dsBaiHatDownloard;
        }
        return dsBaiHatDownloard;

    }
    public static boolean checkTonTai(int loai,int id){
        if(loai==0){
            if( Object_Json.dsBaiHatYeuThich.size()>0){
                for (BaiHat b : Object_Json.dsBaiHatYeuThich){
                    if(b.getIdBaihat().intValue()==id){
                        return true;
                    }
                }
                return false;
            }else {
                return false;
            }

        }if(loai==1) {
            if( Object_Json.dsAlbumYeuThich.size()>0){
                for (Album b : Object_Json.dsAlbumYeuThich){
                    if(b.getIdAlbum().intValue()==id){
                        return true;
                    }
                }
                return false;
            }else {
                return false;
            }
        }if(loai==2) {
            if( Object_Json.dsBaiHatDownloard.size()>0){
                for (BaiHat b : Object_Json.dsBaiHatDownloard){
                    if(b.getIdBaihat().intValue()==id){
                        return true;
                    }
                }
                return false;
            }else {
                return false;
            }
        }
        return false;
    }

}
