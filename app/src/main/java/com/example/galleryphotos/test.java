package com.example.galleryphotos;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.galleryphotos.DBUtils.adminSQLiteOpenHelper;
import com.example.galleryphotos.Model.GalleryModel;

public class test extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

    }

    public  void get(View view){
        TextView lbl = findViewById(R.id.lbl_route_image);

        System.out.println("****************************************************************************************************");
        System.out.println("****************************************************************************************************");
        System.out.println("****************************************************************************************************");
        System.out.println("****************************************************************************************************");
        System.out.println("****************************************************************************************************");
        for(int x=0; x<GalleryModel.GetAll(this.getConnection()).size(); x++){
            System.out.println(GalleryModel.GetAll(this.getConnection()).get(x).getImage());
            System.out.println(GalleryModel.GetAll(this.getConnection()).get(x).getAddress());

        }

        lbl.setText(GalleryModel.GetAll(this.getConnection()).toString());
        System.out.println("CONEXION: " + this.getConnection().toString());
        System.out.println("Tamaño: " + GalleryModel.GetAll(this.getConnection()).size());
    }

    private SQLiteDatabase getConnection() {
        adminSQLiteOpenHelper admin = new adminSQLiteOpenHelper(this, "gallery", null, 1);
        return admin.getWritableDatabase();
    }


}