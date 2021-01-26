package com.example.daggermvp.modules.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

import com.example.daggermvp.R;
import com.example.daggermvp.base.BaseActivity;
import com.example.daggermvp.di.components.DaggerCakeComponent;
import com.example.daggermvp.di.module.CakeModule;
import com.example.daggermvp.mvp.model.Cake;
import com.example.daggermvp.mvp.presenter.CakePresenter;
import com.example.daggermvp.mvp.view.MainView;
import com.example.daggermvp.utilities.NetworkUtils;

import java.util.List;

import javax.inject.Inject;


import butterknife.BindView;

public class MainActivity extends BaseActivity implements MainView {
    private static final String TAG = "MainActivity";
    @BindView(R.id.cake_list) protected RecyclerView mCakeList;
    @Inject
    protected CakePresenter mPresenter;
    private CakeAdapter mCakeAdapter;

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        initializeList();
        loadCakes();
    }

    private void loadCakes() {
       if(NetworkUtils.isNetAvailable(this)) {
            mPresenter.getCakes();
      /*  } else {
            mPresenter.getCakesFromDatabase();*/
        }
    }

    private void initializeList() {
        mCakeList.setHasFixedSize(true);
        mCakeList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mCakeAdapter = new CakeAdapter(getLayoutInflater());
       // mCakeAdapter.setCakeClickListener(mCakeClickListener);
        mCakeList.setAdapter(mCakeAdapter);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }*/

 /*   @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_reload:
                loadCakes();
                return true;
            case R.id.action_about:
                showAbout();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }*/

    /*private void showAbout() {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setMessage("Developed by Filippo Engidashet on 24/09/2016. \n\nGet the code and follow me on github!")
                .setCancelable(true)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("Get Code", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse("https://github.com/filippella/Dagger-Rx-Database-MVP"));
                        startActivity(intent);
                        dialog.dismiss();
                    }
                })
                .create();
        dialog.show();
    }
*/

    @Override
    protected void resolveDaggerDependency() {
        DaggerCakeComponent.builder()
                .applicationComponent(getApplicationComponent())
                .cakeModule(new CakeModule(this))
                .build().inject(this);
    }

    @Override
    public void onCakeLoaded(List<Cake> cakes) {
        mCakeAdapter.addCakes(cakes);
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

  /*  @Override
    public void onClearItems() {
        mCakeAdapter.clearCakes();
    }

    private CakeAdapter.OnCakeClickListener mCakeClickListener = new CakeAdapter.OnCakeClickListener() {
        @Override
        public void onClick(View v, Cake cake, int position) {
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra(DetailActivity.CAKE, cake);

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, v, "cakeImageAnimation");
                startActivity(intent, options.toBundle());
            } else {
                startActivity(intent);
            }
        }
    };*/
}
