package com.gh0stcr4ck3r.besafe.network;

import com.gh0stcr4ck3r.besafe.utils.BaseConstants;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author : Raisul Islam
 * @date : 02-Apr-2020 3:56 PM
 * -:=Think=:--:=Write=:--:=Innovate=:-
 * Copyright (C) 2020 - All Rights Reserved
 **/
public class RetrofitInstance {
    private static Retrofit retrofit;
    public static ApiEndpoint getApiInterface() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BaseConstants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            return retrofit.create(ApiEndpoint.class);
        }
        return retrofit.create(ApiEndpoint.class);
    }

}
