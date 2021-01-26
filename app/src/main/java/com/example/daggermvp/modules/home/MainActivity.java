package com.example.daggermvp.modules.home;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.daggermvp.R;
import com.example.daggermvp.base.BaseActivity;
import com.example.daggermvp.di.components.DaggerCountryComponent;
import com.example.daggermvp.di.module.CountryModule;
import com.example.daggermvp.modules.home.details.DetailActivity;
import com.example.daggermvp.mvp.model.Country;
import com.example.daggermvp.mvp.presenter.CountryPresenter;
import com.example.daggermvp.mvp.view.MainView;
import com.example.daggermvp.utilities.NetworkUtils;

import java.util.List;

import javax.inject.Inject;


import butterknife.BindView;

public class MainActivity extends BaseActivity implements MainView,CountryAdapter.OnCakeClickListener {
    private static final String TAG = "MainActivity";
    @BindView(R.id.cake_list) protected RecyclerView recyclerView;
    @Inject
    protected CountryPresenter mPresenter;
    private CountryAdapter countryAdapter;

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        initializeList();
        loadCountries();
    }

    private void loadCountries() {
       if(NetworkUtils.isNetAvailable(this)) {
            mPresenter.getCakes();
     } else {
            mPresenter.getCountriesFromDatabase();
        }
    }

    private void initializeList() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        countryAdapter = new CountryAdapter(getLayoutInflater());
        countryAdapter.setCakeClickListener(this::onClick);
        recyclerView.setAdapter(countryAdapter);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }



    @Override
    protected void resolveDaggerDependency() {
        DaggerCountryComponent.builder()
                .applicationComponent(getApplicationComponent())
                .countryModule(new CountryModule(this))
                .build().inject(this);
    }

    @Override
    public void onCakeLoaded(List<Country> cakes) {
        countryAdapter.addCountries(cakes);
    }

    @Override
    public void onShowDialog(String message) {
        showDialog(message);
    }

    @Override
    public void onHideDialog() {
        hideDialog();
    }

    @Override
    public void onShowToast(String message) {
        Log.d(TAG, "onShowToast: "+message);
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
    }

   @Override
    public void onClearItems() {
        countryAdapter.clearCakes();
    }


    @Override
    public void onClick(View v, Country country, int position) {
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra(DetailActivity.Country, country);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, v, "cakeImageAnimation");
            startActivity(intent, options.toBundle());
        } else {
            startActivity(intent);
        }
    }
}
