package cappic.presenter;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import cappic.view.MainActivity;
import cappic.view.R;
import cappic.view.fragment_map;

public class fragmentMapController {
    fragment_map fragment;
    Location location, oldLocation;

    public fragmentMapController() {
    }

    public fragmentMapController(fragment_map fragment) {
        this.fragment = fragment;
    }

    public void checkPermision() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ActivityCompat.checkSelfPermission(fragment.getActivity(),
                    android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                fragment.requestPermissions(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 123);
                return;
            }
        }
        getUserLocation();
    }

    void getUserLocation() {
        Toast.makeText(fragment.getActivity(), "User location access on", Toast.LENGTH_LONG).show();

        MyLocationListener myLocation = new MyLocationListener();
        LocationManager locationManager = (LocationManager) fragment.getActivity().getSystemService(Context.LOCATION_SERVICE);

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3, 3f, myLocation);

        MyThread myThread=new MyThread();
        myThread.start();
    }

    class MyLocationListener implements LocationListener {
        public MyLocationListener() {
            location=new Location("Start");
            location.setLatitude(0.0);
            location.setLongitude(0.0);
        }

        public void onLocationChanged(Location p) {
            Log.i("onLocationChangedTuan", location.toString());
            location = p;
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
    class MyThread extends Thread {

        public MyThread() {
            super();
            oldLocation= new Location("Start");
            oldLocation.setLatitude(0.0);
            oldLocation.setLongitude(0.0);
        }

        @Override
        public void run() {
            while (true) {
                try {
                    if (oldLocation.distanceTo(location) == 0f) {
                        continue;
                    }
                    oldLocation = location;
                    if (fragment.getActivity() != null) {
                        fragment.getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(fragment.getActivity(), location.toString(), Toast.LENGTH_SHORT).show();
                                if (fragment.getmGoogleMap() != null) {
                                    fragment.getmGoogleMap().clear();
                                    // Add a marker in Sydney and move the camera
                                    LatLng sydney = new LatLng(location.getLatitude(), location.getLongitude());
                                    fragment.getmGoogleMap().addMarker(new MarkerOptions().position(sydney).title("Me").snippet(" here us my location").
                                            icon(BitmapDescriptorFactory.fromResource(R.drawable.mario)));
                                    fragment.getmGoogleMap().moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 14f));
                                    try {
                                        Thread.sleep(1000);
                                    } catch (Exception e) {

                                    }
                                }


                            }
                        });
                    }
                }catch (Exception e){
                    Log.d("Error","line 122 fragment controller");
                }
            }
        }
    }

    public void setFragment(fragment_map fragment) {
        this.fragment = fragment;
    }
}
