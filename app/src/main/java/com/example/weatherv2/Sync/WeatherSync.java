package com.example.weatherv2.Sync;

import com.example.weatherv2.Activities.MainActivity;

import java.util.List;

import com.example.weatherv2.Data.DataBase.CurrentWeather;
import com.example.weatherv2.Data.DataBase.DailyWeather;
import com.example.weatherv2.Data.DataBase.HourlyWeather;
import com.example.weatherv2.WeatherUtilities.FetchFromNetwork;
import com.example.weatherv2.WeatherUtilities.GetLocation;
import com.example.weatherv2.WeatherUtilities.ParseResponseFromServer;

public class WeatherSync {



    // needs to be modified to take the real city name from the user

  //  String Final = CURRENT_AND_DAILY_TEMP.concat(Query).concat(TEMP_TYPE_QUERY).concat("&exclude=minutely").concat(API_KEY);

    public static void SunshineSyncTask(String CityName)
    {

        CurrentWeather currentWeather;
        List<HourlyWeather> hourlyWeathers;
        List<DailyWeather> dailyWeathers;

        String locationQuery = GetLocation.GET_LON_LAT_FROM_API + CityName + MainActivity.API_KEY;


        // to get lat log of a city by its name
        String queryToGetLatitudeLongitude = GetLocation.queryLatitudeLongitudeFromApi(locationQuery);

        if(queryToGetLatitudeLongitude!=null)
        {
            String locationLatitudeLongitude =  GetLocation.getLatitudeLongitudeFromApiResponse(queryToGetLatitudeLongitude);

            // create temperature query url
            String queryWeatherUrl = MainActivity.CURRENT_AND_DAILY_TEMP +
                    locationLatitudeLongitude + MainActivity.TEMP_TYPE_QUERY + MainActivity.API_KEY ;

            String weatherResponseFromServer = FetchFromNetwork.HttpRequest(queryWeatherUrl);

            if(weatherResponseFromServer!= null)
            {
                MainActivity.viewModel.deleteCurrentWeather();
                MainActivity.viewModel.deleteHourlyWeather();
                MainActivity.viewModel.deleteDailyData();
            }


            currentWeather = ParseResponseFromServer.parseCurrentData(weatherResponseFromServer);
            MainActivity.viewModel.insertCurrentWeather(currentWeather);


            hourlyWeathers = ParseResponseFromServer.parseHourlyData(weatherResponseFromServer);
            MainActivity.viewModel.insertHourlyWeather(hourlyWeathers);


            dailyWeathers = ParseResponseFromServer.parseDailyData(weatherResponseFromServer);
            MainActivity.viewModel.insertDailyWeather(dailyWeathers);

        }
        else
        {
            MainActivity.wrongCityName = false;
        }

    }

}
