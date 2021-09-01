package com.example.galleryphotos;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.galleryphotos.AInterfaces.LocationNameApi;
import com.example.galleryphotos.AModels.Data;
import com.example.galleryphotos.AModels.Location;
import com.example.galleryphotos.Adapter.ListDirectionsAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MapsActivity extends FragmentActivity {


    EditText txt_location;
    RecyclerView listDirection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

    }

    public void ButtonSearch(View view){
        apiPlaces();
    }

    public void apiPlaces() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.1.6:8081/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        LocationNameApi locationNameApi = retrofit.create(LocationNameApi.class);

        txt_location = findViewById(R.id.id_location);

        Call<com.example.galleryphotos.AModels.Location> call = locationNameApi.find(txt_location.getText().toString());
        call.enqueue(new Callback<Location>() {
            @Override
            public void onResponse(Call<Location> call, Response<Location> response) {
                if (response.code() == 200) {
                    Location location = response.body();
                    ArrayList<Data> res = location.getData();


                    listDirection = findViewById(R.id.listDirections);
                    listDirection.setLayoutManager(new LinearLayoutManager(MapsActivity.this));
                    ListDirectionsAdapter adapter = new ListDirectionsAdapter(res);
                    listDirection.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<Location> call, Throwable t) {
                Toast.makeText(MapsActivity.this, ":c" , Toast.LENGTH_SHORT).show();
            }
        });
    }


}