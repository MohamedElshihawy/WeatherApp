package com.example.weatherv2.Data.DataBase;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ViewModel extends AndroidViewModel {

    Repository repository;
    LiveData<CurrentWeather> currentWeatherLiveData;
    LiveData<List<HourlyWeather>> listOfHourlyWeatherLiveData;
    LiveData<List<DailyWeather>> listOfDailyWeatherLiveData ;
    public ViewModel(@NonNull Application application) {
        super(application);

        repository = new Repository(application);
        currentWeatherLiveData = repository.currentWeatherLiveData;
        listOfHourlyWeatherLiveData = repository.getHourlyWeatherLiveData();
        listOfDailyWeatherLiveData = repository.getDailyWeatherLiveData();

    }

    public void insertCurrentWeather(CurrentWeather currentWeather)
    {
        repository.insertCurrentWeather(currentWeather);
    }

    public void deleteCurrentWeather()
    {
        repository.deleteCurrentWeather();
    }

    public LiveData<CurrentWeather> getCurrentWeather()
    {
        if(currentWeatherLiveData==null)
        {
            currentWeatherLiveData = repository.getCurrentWeatherLiveData();

        }

        return currentWeatherLiveData;
    }


    public void insertHourlyWeather(List<HourlyWeather> hourlyWeathers)
    {
        repository.insertHourlyWeather(hourlyWeathers);

    }

    public void deleteHourlyWeather()
    {
        repository.deleteHourlyWeather();
    }


    public LiveData<List<HourlyWeather>> getListOfHourlyWeatherLiveData()
    {
        if (listOfHourlyWeatherLiveData==null){
            listOfHourlyWeatherLiveData = repository.getHourlyWeatherLiveData();
        }

        return listOfHourlyWeatherLiveData;
    }

    public void insertDailyWeather(List<DailyWeather> dailyWeathers)
    {
        repository.insertDailyWeather(dailyWeathers);

    }

    public void deleteDailyData()
    {
        repository.deleteDailyWeather();

    }

    public LiveData<List<DailyWeather>> getListOfDailyWeatherLiveData ()
    {
        if(listOfDailyWeatherLiveData == null)
        {
            listOfDailyWeatherLiveData = repository.dailyWeatherLiveData;

        }
        return listOfDailyWeatherLiveData;

    }





}
