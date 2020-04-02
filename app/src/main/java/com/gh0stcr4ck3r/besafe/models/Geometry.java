package com.gh0stcr4ck3r.besafe.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by cr4ck3r on 8/29/18.
 * Copyright (c) 2018
 */
public class Geometry {

    @SerializedName("location")
    @Expose
    private LocationModel locationModel;
   /* @SerializedName("viewport")
    @Expose
    private Viewport viewport;*/

    public LocationModel getLocationModel() {
        return locationModel;
    }

    public void setLocationModel(LocationModel locationModel) {
        this.locationModel = locationModel;
    }

   /* public Viewport getViewport() {
        return viewport;
    }

    public void setViewport(Viewport viewport) {
        this.viewport = viewport;
    }*/

}
