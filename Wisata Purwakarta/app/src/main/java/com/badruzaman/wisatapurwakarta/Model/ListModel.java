package com.badruzaman.wisatapurwakarta.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ListModel {

    @SerializedName("wisata")
    private ArrayList<Wisata> wisataArrayList;

    public ArrayList<Wisata> getWisataArrayList() {
        return wisataArrayList;
    }

    public void setWisataArrayList(ArrayList<Wisata> wisataArrayList) {
        this.wisataArrayList = wisataArrayList;
    }
}
