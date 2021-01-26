package com.example.daggermvp.mapper;

import com.example.daggermvp.mvp.model.Country;
import com.example.daggermvp.mvp.model.CountriesResponse;
import com.example.daggermvp.mvp.model.Storage;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class CountryMapper {

    @Inject
    public CountryMapper() {
    }

    public List<Country> mapCountries(Storage storage, List<CountriesResponse> countryList) {
        List<Country> countryArrayList = new ArrayList<>();

        if (countryList != null) {
                for (CountriesResponse country : countryList) {
                    Country mCountry = new Country();
                    mCountry.setName(country.getName());
                    mCountry.setCapital(country.getCapital());
                    mCountry.setFlag(country.getFlag());
                    mCountry.setPopulation(country.getPopulation());
                   storage.addCountry(mCountry);
                    countryArrayList.add(mCountry);

            }
        }
        return countryArrayList;
    }
}
