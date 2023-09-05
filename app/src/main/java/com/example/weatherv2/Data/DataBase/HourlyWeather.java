package com.example.weatherv2.Data.DataBase;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "HourlyWeather")
public class HourlyWeather {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String hour;
    private String temp ;
    private String windSpeed;
    private String weatherIcon;

    public HourlyWeather(String hour, String temp, String windSpeed, String weatherIcon) {
        this.hour = hour;
        this.temp = temp;
        this.windSpeed = windSpeed;
        this.weatherIcon = weatherIcon;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getWeatherIcon() {
        return weatherIcon;
    }

    public void setWeatherIcon(String weatherIcon) {
        this.weatherIcon = weatherIcon;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
