package com.example.galleryphotos.AHelper;

import android.graphics.Bitmap;

public class Image {
    public static String Description;
    public static Bitmap Image;

    public static String getDescription() {
        return Description;
    }

    public static void setDescription(String description) {
        Description = description;
    }

    public static Bitmap getImage() {
        return Image;
    }

    public static void setImage(Bitmap image) {
        Image = image;
    }
}
