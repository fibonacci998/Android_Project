package com.example.testbottomnavigationactivity;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class DashboardFragment extends Fragment implements OnMapReadyCallback {

    View mView;
    GoogleMap mGoogleMap;
    MapView mMapView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        mView = inflater.inflate(R.layout.fragment_dashboard, null);
        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mMapView = mView.findViewById(R.id.map);
        if (mMapView != null) {
            mMapView.onCreate(null);
            mMapView.onResume();
            mMapView.getMapAsync(this);
        }

        checkPermision();
    }

    void checkPermision() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ActivityCompat.checkSelfPermission(getActivity(),
                    android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 123);
                return;
            }
        }
        getUserLocation();
    }
    void getUserLocation9() {
        Toast.makeText(getActivity(), "User location access on", Toast.LENGTH_LONG).show();

        MyLocationListener myLocation = new MyLocationListener();
        LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);


        Location temp = new Location("tuan");
        temp.setLatitude(0.0);
        temp.setLongitude(0.0);
        myLocation.setLocation(temp);

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3, 3f, myLocation);

        MyThread myThread=new MyThread();

        myThread.setActivity(getActivity());
        myThread.setLocation(myLocation.getLocation());
        myThread.setmMap(mGoogleMap);

        myThread.start();
    }
    void getUserLocation() {
        Toast.makeText(getActivity(), "User location access on", Toast.LENGTH_LONG).show();

        MyLocationListener1 myLocation = new MyLocationListener1();
        LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3, 3f, myLocation);

        MyThread1 myThread=new MyThread1();
        myThread.start();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getContext());
        mGoogleMap = googleMap;
//        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
//        googleMap.addMarker(new MarkerOptions().position(new LatLng(40.689247, -74.044502)).
//                title("Status of Liberty").snippet("tuan"));
//        CameraPosition liberty = CameraPosition.builder().
//                target(new LatLng(40.689247, -74.044502)).zoom(16).bearing(0).tilt(45).build();
//        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(liberty));
    }
    Location oldLocation, location;

    class MyLocationListener1 implements LocationListener {
        public MyLocationListener1() {
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
    class MyThread1 extends Thread {

        public MyThread1() {
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
                    if (getActivity() != null) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getActivity(), location.toString(), Toast.LENGTH_SHORT).show();
                                if (mGoogleMap != null) {
                                    mGoogleMap.clear();
                                    // Add a marker in Sydney and move the camera
                                    LatLng sydney = new LatLng(location.getLatitude(), location.getLongitude());
                                    mGoogleMap.addMarker(new MarkerOptions().position(sydney).title("Me").snippet(" here us my location").
                                            icon(BitmapDescriptorFactory.fromResource(R.drawable.mario)));
                                    mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 14f));
                                    try {
                                        Thread.sleep(1000);
                                    } catch (Exception e) {

                                    }
                                }


                            }
                        });
                    }
                }catch (Exception e){

                }
            }
        }
    }
}
