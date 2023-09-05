package com.example.weatherv2.Data.DataBase;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName = "DailyWeather")
public class DailyWeather {


    @PrimaryKey(autoGenerate = true)
    private int id;
    private String dayName;
    private String MinTemp;
    private String MaxTemp;
    private String weatherIcon;
    private String weatherCondition;

    public DailyWeather()
    {}

    public DailyWeather(String dayName, String minTemp, String maxTemp, String weatherIcon, String weatherCondition ) {
        this.dayName = dayName;
        MinTemp = minTemp;
        MaxTemp = maxTemp;
        this.weatherIcon = weatherIcon;
        this.weatherCondition = weatherCondition;
    }

    public int getId() {
        return id;
    }

    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public String getMinTemp() {
        return MinTemp;
    }

    public void setMinTemp(String minTemp) {
        MinTemp = minTemp;
    }

    public String getMaxTemp() {
        return MaxTemp;
    }

    public void setMaxTemp(String maxTemp) {
        MaxTemp = maxTemp;
    }

    public String getWeatherIcon() {
        return weatherIcon;
    }

    public void setWeatherIcon(String weatherIcon) {
        this.weatherIcon = weatherIcon;
    }

    public String getWeatherCondition() {
        return weatherCondition;
    }

    public void setWeatherCondition(String weatherCondition) {
        this.weatherCondition = weatherCondition;
    }

    public void setId(int id) {
        this.id = id;
    }
}
