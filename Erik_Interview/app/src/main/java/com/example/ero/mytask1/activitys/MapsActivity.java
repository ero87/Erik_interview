package com.example.ero.mytask1.activitys;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.example.ero.mytask1.R;
import com.example.ero.mytask1.models.Result;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private Result result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        result = (Result) getIntent().getSerializableExtra(InfoActivity.MAP_KEY);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        final Double lat = Double.valueOf(result.getLocation().getCoordinates().getLatitude());
        final Double lng = Double.valueOf(result.getLocation().getCoordinates().getLongitude());
        LatLng user = new LatLng(lat, lng);
        googleMap.addMarker(new MarkerOptions().position(user).title("User Marker"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(user));
    }
}
