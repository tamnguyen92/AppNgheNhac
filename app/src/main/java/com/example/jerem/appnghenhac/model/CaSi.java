package com.example.jerem.appnghenhac.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CaSi implements Parcelable {

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

    protected CaSi(Parcel in) {
        if (in.readByte() == 0) {
            idCasi = null;
        } else {
            idCasi = in.readInt();
        }
        hinhanhCasi = in.readString();
        tencasy = in.readString();
        hinhanhlonCasi = in.readString();
    }

    public static final Creator<CaSi> CREATOR = new Creator<CaSi>() {
        @Override
        public CaSi createFromParcel(Parcel in) {
            return new CaSi(in);
        }

        @Override
        public CaSi[] newArray(int size) {
            return new CaSi[size];
        }
    };

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