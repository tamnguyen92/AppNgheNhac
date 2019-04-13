package com.example.jerem.appnghenhac.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Playlist implements Parcelable {

@SerializedName("id_playlist")
@Expose
private Integer idPlaylist;
@SerializedName("id_chude_playlist")
@Expose
private String idChudePlaylist;
@SerializedName("ten_playlist")
@Expose
private String tenPlaylist;
@SerializedName("hinh_playlist")
@Expose
private String hinhPlaylist;
@SerializedName("icon_playlist")
@Expose
private Object iconPlaylist;

    protected Playlist(Parcel in) {
        if (in.readByte() == 0) {
            idPlaylist = null;
        } else {
            idPlaylist = in.readInt();
        }
        idChudePlaylist = in.readString();
        tenPlaylist = in.readString();
        hinhPlaylist = in.readString();
    }

    public static final Creator<Playlist> CREATOR = new Creator<Playlist>() {
        @Override
        public Playlist createFromParcel(Parcel in) {
            return new Playlist(in);
        }

        @Override
        public Playlist[] newArray(int size) {
            return new Playlist[size];
        }
    };

    public Integer getIdPlaylist() {
return idPlaylist;
}

public void setIdPlaylist(Integer idPlaylist) {
this.idPlaylist = idPlaylist;
}

public String getIdChudePlaylist() {
return idChudePlaylist;
}

public void setIdChudePlaylist(String idChudePlaylist) {
this.idChudePlaylist = idChudePlaylist;
}

public String getTenPlaylist() {
return tenPlaylist;
}

public void setTenPlaylist(String tenPlaylist) {
this.tenPlaylist = tenPlaylist;
}

public String getHinhPlaylist() {
return hinhPlaylist;
}

public void setHinhPlaylist(String hinhPlaylist) {
this.hinhPlaylist = hinhPlaylist;
}

public Object getIconPlaylist() {
return iconPlaylist;
}

public void setIconPlaylist(Object iconPlaylist) {
this.iconPlaylist = iconPlaylist;
}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (idPlaylist == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(idPlaylist);
        }
        dest.writeString(idChudePlaylist);
        dest.writeString(tenPlaylist);
        dest.writeString(hinhPlaylist);
    }
}