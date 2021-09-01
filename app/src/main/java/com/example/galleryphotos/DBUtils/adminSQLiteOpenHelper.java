package com.example.galleryphotos.DBUtils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class adminSQLiteOpenHelper extends SQLiteOpenHelper {

    public adminSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE gallery(" +
                "id text primary key, " +
                "image text, " +
                "log real, " +
                "lat real, " +
                "address text, " +
                "region text, " +
                "description text" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}
