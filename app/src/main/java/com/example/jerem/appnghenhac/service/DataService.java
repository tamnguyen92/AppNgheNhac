package com.example.jerem.appnghenhac.service;

import com.example.jerem.appnghenhac.model.Album;
import com.example.jerem.appnghenhac.model.BaiHat;
import com.example.jerem.appnghenhac.model.CaSi;
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

    @GET("playlist/all")
    Call<List<Playlist>> Getdata_PlaylistAll();

    @GET("chude")
    Call<List<ChuDe>> Getdata_ChuDe();

    @GET("chude/all")
    Call<List<ChuDe>> Getdata_ChuDeAll();

    @GET("baihat")
    Call<List<BaiHat>> Getdata_BaiHat();

    @GET("baihat/luotnghe")
    Call<List<BaiHat>> Getdata_BaiHatLuotNghe();

    @GET("baihat/luotthich")
    Call<List<BaiHat>> Getdata_BaiHatLuotThich();

    @GET("album")
    Call<List<Album>> Getdata_Album();

    @GET("album/all")
    Call<List<Album>> Getdata_AlbumAll();

    @GET("album/luotnghe")
    Call<List<Album>> Getdata_AlbumLuotNghe();

    @GET("casi")
    Call<List<CaSi>> Getdata_Casi();

    @GET("casi/all")
    Call<List<CaSi>> Getdata_CasiAll();

    @FormUrlEncoded
    @POST("album")
    Call<List<BaiHat>> Getdata_BaiHatTheoAlbum(@Field("idAlbum") int idAlbum);

    @FormUrlEncoded
    @POST("playlist")
    Call<List<BaiHat>> Getdata_BaiHatTheoPlaylist(@Field("idplaylist") int idplaylist);

    @FormUrlEncoded
    @POST("playlist/theochude")
    Call<List<Playlist>> Getdata_PlaylistTheoChude(@Field("idchude") int idchude);

    @FormUrlEncoded
    @POST("casi/baihat")
    Call<List<BaiHat>> Getdata_BaiHatTheoCaSy(@Field("idCasi") int idCasi);

    @FormUrlEncoded
    @POST("casi/album")
    Call<List<Album>> Getdata_AlbumTheoCaSy(@Field("idCasi") int idCasi);

    @FormUrlEncoded
    @POST("baihat/search")
    Call<List<BaiHat>> Getdata_TimKiemBaiHat(@Field("tenbaihat") String tenbaihat);

    @FormUrlEncoded
    @POST("album/search")
    Call<List<Album>> Getdata_TimKiemAlbum(@Field("tenalbum") String tenalbbum);

    @FormUrlEncoded
    @POST("playlist/search")
    Call<List<Playlist>> Getdata_TimKiemPlaylist(@Field("tenplaylist") String tenplaylist);

    @FormUrlEncoded
    @POST("casi/search")
    Call<List<CaSi>> Getdata_TimKiemCaSi(@Field("tencasi") String tencasi);


}
