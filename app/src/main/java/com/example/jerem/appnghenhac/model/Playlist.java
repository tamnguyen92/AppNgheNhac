package com.example.jerem.appnghenhac.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Playlist {

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

}