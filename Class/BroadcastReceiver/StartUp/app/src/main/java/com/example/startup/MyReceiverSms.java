package com.example.startup;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MyReceiverSms extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
//        throw new UnsupportedOperationException("Not yet implemented");

        int ii = 1;
        ii++;
        if (intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")){
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                Object[] pdus = (Object[])bundle.get("pdus");
                final SmsMessage[] messages = new SmsMessage[pdus.length];
                for (int i = 0; i < pdus.length; i++) {
                    messages[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
                }

                try {
                    FileOutputStream fos = context.openFileOutput("history_sms.txt", Context.MODE_APPEND);
                    OutputStreamWriter writer = new OutputStreamWriter(fos);
                    Calendar calendar = Calendar.getInstance();
                    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
                    String date = format.format(calendar.getTime());

                    writer.write(date+" : "+messages[0].getMessageBody()+" \n ");
                    writer.close();
                    fos.close();
                }catch (Exception e){
                    Log.d("error",e.getMessage());
                }

                if (messages.length > -1) {
                    Log.i("SMS receive", "Message recieved: " + messages[0].getMessageBody());
                }
            }
        }
    }
}
