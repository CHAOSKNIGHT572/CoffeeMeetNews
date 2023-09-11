package com.example.coffeemeetnews.network;

import com.example.coffeemeetnews.model.NewsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApi {
    @GET("top-headlines")
    Call<NewsResponse> getTopHeadLines(@Query("country") String country);

    @GET("everything")
    Call<NewsResponse> getEverything
            (@Query("q")String query, @Query("pageSize")int pageSize);

}
