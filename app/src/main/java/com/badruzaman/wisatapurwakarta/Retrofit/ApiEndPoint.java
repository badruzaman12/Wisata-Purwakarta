package com.badruzaman.wisatapurwakarta.Retrofit;

import com.badruzaman.wisatapurwakarta.Model.DetailModel;
import com.badruzaman.wisatapurwakarta.Model.ListModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiEndPoint {

    @GET("purwakarta/wisata/")
    Call<ListModel> getListModel();

    @GET("purwakarta/wisata/{id}")
    Call<DetailModel> getDetailModel(@Path("id") String id);

}
