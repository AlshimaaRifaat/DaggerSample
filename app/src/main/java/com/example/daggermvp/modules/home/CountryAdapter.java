package com.example.daggermvp.modules.home;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.daggermvp.R;
import com.example.daggermvp.mvp.model.Country;

import java.util.ArrayList;
import java.util.List;


import butterknife.BindView;
import butterknife.ButterKnife;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.Holder> {
    private static final String TAG = "CountryAdapter";
    private LayoutInflater mLayoutInflater;
    private List<Country> mCountryList = new ArrayList<>();

    public CountryAdapter(LayoutInflater inflater) {
        mLayoutInflater = inflater;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.list_item_layout, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.bind(mCountryList.get(position));
    }

    @Override
    public int getItemCount() {
        return mCountryList.size();
    }

    public void addCountries(List<Country> countries) {

        mCountryList.addAll(countries);
        notifyDataSetChanged();
    }

    public void clearCakes() {
        mCountryList.clear();
        notifyDataSetChanged();
    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.cake_icon) protected ImageView mFlag;
        @BindView(R.id.textview_title) protected TextView mTitle;
        @BindView(R.id.textview_preview_description) protected TextView mCapital;

        private Context mContext;
        private Country mCountry;

        public Holder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mContext = itemView.getContext();
            ButterKnife.bind(this, itemView);
        }

        public void bind(Country country) {
           mCountry = country;
            mTitle.setText(country.getName());
            mCapital.setText(country.getCapital());

            Glide.with(mContext).load(country.getFlag())
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(/*new ImageHandler(*/mFlag);


        }

    @Override
        public void onClick(View v) {
            if (mCakeClickListener != null) {
                mCakeClickListener.onClick(mFlag, mCountry, getAdapterPosition());
            }
        }
    }

    public void setCakeClickListener(OnCakeClickListener listener) {
        mCakeClickListener = listener;
    }

   private OnCakeClickListener mCakeClickListener;

  public interface OnCakeClickListener {

        void onClick(View v, Country country, int position);
    }
}

