package com.example.weatherv2.Data.DataBase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface HourlyWeatherDao {

     String HOURLY_WEATHER_TABLE_NAME = "HourlyWeather";

    @Insert
    void addHourlyWeather(List<HourlyWeather> hourlyWeathers);

    @Query("DELETE FROM " + HOURLY_WEATHER_TABLE_NAME)
    void deleteAll();

    @Query("select * FROM " + HOURLY_WEATHER_TABLE_NAME)
    LiveData<List<HourlyWeather>> getCurrentWeatherFromDataBase();

}
