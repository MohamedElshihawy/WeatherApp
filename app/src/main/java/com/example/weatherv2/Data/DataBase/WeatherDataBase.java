package com.example.weatherv2.Data.DataBase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {CurrentWeather.class , HourlyWeather.class ,DailyWeather.class},version = 1,exportSchema = false)
public abstract class WeatherDataBase extends RoomDatabase {

    public static WeatherDataBase dataBase ;

    public  abstract CurrentWeatherDao currentWeatherDao() ;
    public  abstract DailyWeatherDao dailyWeatherDao() ;
    public  abstract HourlyWeatherDao hourlyWeatherDao() ;

    public static synchronized WeatherDataBase getInstance(Context context)
    {
        if(dataBase==null)
        {
            dataBase = Room.databaseBuilder(context.getApplicationContext() , WeatherDataBase.class , "Weather_db")
            .fallbackToDestructiveMigration()
            .build();

        }

        return dataBase;
    }




}
