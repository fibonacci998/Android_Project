package com.example.testbottomnavigationactivity;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MyThread extends Thread {
    Location location, oldLocation;
    Activity activity;
    GoogleMap mMap;
    public MyThread() {
        oldLocation= new Location("Start");
        oldLocation.setLatitude(0.0);
        oldLocation.setLongitude(0.0);
    }

    public GoogleMap getmMap() {
        return mMap;
    }

    public void setmMap(GoogleMap mMap) {
        this.mMap = mMap;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Location getOldLocation() {
        return oldLocation;
    }

    public void setOldLocation(Location oldLocation) {
        this.oldLocation = oldLocation;
    }

    @Override
    public void run() {
        while (true){
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        while(true){
                            if (oldLocation.distanceTo(location) == 0f){
                                continue;
                            }
                            oldLocation = location;
                            if (mMap != null) {
                                Toast.makeText(getActivity(), location.toString(), Toast.LENGTH_SHORT).show();
                                mMap.clear();
                                // Add a marker in Sydney and move the camera
                                LatLng sydney = new LatLng(location.getLatitude(), location.getLongitude());
                                mMap.addMarker(new MarkerOptions().position(sydney).title("Me").snippet(" here us my location").
                                        icon(BitmapDescriptorFactory.fromResource(R.drawable.mario)));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 14f));

                                try {
                                    Thread.sleep(1000);
                                } catch (Exception e) {

                                }
                            }
                        }

                    }
                });
        }
    }
}