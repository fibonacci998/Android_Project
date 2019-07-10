package com.example.tuans.pockemonandroid

import android.Manifest
import android.content.Context
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AlertDialog
import android.widget.Toast

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.lang.Exception

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        checkPermision()

        loadPockemons()
        //requestLocationPermission();
    }

    var ACCESS_LOCATION=123

    fun requestLocationPermission(){
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
            AlertDialog.Builder(this)
                .setTitle("Permission needed")
                .setMessage("This perrsion is needed because this and that")
                .setPositiveButton(
                    "ok"
                ) { dialog, which ->
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                        ACCESS_LOCATION
                    )
                }
                .setNegativeButton("cancel") { dialog, which -> dialog.dismiss() }.create().show()
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                ACCESS_LOCATION
            )
        }
    }

    fun checkPermision(){
        if (Build.VERSION.SDK_INT>=23){
            if (ActivityCompat.checkSelfPermission(this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED){
                requestPermissions(arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),ACCESS_LOCATION)
                return
            }
        }
        getUserLocation()
    }
    fun getUserLocation(){
        Toast.makeText(this,"User location access on",Toast.LENGTH_LONG).show()
        //TODO: Will implement later

        var myLocation = MyLocationListener()

        var locationManager=getSystemService(Context.LOCATION_SERVICE) as LocationManager

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3, 3f, myLocation)

        Toast.makeText(this,"get address success",Toast.LENGTH_LONG).show()

        var myThread=myThread()
        myThread.start()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode){
            ACCESS_LOCATION->{
                if (grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    getUserLocation()
                }else{
                    Toast.makeText(this,"We cannot access your location",Toast.LENGTH_LONG).show()
                }
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
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
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
    }

    var location:Location?=null
    //get user location
    inner class MyLocationListener:LocationListener{


        constructor(){
            location=Location("Start");
            location!!.latitude=0.0;
            location!!.longitude=0.0;
        }
        override fun onLocationChanged(p: Location?) {

            location=p
        }

        override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onProviderEnabled(provider: String?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onProviderDisabled(provider: String?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

    }
    var oldLocation:Location?=null
    inner class myThread:Thread{
        constructor():super(){
            oldLocation=Location("Start");
            oldLocation!!.latitude=0.0;
            oldLocation!!.longitude=0.0;
        }

        override fun run() {
            while(true){
                try{
                    if (oldLocation!!.distanceTo(location)===0f){
                        continue
                    }
                    oldLocation=location
                    runOnUiThread{
                        mMap.clear()
                        // Add a marker in Sydney and move the camera
                        val sydney = LatLng(location!!.latitude, location!!.longitude)
                        mMap.addMarker(MarkerOptions().position(sydney).title("Me").snippet(" here us my location").
                            icon(BitmapDescriptorFactory.fromResource(R.drawable.mario)))
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,14f))
                        for (i in 0..listPockemon.size-1){
                            var newPockemon=listPockemon.get(i)
                            if (newPockemon.isCatch==false){
                                val pokemonLoc = LatLng(newPockemon.location!!.latitude, newPockemon.location!!.longitude)
                                mMap.addMarker(MarkerOptions().position(pokemonLoc).title(newPockemon.name)
                                    .snippet(newPockemon.des+", power:"+newPockemon.power).
                                    icon(BitmapDescriptorFactory.fromResource(newPockemon.image!!)))
                                if (location!!.distanceTo(newPockemon.location)<2){

                                    newPockemon.isCatch=true
                                    listPockemon[i]=newPockemon
                                    playerPower+=newPockemon.power!!
                                    Toast.makeText(applicationContext,
                                        "You catch new pockemin, your new power is "+playerPower,
                                        Toast.LENGTH_LONG).show()
                                }
                            }
                        }
                    }
                    Thread.sleep(1000)
                }catch (ex:Exception){

                }
            }
        }
    }
    var playerPower=0.0
    var listPockemon=ArrayList<Pockemon>()

    fun loadPockemons(){
        listPockemon.add(
            Pockemon(R.drawable.charmander,"Charmander","Here is from japan",
            55.0,37.7789994893035,-122.401846647263)
        )
        listPockemon.add(Pockemon(R.drawable.bulbasaur,"Bulbasaur","Here is from usa",
            90.5,37.7949568502667,-122.410494089127))
        listPockemon.add(Pockemon(R.drawable.squirtle,"Squirtle","Here is from irag",
            33.5,37.7816621152613,-122.41225361824))
    }
}
