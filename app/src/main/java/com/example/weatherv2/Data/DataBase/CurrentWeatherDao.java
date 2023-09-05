package com.example.weatherv2.Data.DataBase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface CurrentWeatherDao {

    String CURRENT_WEATHER_TABLE_NAME = "CurrentWeather";
    @Insert
    void addCurrentWeather(CurrentWeather currentWeather);

    @Query("DELETE FROM " + CURRENT_WEATHER_TABLE_NAME)
    void deleteAll();

    @Query("select * FROM " + CURRENT_WEATHER_TABLE_NAME)
    LiveData<CurrentWeather> getCurrentWeatherFromDataBase();



}
