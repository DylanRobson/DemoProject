package com.example.dylan.demoproject;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * APIService singleton object. //TODO: verify singleton style/use case.
 */
public class API {

    private static final String API_BASE_URL = "https://jsonplaceholder.typicode.com";
    private static APIService sAPI;

    public static APIService getInstance() {
        if (sAPI == null) {
            sAPI = configureAPI(API_BASE_URL);
        }
        return sAPI;
    }

    /**
     * Configure Retrofit and GSON for deserialization.
     */
    private static APIService configureAPI(String baseUrl) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(APIService.class);
    }
}
