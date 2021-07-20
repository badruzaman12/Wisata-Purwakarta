package com.badruzaman.wisatapurwakarta.Model;

import com.google.gson.annotations.SerializedName;

public class DetailModel {

    @SerializedName("deskripsi")
    private String deskripsi;

    @SerializedName("nama")
    private String nama;

    @SerializedName("latitude")
    private String latitude;

    @SerializedName("longitude")
    private String longtitude;

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(String longtitude) {
        this.longtitude = longtitude;
    }
}
