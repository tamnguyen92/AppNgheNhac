package com.example.jerem.appnghenhac.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BaiHat implements Parcelable {

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
@SerializedName("tencasi_baihat")
@Expose
private String tencasiBaihat;

    protected BaiHat(Parcel in) {
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
        tencasiBaihat = in.readString();
    }

    public static final Creator<BaiHat> CREATOR = new Creator<BaiHat>() {
        @Override
        public BaiHat createFromParcel(Parcel in) {
            return new BaiHat(in);
        }

        @Override
        public BaiHat[] newArray(int size) {
            return new BaiHat[size];
        }
    };

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
        dest.writeString(tencasiBaihat);
    }

    public BaiHat(Integer idBaihat, String tenbaihat, String hinhanhBaihat, String linkBaibat, Integer idCasiBaihat, Integer idAlbumBaihat, Integer idPlaylistBaihat, Integer luotthichBaihat, Integer luotngheBaihat, String tencasiBaihat) {
        this.idBaihat = idBaihat;
        this.tenbaihat = tenbaihat;
        this.hinhanhBaihat = hinhanhBaihat;
        this.linkBaibat = linkBaibat;
        this.idCasiBaihat = idCasiBaihat;
        this.idAlbumBaihat = idAlbumBaihat;
        this.idPlaylistBaihat = idPlaylistBaihat;
        this.luotthichBaihat = luotthichBaihat;
        this.luotngheBaihat = luotngheBaihat;
        this.tencasiBaihat = tencasiBaihat;
    }
}