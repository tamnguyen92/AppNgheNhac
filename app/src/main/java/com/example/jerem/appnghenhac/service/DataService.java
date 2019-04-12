package com.example.jerem.appnghenhac.service;

import com.example.jerem.appnghenhac.model.Album;
import com.example.jerem.appnghenhac.model.BaiHat;
import com.example.jerem.appnghenhac.model.ChuDe;
import com.example.jerem.appnghenhac.model.Playlist;
import com.example.jerem.appnghenhac.model.QuangCao;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface DataService {
    @GET("quangcao")
    Call<List<QuangCao>> Getdata_Banner();

    @GET("playlist")
    Call<List<Playlist>> Getdata_Playlist();

    @GET("chude")
    Call<List<ChuDe>> Getdata_ChuDe();

    @GET("baihat")
    Call<List<BaiHat>> Getdata_BaiHat();

    @GET("album")
    Call<List<Album>> Getdata_Album();

    @FormUrlEncoded
    @POST("album")
    Call<List<BaiHat>> Getdata_BaiHatTheoAlbum(@Field("idAlbum") int idAlbum);

}
