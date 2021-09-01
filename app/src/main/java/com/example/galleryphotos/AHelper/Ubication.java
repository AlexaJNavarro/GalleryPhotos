package com.example.galleryphotos.AHelper;

public class Ubication {
    public static String getAdreess() {
        return Adreess;
    }

    public static void setAdreess(String adreess) {
        Adreess = adreess;
    }

    public static Float getLat() {
        return lat;
    }

    public static void setLat(Float lat) {
        Ubication.lat = lat;
    }

    public static Float getLon() {
        return lon;
    }

    public static void setLon(Float lon) {
        Ubication.lon = lon;
    }

    public static String getRegion() {
        return Region;
    }

    public static void setRegion(String region) {
        Region = region;
    }

    public static String Adreess;
    public static String Region;
    public static  Float lat;
    public static  Float lon;
}
