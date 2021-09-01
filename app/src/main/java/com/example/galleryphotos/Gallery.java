package com.example.galleryphotos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.galleryphotos.Adapter.ListDirectionsAdapter;
import com.example.galleryphotos.Adapter.ListImageAdapter;
import com.example.galleryphotos.Entity.GalleryEntity;

import java.util.ArrayList;
import java.util.UUID;

public class Gallery extends AppCompatActivity {
    RecyclerView listImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);



    }

    public void Read(View view) {
        ArrayList<GalleryEntity> ge = new ArrayList<>();

        GalleryEntity g1 = new GalleryEntity();
        g1.setId(UUID.randomUUID().toString());
        g1.setRegion("dasdasd");
        g1.setDescription("dasdasd");
        g1.setAddress("dasdasd");
        g1.setLog(-451.5454f);
        g1.setLat(-154.4545f);

        GalleryEntity g2 = new GalleryEntity();
        g2.setId(UUID.randomUUID().toString());
        g2.setRegion("dasdasd");
        g2.setDescription("dasdasd");
        g2.setAddress("dasdasd");
        g2.setLog(-451.5454f);
        g2.setLat(-154.4545f);

        ge.add(g1);
        ge.add(g2);

        listImage = findViewById(R.id.listImages);
        listImage.setLayoutManager(new LinearLayoutManager(Gallery.this));
        ListImageAdapter adapter = new ListImageAdapter(ge);
        listImage.setAdapter(adapter);

        Toast.makeText(Gallery.this, ge.get(0).getId() , Toast.LENGTH_SHORT).show();
    }
}