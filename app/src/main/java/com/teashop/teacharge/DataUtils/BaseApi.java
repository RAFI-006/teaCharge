package com.teashop.teacharge.DataUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseApi {

    private static Retrofit retrofit=null;
    //public static final String BASE_URL ="http://babusofttech.in/teacharge/";
    public static final String BASE_URL="http://10.0.3.2/teacharge/api/";


    public static Retrofit getClient() {

        OkHttpClient client = new OkHttpClient();
        client.connectTimeoutMillis();
        client.readTimeoutMillis();
        if (retrofit==null) {

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofit;
    }

}
