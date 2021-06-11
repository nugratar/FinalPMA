package com.example.finalp.Data.Api.Repository.Utils;

import com.example.finalp.Const;
import com.example.finalp.Data.Api.Service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SingleRequest {
    private static Service service;

    public static Service getInstance() {
        if(service == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Const.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            service = retrofit.create(Service.class);
        }
        return service;
    }
}
