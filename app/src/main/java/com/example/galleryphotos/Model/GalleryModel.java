package com.example.galleryphotos.Model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;

import com.example.galleryphotos.Entity.GalleryEntity;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.UUID;

public class GalleryModel {
    protected static String QUERY_GET_ALL = "SELECT id FROM gallery";

    public static String NewImage(SQLiteDatabase db, GalleryEntity data) {
        data.setId(UUID.randomUUID().toString());

        ContentValues row = new ContentValues();
        row.put("id", data.getId());
        //row.put("image", img);
        row.put("log", data.getLog());
        row.put("lat", data.getLat());
        row.put("address", data.getAddress());
        row.put("region", data.getRegion());
        row.put("description", data.getDescription());

        db.insert("gallery", null, row);
        db.close();

        return "image saved";
    }

    public static GalleryEntity GetAll(SQLiteDatabase db) {
        GalleryEntity data = new GalleryEntity();

        Cursor cursor = db.rawQuery(QUERY_GET_ALL, null);
        if(cursor.moveToFirst()) {
            do {
                data.setId(cursor.getString(0));
            }while (cursor.moveToFirst());
        }
        return data;
    }
}
