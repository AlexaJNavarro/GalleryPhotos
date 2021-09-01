package com.example.galleryphotos.Adapter;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.galleryphotos.AHelper.Ubication;
import com.example.galleryphotos.Entity.GalleryEntity;
import com.example.galleryphotos.Navigation;
import com.example.galleryphotos.R;

import java.util.ArrayList;

public class ListImageAdapter extends RecyclerView.Adapter<ListImageAdapter.DirectionsViewHolder> {

    ArrayList<GalleryEntity> listImage;

    public ListImageAdapter(ArrayList<GalleryEntity> listImage){
        this.listImage = listImage;
    }

    @NonNull
    @Override
    public ListImageAdapter.DirectionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_image, null, false);
        return new  ListImageAdapter.DirectionsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListImageAdapter.DirectionsViewHolder holder, int position) {
        holder.image_description.setText(listImage.get(position).getDescription());
        holder.image_address.setText(listImage.get(position).getAddress());
        holder.image_region.setText(listImage.get(position).getRegion());
        holder.image_lon.setText(Double.toString(listImage.get(position).getLog()));
        holder.image_lat.setText(Double.toString(listImage.get(position).getLat()));
        holder.image_photos.setImageURI(Uri.parse(listImage.get(position).getImage()));
        holder.btn_dir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ubication.setLat(Float.parseFloat(holder.image_lat.getText().toString()));
                Ubication.setLon(Float.parseFloat(holder.image_lon.getText().toString()));
                Ubication.setAdreess(holder.image_address.getText().toString());

                Intent i = new Intent(view.getContext(), Navigation.class);
                view.getContext().startActivity(i);

            }
        });

    }

    @Override
    public int getItemCount() {
        return listImage.size();
    }

    public class DirectionsViewHolder extends RecyclerView.ViewHolder {
        TextView  image_description, image_address, image_region, image_lon, image_lat;
        ImageView image_photos;
        Button btn_dir;
        public DirectionsViewHolder(@NonNull View itemView) {
            super(itemView);
            image_description = itemView.findViewById(R.id.image_description);
            image_address = itemView.findViewById(R.id.image_address);
            image_region = itemView.findViewById(R.id.image_region);
            image_lon = itemView.findViewById(R.id.image_lon);
            image_lat = itemView.findViewById(R.id.image_lat);
            image_photos = itemView.findViewById(R.id.image_photo);
            btn_dir = itemView.findViewById(R.id.btn_dir);
        }
    }
}
