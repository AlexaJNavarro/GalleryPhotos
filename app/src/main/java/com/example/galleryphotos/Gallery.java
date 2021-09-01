package com.example.galleryphotos;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.galleryphotos.Adapter.ListImageAdapter;
import com.example.galleryphotos.DBUtils.adminSQLiteOpenHelper;
import com.example.galleryphotos.Entity.GalleryEntity;
import com.example.galleryphotos.Model.GalleryModel;

import java.util.ArrayList;

public class Gallery extends FragmentActivity {

    ArrayList<GalleryEntity> ge;
    RecyclerView listImages;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        ge = GalleryModel.GetAll(this.getConnection());

    }





    private SQLiteDatabase getConnection() {
        adminSQLiteOpenHelper admin = new adminSQLiteOpenHelper(this, "gallery", null, 1);
        return admin.getWritableDatabase();
    }






    public void Read(View view) {
        System.out.println("*****************************************************************************");
        listImages = findViewById(R.id.listImages);
        listImages.setLayoutManager(new LinearLayoutManager(Gallery.this));
        ListImageAdapter adapter = new ListImageAdapter(ge);
        listImages.setAdapter(adapter);
        System.out.println("*****************************************************************************");

        for(int x=0; x<GalleryModel.GetAll(this.getConnection()).size(); x++){
            System.out.println(GalleryModel.GetAll(this.getConnection()).get(x).getImage());
            System.out.println(GalleryModel.GetAll(this.getConnection()).get(x).getAddress());

        }
        //ListImageAdapter listAdapter = new ListImageAdapter(GalleryModel.GetAll(this.getConnection()));
        //listImage.setLayoutManager(new LinearLayoutManager(Gallery.this));
        //listImage.setAdapter(listAdapter);

    }
}