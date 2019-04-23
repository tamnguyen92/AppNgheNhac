package com.example.jerem.appnghenhac.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TaiKhoan implements Parcelable {
    public TaiKhoan() {
    }

    @SerializedName("id_TaiKhoan")
@Expose
private Integer idTaiKhoan;
@SerializedName("username")
@Expose
private String username;
@SerializedName("password")
@Expose
private String password;
@SerializedName("hoten")
@Expose
private Object hoten;
@SerializedName("loaiTK")
@Expose
private Integer loaiTK;
@SerializedName("email")
@Expose
private Object email;
@SerializedName("id_socical")
@Expose
private Integer idSocical;

    protected TaiKhoan(Parcel in) {
        if (in.readByte() == 0) {
            idTaiKhoan = null;
        } else {
            idTaiKhoan = in.readInt();
        }
        username = in.readString();
        password = in.readString();
        if (in.readByte() == 0) {
            loaiTK = null;
        } else {
            loaiTK = in.readInt();
        }
        if (in.readByte() == 0) {
            idSocical = null;
        } else {
            idSocical = in.readInt();
        }
    }

    public static final Creator<TaiKhoan> CREATOR = new Creator<TaiKhoan>() {
        @Override
        public TaiKhoan createFromParcel(Parcel in) {
            return new TaiKhoan(in);
        }

        @Override
        public TaiKhoan[] newArray(int size) {
            return new TaiKhoan[size];
        }
    };

    public Integer getIdTaiKhoan() {
return idTaiKhoan;
}

public void setIdTaiKhoan(Integer idTaiKhoan) {
this.idTaiKhoan = idTaiKhoan;
}

public String getUsername() {
return username;
}

public void setUsername(String username) {
this.username = username;
}

public String getPassword() {
return password;
}

public void setPassword(String password) {
this.password = password;
}

public Object getHoten() {
return hoten;
}

public void setHoten(Object hoten) {
this.hoten = hoten;
}

public Integer getLoaiTK() {
return loaiTK;
}

public void setLoaiTK(Integer loaiTK) {
this.loaiTK = loaiTK;
}

public Object getEmail() {
return email;
}

public void setEmail(Object email) {
this.email = email;
}

public Integer getIdSocical() {
return idSocical;
}

public void setIdSocical(Integer idSocical) {
this.idSocical = idSocical;
}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (idTaiKhoan == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(idTaiKhoan);
        }
        dest.writeString(username);
        dest.writeString(password);
        if (loaiTK == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(loaiTK);
        }
        if (idSocical == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(idSocical);
        }
    }
}