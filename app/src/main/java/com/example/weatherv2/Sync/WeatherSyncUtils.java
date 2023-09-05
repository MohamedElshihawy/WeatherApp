package com.example.weatherv2.Sync;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.weatherv2.Data.DataBase.Repository;

public class WeatherSyncUtils {


    static Repository repository ;
    public static boolean sInitialized = false ;
    public static void startImmediateSync(Context context , String cityName)
    {
        Intent intent =  new Intent(context.getApplicationContext() ,WeatherIntentService.class);
        intent.putExtra("CityName" , cityName);
        context.startService(intent);
    }

    public static void initialize( Context context ,String cityName)
    {
        repository = new Repository(context);
        // this means that we already have called this method at least once and that our database isn't empty
        if (sInitialized) {
            Log.i("TAG", "initialize: all classes are done");
            return;
        }

        sInitialized = true ;


            startImmediateSync(context , cityName);
        Log.i("TAG", "initialize: all classes are empty");


    }



}
