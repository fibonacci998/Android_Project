package com.example.testbottomnavigationactivity;

import android.app.Activity;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MyLocationListener extends Thread implements LocationListener {
    Location location, oldLocation;
    Activity activity;

    @Override
    public void run() {
//        super.run();
//        while (true){
//            if (oldLocation.distanceTo(location) == 0f){
//                continue;
//            }
//            oldLocation = location;
//            activity.runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    if (mMap == null){
//
//
//                        mMap.clear();
//                        // Add a marker in Sydney and move the camera
//                        LatLng sydney = new LatLng(location.getLatitude(), location.getLongitude());
//                        mMap.addMarker(new MarkerOptions().position(sydney).title("Me").snippet(" here us my location").
//                                icon(BitmapDescriptorFactory.fromResource(R.drawable.mario)));
//                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,14f));
//
//                        try{
//                            Thread.sleep(1000);
//                        }catch (Exception e){
//
//                        }
//                    }
//                }
//            });
//        }
    }

    public MyLocationListener() {
        location=new Location("Start");
        location.setLatitude(0.0);
        location.setLongitude(0.0);

        oldLocation = new Location("Old location");
        oldLocation.setLongitude(0.0);
        oldLocation.setLatitude(0.0);
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        Log.i("onLocationChangedTuan", location.toString());
        this.location = location;
    }

    public void onLocationChanged(Location p) {
        this.setLocation(p);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }


}