package com.example.startup;

import android.Manifest;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         getPermission(Manifest.permission.READ_PHONE_STATE);
        getPermission(Manifest.permission.READ_CALL_LOG);
        IntentFilter filter = new IntentFilter();
        filter.addAction(TelephonyManager.ACTION_PHONE_STATE_CHANGED);
        MyReceiver myReceiver = new MyReceiver();
        registerReceiver(myReceiver, filter);

        getPermission(Manifest.permission.READ_SMS);
        getPermission(Manifest.permission.RECEIVE_SMS);
        IntentFilter filter1 = new IntentFilter();
        filter1.addAction("android.provider.Telephony.SMS_RECEIVED");
        MyReceiverSms myReceiverSms = new MyReceiverSms();
        registerReceiver(myReceiverSms, filter1);
    }

    boolean getPermission(String permission){
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                permission)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    permission)) {
                return true;
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{permission},
                        100);
                return true;
                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted
            return true;
        }
    }
}
