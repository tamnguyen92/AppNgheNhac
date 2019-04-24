package com.example.jerem.appnghenhac.model;


import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class QuangCao implements Parcelable {

    @SerializedName("id_quangcao")
    @Expose
    private Integer idQuangcao;
    @SerializedName("hinh_quangcao")
    @Expose
    private String hinhQuangcao;
    @SerializedName("id_baihat_quangcao")
    @Expose
    private Integer idBaihatQuangcao;
    @SerializedName("noidung_quangcao")
    @Expose
    private Object noidungQuangcao;
    @SerializedName("id_baihat")
    @Expose
    private Integer idBaihat;
    @SerializedName("tenbaihat")
    @Expose
    private String tenbaihat;
    @SerializedName("hinhanh_baihat")
    @Expose
    private String hinhanhBaihat;
    @SerializedName("link_baibat")
    @Expose
    private String linkBaibat;
    @SerializedName("id_casi_baihat")
    @Expose
    private Integer idCasiBaihat;
    @SerializedName("id_album_baihat")
    @Expose
    private Integer idAlbumBaihat;
    @SerializedName("id_playlist_baihat")
    @Expose
    private Integer idPlaylistBaihat;
    @SerializedName("luotthich_baihat")
    @Expose
    private Integer luotthichBaihat;
    @SerializedName("luotnghe_baihat")
    @Expose
    private Integer luotngheBaihat;
    @SerializedName("thoigian")
    @Expose
    private String thoigian;
    @SerializedName("tencasi_baihat")
    @Expose
    private String tencasiBaihat;

    protected QuangCao(Parcel in) {
        if (in.readByte() == 0) {
            idQuangcao = null;
        } else {
            idQuangcao = in.readInt();
        }
        hinhQuangcao = in.readString();
        if (in.readByte() == 0) {
            idBaihatQuangcao = null;
        } else {
            idBaihatQuangcao = in.readInt();
        }
        if (in.readByte() == 0) {
            idBaihat = null;
        } else {
            idBaihat = in.readInt();
        }
        tenbaihat = in.readString();
        hinhanhBaihat = in.readString();
        linkBaibat = in.readString();
        if (in.readByte() == 0) {
            idCasiBaihat = null;
        } else {
            idCasiBaihat = in.readInt();
        }
        if (in.readByte() == 0) {
            idAlbumBaihat = null;
        } else {
            idAlbumBaihat = in.readInt();
        }
        if (in.readByte() == 0) {
            idPlaylistBaihat = null;
        } else {
            idPlaylistBaihat = in.readInt();
        }
        if (in.readByte() == 0) {
            luotthichBaihat = null;
        } else {
            luotthichBaihat = in.readInt();
        }
        if (in.readByte() == 0) {
            luotngheBaihat = null;
        } else {
            luotngheBaihat = in.readInt();
        }
        thoigian = in.readString();
        tencasiBaihat = in.readString();
    }

    public static final Creator<QuangCao> CREATOR = new Creator<QuangCao>() {
        @Override
        public QuangCao createFromParcel(Parcel in) {
            return new QuangCao(in);
        }

        @Override
        public QuangCao[] newArray(int size) {
            return new QuangCao[size];
        }
    };

    public Integer getIdQuangcao() {
        return idQuangcao;
    }

    public void setIdQuangcao(Integer idQuangcao) {
        this.idQuangcao = idQuangcao;
    }

    public String getHinhQuangcao() {
        return hinhQuangcao;
    }

    public void setHinhQuangcao(String hinhQuangcao) {
        this.hinhQuangcao = hinhQuangcao;
    }

    public Integer getIdBaihatQuangcao() {
        return idBaihatQuangcao;
    }

    public void setIdBaihatQuangcao(Integer idBaihatQuangcao) {
        this.idBaihatQuangcao = idBaihatQuangcao;
    }

    public Object getNoidungQuangcao() {
        return noidungQuangcao;
    }

    public void setNoidungQuangcao(Object noidungQuangcao) {
        this.noidungQuangcao = noidungQuangcao;
    }

    public Integer getIdBaihat() {
        return idBaihat;
    }

    public void setIdBaihat(Integer idBaihat) {
        this.idBaihat = idBaihat;
    }

    public String getTenbaihat() {
        return tenbaihat;
    }

    public void setTenbaihat(String tenbaihat) {
        this.tenbaihat = tenbaihat;
    }

    public String getHinhanhBaihat() {
        return hinhanhBaihat;
    }

    public void setHinhanhBaihat(String hinhanhBaihat) {
        this.hinhanhBaihat = hinhanhBaihat;
    }

    public String getLinkBaibat() {
        return linkBaibat;
    }

    public void setLinkBaibat(String linkBaibat) {
        this.linkBaibat = linkBaibat;
    }

    public Integer getIdCasiBaihat() {
        return idCasiBaihat;
    }

    public void setIdCasiBaihat(Integer idCasiBaihat) {
        this.idCasiBaihat = idCasiBaihat;
    }

    public Integer getIdAlbumBaihat() {
        return idAlbumBaihat;
    }

    public void setIdAlbumBaihat(Integer idAlbumBaihat) {
        this.idAlbumBaihat = idAlbumBaihat;
    }

    public Integer getIdPlaylistBaihat() {
        return idPlaylistBaihat;
    }

    public void setIdPlaylistBaihat(Integer idPlaylistBaihat) {
        this.idPlaylistBaihat = idPlaylistBaihat;
    }

    public Integer getLuotthichBaihat() {
        return luotthichBaihat;
    }

    public void setLuotthichBaihat(Integer luotthichBaihat) {
        this.luotthichBaihat = luotthichBaihat;
    }

    public Integer getLuotngheBaihat() {
        return luotngheBaihat;
    }

    public void setLuotngheBaihat(Integer luotngheBaihat) {
        this.luotngheBaihat = luotngheBaihat;
    }

    public String getThoigian() {
        return thoigian;
    }

    public void setThoigian(String thoigian) {
        this.thoigian = thoigian;
    }

    public String getTencasiBaihat() {
        return tencasiBaihat;
    }

    public void setTencasiBaihat(String tencasiBaihat) {
        this.tencasiBaihat = tencasiBaihat;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (idQuangcao == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(idQuangcao);
        }
        dest.writeString(hinhQuangcao);
        if (idBaihatQuangcao == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(idBaihatQuangcao);
        }
        if (idBaihat == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(idBaihat);
        }
        dest.writeString(tenbaihat);
        dest.writeString(hinhanhBaihat);
        dest.writeString(linkBaibat);
        if (idCasiBaihat == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(idCasiBaihat);
        }
        if (idAlbumBaihat == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(idAlbumBaihat);
        }
        if (idPlaylistBaihat == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(idPlaylistBaihat);
        }
        if (luotthichBaihat == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(luotthichBaihat);
        }
        if (luotngheBaihat == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(luotngheBaihat);
        }
        dest.writeString(thoigian);
        dest.writeString(tencasiBaihat);
    }
}