package com.example.weatherv2.WeatherUtilities;

import static android.content.ContentValues.TAG;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import com.example.weatherv2.Data.DataBase.CurrentWeather;
import com.example.weatherv2.Data.DataBase.DailyWeather;
import com.example.weatherv2.Data.DataBase.HourlyWeather;

public class ParseResponseFromServer {

    public final static String WEATHER_CONDITION_IMAGE = "https://openweathermap.org/img/wn/";
    public static CurrentWeather parseCurrentData(String Response) {
        CurrentWeather currentWeather = null;
        try {
            JSONObject object = new JSONObject(Response);

            JSONObject currentWeatherData = object.getJSONObject("current");
            String unixTime= currentWeatherData.getString("dt");
            String dayName =TransformUnixTime.getTodayDate(unixTime);
            String temp = currentWeatherData.getString("temp");
            String currentZone = (String) object.get("timezone");
            String feelsLike = currentWeatherData.getString("feels_like");
            String pressure = currentWeatherData.getString("pressure");
            String humidity = currentWeatherData.getString("humidity");
            String visibility = currentWeatherData.getString("visibility");
            String windSpeed = currentWeatherData.getString("wind_speed");
            JSONArray weatherArray= currentWeatherData.getJSONArray("weather");
            JSONObject weatherItem = weatherArray.getJSONObject(0);
            String weatherDescription = weatherItem.getString("description");
            String weatherIcon = WEATHER_CONDITION_IMAGE +  weatherItem.getString("icon")+".png";

            currentWeather = new CurrentWeather(dayName ,temp ,weatherDescription ,feelsLike ,pressure ,humidity , visibility, windSpeed , currentZone ,weatherIcon);
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("Parsing Current Data", "parseCurrentData: " , e);
        }

        assert currentWeather != null;
        Log.i("TAG", "parseCurrentData:  " + currentWeather.getFeelsLike());
        return currentWeather ;
    }

    public static List<HourlyWeather> parseHourlyData(String Response) {

        List<HourlyWeather> hourlyWeathers = new ArrayList<>();
        try {
            JSONObject object = new JSONObject(Response);

            JSONArray array = object.getJSONArray("hourly");
            for (int i =0 ; i<array.length();i++) {
                JSONObject currentWeatherData = array.getJSONObject(i);
                String unixTime = currentWeatherData.getString("dt");
                String hour = TransformUnixTime.getHourOfDay(Long.parseLong(unixTime));
                String temp = currentWeatherData.getString("temp");
                String windSpeed = currentWeatherData.getString("wind_speed");
                JSONArray weatherArray = currentWeatherData.getJSONArray("weather");
                JSONObject weatherItem = weatherArray.getJSONObject(0);
                String weatherIcon = WEATHER_CONDITION_IMAGE + weatherItem.getString("icon")+".png";
                hourlyWeathers.add(new HourlyWeather(hour ,temp ,windSpeed , weatherIcon));
            }

        }catch (Exception  e)
        {
            e.printStackTrace();
        }
        Log.i("TAG", "parseHourlyData: " + hourlyWeathers.get(0).getWindSpeed() + " wind speeeeeeed");
        return hourlyWeathers;
    }

    public static List<DailyWeather> parseDailyData(String Response) {

        List<DailyWeather> dailyWeathers =new ArrayList<>() ;

        try {
            JSONObject object = new JSONObject(Response);
            JSONArray array = object.getJSONArray( "daily");
            for (int i =0 ; i<array.length();i++) {
                JSONObject currentWeatherData = array.getJSONObject(i);
                String unixTime = currentWeatherData.getString("dt");
                String dayName = TransformUnixTime.getDayName(Long.parseLong(unixTime));
                JSONObject tempValues = currentWeatherData.getJSONObject("temp");
                String minTemp = tempValues.getString("min");
                String maxTemp = tempValues.getString("max");
                JSONArray weatherArray = currentWeatherData.getJSONArray("weather");
                JSONObject weatherItem = weatherArray.getJSONObject(0);
                String weatherIcon = WEATHER_CONDITION_IMAGE + weatherItem.getString("icon")+".png";
                String weatherCondition = weatherItem.getString("main");

                dailyWeathers.add(new DailyWeather(dayName ,minTemp ,maxTemp ,weatherIcon ,weatherCondition));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("Parsing daily data", "parseDailyData: failed",e );
        }
        Log.i(TAG, "parseDailyData: " + dailyWeathers.get(0).getDayName() + "daaaaaay");
        return dailyWeathers;
    }

}
