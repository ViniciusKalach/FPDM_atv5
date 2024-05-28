package com.example.facensatv5.utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class ApiClient {
    public static Retrofit retrofit;
    public static final String BASE_URL = "https://66550a3c3c1d3b60293816b7.mockapi.io/api/fatv5/"; // URL da API MockAPI.io
    public static final String VIACEP_URL = "https://viacep.com.br/";

    public static Retrofit getRetrofitInstance(String baseUrl) {
        if (retrofit == null || !retrofit.baseUrl().toString().equals(baseUrl)) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
