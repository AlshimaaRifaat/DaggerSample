package com.example.daggermvp.mvp.presenter;

import com.example.daggermvp.api.CountryApiService;
import com.example.daggermvp.base.BasePresenter;
import com.example.daggermvp.mapper.CountryMapper;
import com.example.daggermvp.mvp.model.CountriesResponse;
import com.example.daggermvp.mvp.model.Country;
import com.example.daggermvp.mvp.model.Storage;
import com.example.daggermvp.mvp.view.MainView;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Observer;


public class CountryPresenter extends BasePresenter<MainView> implements Observer<List<CountriesResponse>> {

    @Inject
    protected CountryApiService mApiService;
    @Inject protected CountryMapper mCakeMapper;
    @Inject protected Storage mStorage;

    @Inject
    public CountryPresenter() {
    }

    public void getCakes() {
        getView().onShowDialog("Loading countries....");
        Observable<List<CountriesResponse>> cakesResponseObservable = mApiService.getCountries();
        subscribe(cakesResponseObservable, this);
    }

    @Override
    public void onCompleted() {
        getView().onHideDialog();
        getView().onShowToast("Countries loading complete!");
    }

    @Override
    public void onError(Throwable e) {
        getView().onHideDialog();
        getView().onShowToast("Error loading countries " + e.getMessage());
    }

    @Override
    public void onNext(List<CountriesResponse> countriesResponses) {
        List<Country> cakes = mCakeMapper.mapCountries(mStorage, countriesResponses);
         getView().onClearItems();
        getView().onCakeLoaded(cakes);
    }

    public void getCountriesFromDatabase() {
        List<Country> cakes = mStorage.getSavedCountries();
        getView().onClearItems();
        getView().onCakeLoaded(cakes);
    }
}
