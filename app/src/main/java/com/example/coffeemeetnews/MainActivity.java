package com.example.coffeemeetnews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.util.Log;

import com.example.coffeemeetnews.model.NewsResponse;
import com.example.coffeemeetnews.network.NewsApi;
import com.example.coffeemeetnews.network.RetrofitClient;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);
       navController = navHostFragment.getNavController();
       NavigationUI.setupWithNavController(navView, navController);
       NavigationUI.setupActionBarWithNavController(this, navController);

        NewsApi api = RetrofitClient.newInstance().create(NewsApi.class);
        api.getTopHeadLines("US").enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                if (response.isSuccessful()) {
                    Log.d("get new top headlines", response.body().toString());
                }
                else {
                    Log.d("getTopHeadlines", response.toString());
                }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                Log.d("getTopHeadlines", t.getMessage());
            }
        });
    }
    @Override
    public boolean onSupportNavigateUp() {
        return navController.navigateUp();
    }
}