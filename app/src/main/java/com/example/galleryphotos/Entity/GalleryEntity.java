package com.example.galleryphotos.Entity;

import android.graphics.Bitmap;

public class GalleryEntity {

    private String Id;

    public GalleryEntity() {
    }

    public GalleryEntity(String image, double log, double lat, String address, String region, String description) {
        Image = image;
        Log = log;
        Lat = lat;
        Address = address;
        Region = region;
        Description = description;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public double getLog() {
        return Log;
    }

    public void setLog(double log) {
        Log = log;
    }

    public double getLat() {
        return Lat;
    }

    public void setLat(double lat) {
        Lat = lat;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getRegion() {
        return Region;
    }

    public void setRegion(String region) {
        Region = region;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    private String Image;
    private double Log;
    private double Lat;
    private String Address;
    private String Region;
    private String Description;
}
