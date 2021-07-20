package com.badruzaman.wisatapurwakarta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.TextView;

import com.badruzaman.wisatapurwakarta.Adapter.WisataAdapter;
import com.badruzaman.wisatapurwakarta.Model.DetailModel;
import com.badruzaman.wisatapurwakarta.Model.ListModel;
import com.badruzaman.wisatapurwakarta.Model.Wisata;
import com.badruzaman.wisatapurwakarta.Retrofit.ApiClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private WisataAdapter wisataAdapter;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView =findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);


        getListModel();
    }

    private void getListModel(){
        ApiClient.endPoint().getListModel()
                .enqueue(new Callback<ListModel>() {
                    @Override
                    public void onResponse(Call<ListModel> call, Response<ListModel> response) {

                        ArrayList<Wisata> wisataArrayList = response.body().getWisataArrayList();
                        wisataAdapter = new WisataAdapter(wisataArrayList);
                        wisataAdapter.notifyDataSetChanged();
                        recyclerView.setAdapter(wisataAdapter);

                        wisataAdapter.setOnItemClickCallback(new WisataAdapter.OnItemClickCallback() {
                            @Override
                            public void onItemClicked(Wisata data) {
                                showSelectedItem(data);
                            }
                        });


                    }

                    @Override
                    public void onFailure(Call<ListModel> call, Throwable t) {

                    }
                });
    }

    private void showSelectedItem (Wisata wisata){
        Intent intent = new Intent(MainActivity.this, DetailWisataActivity.class);
        intent.putExtra("detailWisata", wisata);
        startActivity(intent);

    }
}