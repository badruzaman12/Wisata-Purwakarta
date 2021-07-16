package com.badruzaman.wisatapurwakarta.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.badruzaman.wisatapurwakarta.Model.Wisata;
import com.badruzaman.wisatapurwakarta.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class WisataAdapter extends RecyclerView.Adapter<WisataAdapter.MyListViewHolder> {

    private ArrayList<Wisata> wisataArrayList;

    public WisataAdapter(ArrayList<Wisata> wisataArrayList) {
        this.wisataArrayList = wisataArrayList;
    }

    public interface OnItemClickCallback {
        void onItemClicked(Wisata data);
    }

    private OnItemClickCallback onItemClickCallback;

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public WisataAdapter.MyListViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
        return new MyListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull WisataAdapter.MyListViewHolder holder, int position) {

        holder.tvNama.setText(wisataArrayList.get(position).getNama());
        Glide.with(holder.itemView.getContext())
                .load(wisataArrayList.get(position).getGambarUrl())
                .into(holder.imgWisata);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(wisataArrayList.get(holder.getBindingAdapterPosition()));
            }
        });

    }

    @Override
    public int getItemCount() {
        return wisataArrayList.size();
    }

    public class MyListViewHolder extends RecyclerView.ViewHolder {

        TextView tvNama;
        ImageView imgWisata;

        public MyListViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);

            tvNama = itemView.findViewById(R.id.item_namaWisata);
            imgWisata = itemView.findViewById(R.id.item_image);

        }
    }
}
