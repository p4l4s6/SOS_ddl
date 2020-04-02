package com.gh0stcr4ck3r.besafe.network;

import android.database.Observable;

import com.gh0stcr4ck3r.besafe.models.NearbyPlaces;
import com.gh0stcr4ck3r.besafe.utils.BaseConstants;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author : Raisul Islam
 * @date : 02-Apr-2020 4:00 PM
 * -:=Think=:--:=Write=:--:=Innovate=:-
 * Copyright (C) 2020 - All Rights Reserved
 **/
public interface ApiEndpoint {

    @GET("nearbysearch/json?sensor=true&key=" + BaseConstants.MAPS_API_KEY)
    Call<NearbyPlaces> getNearbyPlaces(@Query("type") String type, @Query("location") String location, @Query("radius") int radius);
}
