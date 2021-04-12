package com.teashop.teacharge.DataUtils;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseApi {

    private static Retrofit retrofit = null;
    //public static final String BASE_URL ="http://babusofttech.in/teacharge/";


    /* for local host*/
    public static final String BASE_URL = "http://api.chaiboy.xyz/api/";


    /*with Ngrock we should change every time we run */
    // public static final String BASE_URL="http://4b8a9400.ngrok.io/teacharge/api/";


    /*    public static void setFollowRedirects (boolean auto)
        public OkHttpClient setFollowProtocolRedirects(boolean followProtocolRedirects);*/
    public static Retrofit getClient() {



     //   client.interceptors().add(new RedirectInterceptor());
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @NotNull
            @Override
            public Response intercept(@NotNull Chain chain) throws IOException {
                Request original = chain.request();

                Request request = original.newBuilder()
                        .method(original.method(), original.body())
                        .build();
                return chain.proceed(request);
            }
        }).addInterceptor(interceptor).build();

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofit;
    }


    private static class RedirectInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Response response = chain.proceed(chain.request());
            if (response.code() == 307) {
                request = request.newBuilder()
                        .url(response.header("Location"))
                        .build();
                response = chain.proceed(request);

            }
            return response;
        }
    }
}


