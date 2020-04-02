package com.gh0stcr4ck3r.besafe.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.gh0stcr4ck3r.besafe.R;
import com.gh0stcr4ck3r.besafe.models.NearbyPlaces;
import com.gh0stcr4ck3r.besafe.models.Result;
import com.gh0stcr4ck3r.besafe.network.ApiEndpoint;
import com.gh0stcr4ck3r.besafe.network.RetrofitInstance;
import com.gh0stcr4ck3r.besafe.services.GPSTracker;
import com.gh0stcr4ck3r.besafe.ui.adapters.PlaceAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlaceListActivity extends AppCompatActivity {

    private String placeType;
    private GPSTracker gpsTracker;
    private List<Result> resultList;
    private RecyclerView recyclerView;
    private PlaceAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_list);

        resultList = new ArrayList<>();
        Intent intent = getIntent();
        placeType = intent.getStringExtra("place_type");
        gpsTracker = new GPSTracker(PlaceListActivity.this);
        recyclerView=findViewById(R.id.place_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



    }

    private void getPlaceList() {
        if (gpsTracker.canGetLocation()) {
            Location location = gpsTracker.getLocation();
            String locationString = location.getLatitude() + "," + location.getLongitude();
            ApiEndpoint api = RetrofitInstance.getApiInterface();
            api.getNearbyPlaces(placeType, locationString, 1000).enqueue(new Callback<NearbyPlaces>() {
                @Override
                public void onResponse(Call<NearbyPlaces> call, Response<NearbyPlaces> response) {
                    if(response.isSuccessful() && response.body()!=null){
                        resultList=response.body().getResults();
                        adapter= new PlaceAdapter(resultList,PlaceListActivity.this);
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onFailure(Call<NearbyPlaces> call, Throwable t) {

                }
            });
        } else {
            Toast.makeText(getApplicationContext(), "Cannot determine your location", Toast.LENGTH_SHORT).show();
        }

    }
}
