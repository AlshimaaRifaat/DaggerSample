package com.example.daggermvp.api;

import com.example.daggermvp.mvp.model.CountriesResponse;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

public interface CountryApiService {

    @GET("/rest/v2/all")
    Observable<List<CountriesResponse>> getCountries();

  /*  @GET("/filippella/a728a34822a3bc7add98e477a4057b69/raw/310d712e87941f569074a63fedb675d2b611342a/cakes")
    Call<CakesResponse> getTheCakes();*/
}
