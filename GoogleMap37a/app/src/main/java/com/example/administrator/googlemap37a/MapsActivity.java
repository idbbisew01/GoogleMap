package com.example.administrator.googlemap37a;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    double lang, lat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //lang = 23.7442781;
        //lat = 90.3721879;

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Intent i = getIntent();
        lang = i.getDoubleExtra("lang", 0);
        lat = i.getDoubleExtra("lat", 0);
        String streetAddress = "";

        try {
            final Geocoder geocoder = new Geocoder(this);
            List<Address> addressList = geocoder.getFromLocation(lat, lang, 1);
            streetAddress = addressList.get(0).getAddressLine(0);
        } catch (Exception e) {
            e.printStackTrace();
        }


        // Add a marker in Sydney and move the camera
        LatLng dhaka = new LatLng(lat, lang);
        //LatLng dhaka = new LatLng(23.7442781, 90.3721879);
        mMap.addMarker(new MarkerOptions().position(dhaka).title(streetAddress));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(dhaka, 15));
    }
}
