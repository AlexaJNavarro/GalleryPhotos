package com.example.galleryphotos.Model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.galleryphotos.Entity.GalleryEntity;

import java.util.ArrayList;
import java.util.UUID;

public class GalleryModel {
    protected static String QUERY_GET_ALL = "SELECT id FROM gallery";

    public static String NewImage(SQLiteDatabase db, GalleryEntity data) {
        data.setId(UUID.randomUUID().toString());

        ContentValues row = new ContentValues();
        row.put("id", data.getId());
        row.put("image", data.getImage());
        row.put("log", data.getLog());
        row.put("lat", data.getLat());
        row.put("address", data.getAddress());
        row.put("region", data.getRegion());
        row.put("description", data.getDescription());

        try {
            db.insert("gallery", null, row);
        } catch (Exception e) {
            return e.getMessage();
        }
        db.close();

        return "image saved";
    }

    public static ArrayList<GalleryEntity> GetAll(SQLiteDatabase db) {
        //GalleryEntity data = new GalleryEntity();
        ArrayList<GalleryEntity> data = new ArrayList<>();

        String selectQuery = "SELECT id, image, log, lat, address, region, description FROM gallery";

        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do{
                GalleryEntity galleryEntity = new GalleryEntity(
                        cursor.getString(1),
                        cursor.getDouble(2),
                        cursor.getDouble(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6)
                );
                galleryEntity.setId(cursor.getString(0));
                data.add(galleryEntity);
            }while (cursor.moveToNext());

        }

        return data;
    }

}
