package com.example.galleryphotos.Adapter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.galleryphotos.Entity.GalleryEntity;
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
        holder.image_address.setText(listImage.get(position).getAddress());
        holder.image_description.setText(listImage.get(position).getDescription());
        holder.image_photos.setImageURI(Uri.parse(listImage.get(position).getImage()));

    }

    @Override
    public int getItemCount() {
        return listImage.size();
    }

    public class DirectionsViewHolder extends RecyclerView.ViewHolder {
        TextView image_id, image_address, image_description;
        ImageView image_photos;
        public DirectionsViewHolder(@NonNull View itemView) {
            super(itemView);
            image_id = itemView.findViewById(R.id.id_image);
            image_address = itemView.findViewById(R.id.image_address);
            image_description = itemView.findViewById(R.id.image_description);
            image_photos = itemView.findViewById(R.id.image_photo);
        }
    }
}
