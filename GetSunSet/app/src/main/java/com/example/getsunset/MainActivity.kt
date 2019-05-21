package com.example.getsunset

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.util.zip.CheckedInputStream

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    protected fun getData(view: View) {
        var city = etCityName.text.toString()
        val url = "https://api.apixu.com/v1/current.json?key=e4c0d85a143640c19b2120734191104&q=" + city
        MyAsyncTask().execute(url)
    }
    inner class MyAsyncTask:AsyncTask<String, String, String>(){


        override fun onPreExecute() {
            //TODO: before task started
            //super.onPreExecute()
        }
        override fun doInBackground(vararg params: String?): String {
            //TODO; http call
            try{
                val url = URL(params[0])
                val urlConnect = url.openConnection() as HttpURLConnection
                urlConnect.connectTimeout = 7000
                var inString = ConvertStreamToString(urlConnect.inputStream)
                //cannot access to ui
                publishProgress(inString)
            }catch (ex:Exception){

            }
            return " "
        }

        override fun onProgressUpdate(vararg values: String?) {
            try{
                var json = JSONObject(values[0])
                var location = json.getJSONObject("location")
                var current = json.getJSONObject("current")
                var tempC = current.getString("temp_c")
                var wind = current.getString("wind_kph")
                tvData.text = "Temparature is "+tempC+"; wind speed is "+wind
            }catch(ex:Exception){

            }
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
        }
    }
    fun ConvertStreamToString(inputStream: InputStream):String{
        val bufferReader = BufferedReader(InputStreamReader(inputStream))
        var line:String
        var allString:String=""

        try{
            do{
                line=bufferReader.readLine()
                if (line!=null){
                    allString+=line;
                }
            } while (line!=null)
            inputStream.close()
        }catch(ex:Exception){

        }

        return allString
    }
}