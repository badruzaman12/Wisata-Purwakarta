package com.badruzaman.wisatapurwakarta.Retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static String BASE_URL = "https://dev.farizdotid.com/api/";

    private static Retrofit retrofit;

    public static ApiEndPoint endPoint(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(ApiEndPoint.class);
    }

}
