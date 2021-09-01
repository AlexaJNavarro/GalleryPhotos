package com.example.galleryphotos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.galleryphotos.AHelper.Image;

public class test extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        ImageView img = findViewById(R.id.image_saved);
        img.setImageURI(Image.getImage());

        TextView lbl = findViewById(R.id.lbl_route_image);
        lbl.setText(Image.getImage().toString());
    }
}