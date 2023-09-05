package com.example.weatherv2.Data.DataBase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DailyWeatherDao {

    String DAILY_WEATHER_TABLE_NAME = "DailyWeather";


    @Insert
    void addDailyWeather(List<DailyWeather> dailyWeathers);

    @Query("DELETE FROM " +DAILY_WEATHER_TABLE_NAME )
    void deleteAll();

    @Query("select * FROM " + DAILY_WEATHER_TABLE_NAME)
    LiveData<List<DailyWeather>> getCurrentWeatherFromDataBase();


}
