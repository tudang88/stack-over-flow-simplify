package com.androidarchitecture.stackoverflowclient.networking;

import com.androidarchitecture.stackoverflowclient.common.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class StackoverflowApiService {
    private static StackoverflowApiService INSTANCE;
    private final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private StackoverflowApiService() {
    }

    /**
     * The singleton of service
     * Use synchronized to make it thread-safe
     *
     * @return service instance
     */
    public synchronized static StackoverflowApiService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new StackoverflowApiService();
        }
        return INSTANCE;
    }

    public StackoverflowApi getService() {
        return retrofit.create(StackoverflowApi.class);
    }
}
