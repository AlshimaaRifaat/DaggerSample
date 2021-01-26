package com.example.daggermvp.modules.home.details;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.daggermvp.R;
import com.example.daggermvp.base.BaseActivity;
import com.example.daggermvp.helper.ImageHandler;
import com.example.daggermvp.mvp.model.Country;

import butterknife.BindView;

public class DetailActivity extends BaseActivity {
    public static final String Country = "country";
    @BindView(R.id.cakeImage) protected ImageView mCakeImage;
    @BindView(R.id.cakeTitle) protected TextView mCakeTitle;
    @BindView(R.id.population) protected TextView mCakeDescription;

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mCakeImage.setTransitionName("countryImageAnimation");
        }



       Country country = (Country) intent.getSerializableExtra(Country);
        setTitle("Country Detail");

        mCakeTitle.setText(country.getName());
        mCakeDescription.setText("Population: "+String.valueOf(country.getPopulation()));

        Glide.with(this).load(country.getFlag())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(new ImageHandler(mCakeImage));
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_detail;
    }
}