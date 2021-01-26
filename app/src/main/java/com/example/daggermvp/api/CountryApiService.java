package com.example.daggermvp.api;

import com.example.daggermvp.mvp.model.CountriesResponse;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

public interface CountryApiService {

    @GET("/rest/v2/all")
    Observable<List<CountriesResponse>> getCountries();


}
