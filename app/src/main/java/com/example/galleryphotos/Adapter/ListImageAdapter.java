package com.example.galleryphotos.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.galleryphotos.AHelper.Ubication;
import com.example.galleryphotos.AModels.Data;
import com.example.galleryphotos.Entity.GalleryEntity;
import com.example.galleryphotos.Navigation;
import com.example.galleryphotos.R;

import java.util.ArrayList;

public class ListImageAdapter extends RecyclerView.Adapter<ListImageAdapter.DirectionsViewHolder> {
    ArrayList<GalleryEntity> listImages;

    public ListImageAdapter (ArrayList<GalleryEntity> listImages){
        this.listImages = listImages;
    }

    @NonNull
    @Override
    public DirectionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_images, null, false);
        return new  ListImageAdapter.DirectionsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListImageAdapter.DirectionsViewHolder holder, int position) {
        holder.lblDirection.setText(listImages.get(position).getAddress());
        holder.lblRegion.setText(listImages.get(position).getRegion());
        holder.lblLat.setText((int) listImages.get(position).getLat());
        holder.lblLog.setText((int) listImages.get(position).getLog());
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ubication.setLat(Float.parseFloat(holder.lblLat.getText().toString()));
                Ubication.setLon(Float.parseFloat(holder.lblLog.getText().toString()));
                Ubication.setAdreess(holder.lblDirection.getText().toString());

                Intent i = new Intent(view.getContext(), Navigation.class);
                view.getContext().startActivity(i);

            }
        });
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class DirectionsViewHolder extends RecyclerView.ViewHolder {
        TextView lblDirection, lblRegion, lblLog, lblLat;
        Button btn;
        public DirectionsViewHolder(@NonNull View itemView) {
            super(itemView);
            lblDirection = itemView.findViewById(R.id.lbl_direction_image);
            lblRegion = itemView.findViewById(R.id.lbl_region_image);
            lblLog = itemView.findViewById(R.id.lbl_log_image);
            lblLat = itemView.findViewById(R.id.lbl_lat_image);
            btn = itemView.findViewById(R.id.btn_cor_image);
        }
    }
}
