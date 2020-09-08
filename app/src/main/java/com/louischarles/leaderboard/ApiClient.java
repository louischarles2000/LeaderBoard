package com.louischarles.leaderboard;

import com.google.gson.Gson;

import retrofit2.Retrofit;

public class ApiClient {
    private static Retrofit getRetrofit(){
        Retrofit retrofit = new Retrofit.Builder().build();
        return retrofit;
    }
}
