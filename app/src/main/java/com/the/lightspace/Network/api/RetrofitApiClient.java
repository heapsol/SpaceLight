package com.the.lightspace.Network.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sidhu on 1/25/2018.
 */

public class RetrofitApiClient {

    public static final String BASE_URL = "https://www.googleapis.com/youtube/v3/";            //@TODO update base url
    private static RetrofitApiClient singleton = null;

    private  Retrofit retrofit = null;
    private  RetrofitNetworkInterface networkAPI;



    public    RetrofitApiClient(boolean excludeAnnotation)
    {
        if (retrofit == null)
        {

            OkHttpClient okHttpClient=buildClient();
            Gson builder = new GsonBuilder().create();
            if(excludeAnnotation)
                builder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(builder))
                    .client(okHttpClient)
                    .build();
            networkAPI = retrofit.create(RetrofitNetworkInterface.class);
        }
        //return retrofit;
    }

    public synchronized static RetrofitApiClient getInstance(boolean excludeAnnotation) {
        if(singleton == null)
            singleton = new RetrofitApiClient(excludeAnnotation);
        return singleton;
    }


    /**
     * Method to return the API interface.
     *
     * @return
     */
    public RetrofitNetworkInterface getAPI() {
        return networkAPI;
    }






    public OkHttpClient buildClient() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Response response = chain.proceed(chain.request());
                // Do anything with response here
                //if we ant to grab a specific cookie or something..
                return response;
            }
        });

        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                //this is where we will add whatever we want to our request headers.
                Request request = chain.request().newBuilder().addHeader("Accept", "application/json").build();
                return chain.proceed(request);
            }
        });

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(interceptor);

        builder.connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(interceptor);

        return builder.build();
    }

}
