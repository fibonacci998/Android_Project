package com.example.startup;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
//        throw new UnsupportedOperationException("Not yet implemented");
        String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
        if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)){
            String phoneNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);

            try {
                FileOutputStream fos = context.openFileOutput("history.txt", Context.MODE_APPEND);
                OutputStreamWriter writer = new OutputStreamWriter(fos);
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
                String date = format.format(calendar.getTime());

                writer.write(date+" : "+phoneNumber+" \n ");
                writer.close();
                fos.close();
            }catch (Exception e){
                Log.d("error",e.getMessage());
            }
        }
    }
}
