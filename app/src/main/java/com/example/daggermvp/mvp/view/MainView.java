package com.example.daggermvp.mvp.view;

import com.example.daggermvp.mvp.model.Country;

import java.util.List;

public interface MainView extends BaseView {

    void onCakeLoaded(List<Country> cakes);

    void onShowDialog(String message);

    void onHideDialog();

    void onShowToast(String message);

    void onClearItems();
}
