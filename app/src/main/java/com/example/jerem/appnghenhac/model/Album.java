package com.example.jerem.appnghenhac.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Album implements Parcelable {

    @SerializedName("id_album")
    @Expose
    private Integer idAlbum;
    @SerializedName("ten_album")
    @Expose
    private String tenAlbum;
    @SerializedName("id_casi_album")
    @Expose
    private Integer idCasiAlbum;
    @SerializedName("hinh_album")
    @Expose
    private String hinhAlbum;
    @SerializedName("id_theloai_album")
    @Expose
    private Object idTheloaiAlbum;
    @SerializedName("id_casi")
    @Expose
    private Integer idCasi;
    @SerializedName("hinhanh_casi")
    @Expose
    private String hinhanhCasi;
    @SerializedName("tencasy")
    @Expose
    private String tencasy;
    @SerializedName("hinhanhlon_casi")
    @Expose
    private String hinhanhlonCasi;

    protected Album(Parcel in) {
        if (in.readByte() == 0) {
            idAlbum = null;
        } else {
            idAlbum = in.readInt();
        }
        tenAlbum = in.readString();
        if (in.readByte() == 0) {
            idCasiAlbum = null;
        } else {
            idCasiAlbum = in.readInt();
        }
        hinhAlbum = in.readString();
        if (in.readByte() == 0) {
            idCasi = null;
        } else {
            idCasi = in.readInt();
        }
        hinhanhCasi = in.readString();
        tencasy = in.readString();
        hinhanhlonCasi = in.readString();
    }

    public static final Creator<Album> CREATOR = new Creator<Album>() {
        @Override
        public Album createFromParcel(Parcel in) {
            return new Album(in);
        }

        @Override
        public Album[] newArray(int size) {
            return new Album[size];
        }
    };

    public Integer getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(Integer idAlbum) {
        this.idAlbum = idAlbum;
    }

    public String getTenAlbum() {
        return tenAlbum;
    }

    public void setTenAlbum(String tenAlbum) {
        this.tenAlbum = tenAlbum;
    }

    public Integer getIdCasiAlbum() {
        return idCasiAlbum;
    }

    public void setIdCasiAlbum(Integer idCasiAlbum) {
        this.idCasiAlbum = idCasiAlbum;
    }

    public String getHinhAlbum() {
        return hinhAlbum;
    }

    public void setHinhAlbum(String hinhAlbum) {
        this.hinhAlbum = hinhAlbum;
    }

    public Object getIdTheloaiAlbum() {
        return idTheloaiAlbum;
    }

    public void setIdTheloaiAlbum(Object idTheloaiAlbum) {
        this.idTheloaiAlbum = idTheloaiAlbum;
    }

    public Integer getIdCasi() {
        return idCasi;
    }

    public void setIdCasi(Integer idCasi) {
        this.idCasi = idCasi;
    }

    public String getHinhanhCasi() {
        return hinhanhCasi;
    }

    public void setHinhanhCasi(String hinhanhCasi) {
        this.hinhanhCasi = hinhanhCasi;
    }

    public String getTencasy() {
        return tencasy;
    }

    public void setTencasy(String tencasy) {
        this.tencasy = tencasy;
    }

    public String getHinhanhlonCasi() {
        return hinhanhlonCasi;
    }

    public void setHinhanhlonCasi(String hinhanhlonCasi) {
        this.hinhanhlonCasi = hinhanhlonCasi;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (idAlbum == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(idAlbum);
        }
        dest.writeString(tenAlbum);
        if (idCasiAlbum == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(idCasiAlbum);
        }
        dest.writeString(hinhAlbum);
        if (idCasi == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(idCasi);
        }
        dest.writeString(hinhanhCasi);
        dest.writeString(tencasy);
        dest.writeString(hinhanhlonCasi);
    }
}