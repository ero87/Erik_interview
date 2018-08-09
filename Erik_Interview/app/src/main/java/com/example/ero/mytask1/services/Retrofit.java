package com.example.ero.mytask1.services;

import retrofit2.converter.gson.GsonConverterFactory;

public class Retrofit {
    private static final String URL = "https://randomuser.me";
    private static retrofit2.Retrofit retrofit = null;

    public static retrofit2.Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
