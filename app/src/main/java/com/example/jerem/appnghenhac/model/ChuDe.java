package com.example.jerem.appnghenhac.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChuDe implements Parcelable {

@SerializedName("id_chude")
@Expose
private Integer idChude;
@SerializedName("ten_chude")
@Expose
private String tenChude;
@SerializedName("hinh_chude")
@Expose
private String hinhChude;

    protected ChuDe(Parcel in) {
        if (in.readByte() == 0) {
            idChude = null;
        } else {
            idChude = in.readInt();
        }
        tenChude = in.readString();
        hinhChude = in.readString();
    }

    public static final Creator<ChuDe> CREATOR = new Creator<ChuDe>() {
        @Override
        public ChuDe createFromParcel(Parcel in) {
            return new ChuDe(in);
        }

        @Override
        public ChuDe[] newArray(int size) {
            return new ChuDe[size];
        }
    };

    public Integer getIdChude() {
return idChude;
}

public void setIdChude(Integer idChude) {
this.idChude = idChude;
}

public String getTenChude() {
return tenChude;
}

public void setTenChude(String tenChude) {
this.tenChude = tenChude;
}

public String getHinhChude() {
return hinhChude;
}

public void setHinhChude(String hinhChude) {
this.hinhChude = hinhChude;
}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (idChude == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(idChude);
        }
        dest.writeString(tenChude);
        dest.writeString(hinhChude);
    }
}