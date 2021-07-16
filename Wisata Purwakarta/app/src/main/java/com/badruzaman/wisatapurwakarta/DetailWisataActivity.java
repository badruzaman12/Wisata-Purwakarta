package com.badruzaman.wisatapurwakarta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.badruzaman.wisatapurwakarta.Model.DetailModel;
import com.badruzaman.wisatapurwakarta.Model.Wisata;
import com.badruzaman.wisatapurwakarta.Retrofit.ApiClient;
import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailWisataActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    TextView tvNamaTempat, tvDescWisata;
    ImageView imgDetail;
    Wisata wisata;
    String namaTempat, imageDetail, desc, id, latitude, longtitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_wisata);

        tvNamaTempat = findViewById(R.id.namaTempat);
        imgDetail = findViewById(R.id.imgDetail);
        tvDescWisata = findViewById(R.id.descWisata);


        wisata = (Wisata) getIntent().getSerializableExtra("detailWisata");
        id = wisata.getId();
        namaTempat = wisata.getNama();
        imageDetail = wisata.getGambarUrl();

        tvNamaTempat.setText(namaTempat);
        Glide.with(DetailWisataActivity.this)
                .load(imageDetail)
                .into(imgDetail);

        getDetailModel();

        //show maps
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    private void getDetailModel(){

        ApiClient.endPoint().getDetailModel(id)
                .enqueue(new Callback<DetailModel>() {
                    @Override
                    public void onResponse(Call<DetailModel> call, Response<DetailModel> response) {

                        namaTempat = response.body().getNama();
                        desc = response.body().getDeskripsi();

                        latitude = response.body().getLatitude();
                        longtitude = response.body().getLongtitude();

                        Log.d("cek", "data masuk" + latitude);

                        tvNamaTempat.setText(namaTempat);
                        tvDescWisata.setText(desc);

                    }

                    @Override
                    public void onFailure(Call<DetailModel> call, Throwable t) {

                    }
                });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        ApiClient.endPoint().getDetailModel(id)
                .enqueue(new Callback<DetailModel>() {
                    @Override
                    public void onResponse(Call<DetailModel> call, Response<DetailModel> response) {

                        latitude = response.body().getLatitude();
                        longtitude = response.body().getLongtitude();

                        Log.d("cek", "data masuk" + latitude);

                        mMap = googleMap;

                        Double lat = Double.parseDouble(latitude);
                        Double lon= Double.parseDouble(longtitude);

                        LatLng latLng = new LatLng(lat,lon);
                        mMap.addMarker(new MarkerOptions().position(latLng).title(namaTempat));
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16));
                        mMap.getUiSettings().setAllGesturesEnabled(true);
                        mMap.getUiSettings().setZoomGesturesEnabled(true);
                        mMap.setTrafficEnabled(true);

                    }

                    @Override
                    public void onFailure(Call<DetailModel> call, Throwable t) {

                    }
                });

    }
}