package com.example.jerem.appnghenhac.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChuDe {

@SerializedName("id_chude")
@Expose
private Integer idChude;
@SerializedName("ten_chude")
@Expose
private String tenChude;
@SerializedName("hinh_chude")
@Expose
private String hinhChude;

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

}