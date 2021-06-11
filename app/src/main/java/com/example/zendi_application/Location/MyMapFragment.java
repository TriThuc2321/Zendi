package com.example.zendi_application.Location;

import android.content.Context;
import android.graphics.Camera;
import android.location.Address;
import android.location.Geocoder;

import com.example.zendi_application.searchfragment.MyDetailProduct;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

public class MyMapFragment extends SupportMapFragment implements OnMapReadyCallback {

    private GoogleMap googleMap;
    public static Context mContext;

    public MyMapFragment()  {
        getMapAsync(this);
    }

    public void SearchMap(String location){
        /*List<Address> addressList = null;

        if(location != null || location !=""){
            Geocoder geocoder = new Geocoder(mContext);
            try{
                addressList = geocoder.getFromLocationName(location,1);
            } catch (IOException e){
                e.printStackTrace();
            }
            Address address = addressList.get(0);

            LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());

            this.googleMap.addMarker(new MarkerOptions().position(latLng).title(location));
            this.googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        }*/

    }

    @Override
    public void onMapReady(final GoogleMap gmap) {
        this.googleMap = gmap;

        // Set default position
        // Add a marker in Sydney and move the camera

        LatLng vietnam = new LatLng(14.0583, 108.2772); // 14.0583° N, 108.2772° E
        this.googleMap.addMarker(new MarkerOptions().position(vietnam).title("Marker in Vietnam"));
        this.googleMap.moveCamera(CameraUpdateFactory.newLatLng(vietnam));

        this.googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLng);
                markerOptions.title(latLng.latitude + " : "+ latLng.longitude);
                // Clear previously click position.
                googleMap.clear();
                // Zoom the Marker
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
                // Add Marker on Map
                googleMap.addMarker(markerOptions);
            }
        });
    }


}
