package com.example.dogapp.viewmodel;

import com.example.dogapp.model.DogBreed;
import com.example.dogapp.model.DogsApi;

import java.util.List;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DogsApiService {
    public static final String BASE_URL = "https://raw.githubusercontent.com/";
    private DogsApi api;

    public DogsApiService() {
            api = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .build()
                    .create(DogsApi.class);
    }
    public Single<List<DogBreed>> getDogs() {
        return api.getDogs();
    }
}
