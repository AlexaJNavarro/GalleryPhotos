package com.example.galleryphotos.AHelper;

import android.graphics.Bitmap;
import android.net.Uri;

public class Image {
    public static String getDescription() {
        return Description;
    }

    public static void setDescription(String description) {
        Description = description;
    }

    public static Uri getImage() {
        return Image;
    }

    public static void setImage(Uri image) {
        Image = image;
    }

    public static String Description;
    public static Uri Image;
}
