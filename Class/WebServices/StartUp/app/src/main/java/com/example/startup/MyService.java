package com.example.startup;

import android.app.Activity;
import android.os.AsyncTask;

import com.google.gson.Gson;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MyService extends AsyncTask<String, Void, String> {
    private Activity activity;

    public MyService(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        MainActivity mainActivity = (MainActivity) activity;
        mainActivity.setMessage(s);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected String doInBackground(String... strings) {
        String url = strings[0];
        HttpClient client = new DefaultHttpClient();
//        HttpGet httpGet = new HttpGet(url);
        HttpPost httpPost = new HttpPost(url);
        List<NameValuePair> list = new ArrayList<>();
        list.add(new BasicNameValuePair("id",strings[1]));
        try {
            HttpEntity entity = new UrlEncodedFormEntity(list);
            httpPost.setEntity(entity);
        }catch (Exception e){
            e.printStackTrace();
        }
        try{
            HttpResponse httpResponse = client.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            BufferedReader reader = new BufferedReader(new InputStreamReader(httpEntity.getContent()));
            StringBuilder sb= new StringBuilder();
            String line = null;
            while ((line=reader.readLine())!=null){
                sb.append(line);
            }
            return sb.toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }
}
