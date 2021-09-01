package com.example.galleryphotos;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.example.galleryphotos.AHelper.Func;
import com.example.galleryphotos.AHelper.Image;
import com.example.galleryphotos.AHelper.Ubication;
import com.example.galleryphotos.DBUtils.adminSQLiteOpenHelper;
import com.example.galleryphotos.Entity.GalleryEntity;
import com.example.galleryphotos.Model.GalleryModel;
import com.example.galleryphotos.databinding.ActivityNavigationBinding;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Navigation extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityNavigationBinding binding;

    private SQLiteDatabase getConnection() {
        adminSQLiteOpenHelper admin = new adminSQLiteOpenHelper(this, "gallery", null, 1);
        return admin.getWritableDatabase();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityNavigationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        Toast.makeText(Navigation.this, "Lat: " + Ubication.getLat(), Toast.LENGTH_SHORT).show();

        LatLng sydney = new LatLng(Ubication.getLat(), Ubication.getLon());
        mMap.addMarker(new MarkerOptions().position(sydney).title(Ubication.getAdreess()));
        CameraPosition CameraPosition = new CameraPosition.Builder().target(sydney).zoom(13).build();
        CameraUpdate update = CameraUpdateFactory.newCameraPosition(CameraPosition);
        mMap.moveCamera(update);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    public void BtnSave(View view) {
        String response = GalleryModel.NewImage(
                this.getConnection(),
                new GalleryEntity(Image.getImage().toString(), Ubication.getLon(), Ubication.getLat(),
                        Ubication.getAdreess(), Ubication.getRegion(), Image.getDescription())
                );
        Toast.makeText(Navigation.this, response, Toast.LENGTH_SHORT).show();
    }
}