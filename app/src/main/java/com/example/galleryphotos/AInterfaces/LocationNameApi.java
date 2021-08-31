package com.example.galleryphotos.AInterfaces;



import com.example.galleryphotos.AModels.Location;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface LocationNameApi {
    @GET("mor-sac/{place}")
    public Call<Location> find(@Path("place") String place);
}
