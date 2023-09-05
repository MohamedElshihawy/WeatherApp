package com.example.weatherv2.Data.DataBase;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import java.util.List;
public class Repository {

    public WeatherDataBase dataBase ;

    public CurrentWeatherDao currentWeatherDao ;
    LiveData<CurrentWeather> currentWeatherLiveData ;

    public HourlyWeatherDao hourlyWeatherDao ;
    LiveData<List<HourlyWeather>> hourlyWeatherLiveData ;

    public DailyWeatherDao dailyWeatherDao ;
    LiveData<List<DailyWeather>> dailyWeatherLiveData ;

    public Repository(Context context)
    {
        dataBase = WeatherDataBase.getInstance(context.getApplicationContext());

        currentWeatherDao = dataBase.currentWeatherDao();
        hourlyWeatherDao = dataBase.hourlyWeatherDao();
        dailyWeatherDao = dataBase.dailyWeatherDao();

        currentWeatherLiveData = currentWeatherDao.getCurrentWeatherFromDataBase();
        hourlyWeatherLiveData = hourlyWeatherDao.getCurrentWeatherFromDataBase();
        dailyWeatherLiveData = dailyWeatherDao.getCurrentWeatherFromDataBase();

    }

    public void insertCurrentWeather(CurrentWeather currentWeather)
    {

        new insertCurrentWeatherAsyncTask(currentWeatherDao).execute(currentWeather);

    }

    public LiveData<CurrentWeather> getCurrentWeatherLiveData() {
        return currentWeatherLiveData;
    }

    public void deleteCurrentWeather()
    {

        new deleteCurrentWeatherAsyncTask(currentWeatherDao).execute();

    }







    public void insertHourlyWeather(List<HourlyWeather> hourlyWeathers)
    {
        new insertHourlyWeatherAsyncTask(hourlyWeatherDao).execute(hourlyWeathers);

    }

    public LiveData<List<HourlyWeather>> getHourlyWeatherLiveData() {
        return hourlyWeatherLiveData;
    }

    public void deleteHourlyWeather()
    {

        new deleteHourlyWeatherAsyncTask(hourlyWeatherDao).execute();

    }




    public void insertDailyWeather(List<DailyWeather> dailyWeathers)
    {
        new insertDailyWeatherAsyncTask(dailyWeatherDao).execute(dailyWeathers);
    }


    public LiveData<List<DailyWeather>> getDailyWeatherLiveData() {
        return dailyWeatherLiveData;
    }

    public void deleteDailyWeather()
    {

        new deleteDailyWeatherAsyncTask(dailyWeatherDao).execute();

    }


    private static class insertCurrentWeatherAsyncTask extends AsyncTask<CurrentWeather , Void ,Void > {

        CurrentWeatherDao currentWeatherDao;

        private insertCurrentWeatherAsyncTask( CurrentWeatherDao currentWeatherDao)
        {
            this.currentWeatherDao = currentWeatherDao;
        }

        @Override
        protected Void doInBackground(CurrentWeather... currentWeathers) {
            currentWeatherDao.addCurrentWeather(currentWeathers[0]);
            return null;
        }
    }

    private static class deleteCurrentWeatherAsyncTask extends AsyncTask<Void ,Void ,Void> {

        CurrentWeatherDao currentWeatherDao;

        private deleteCurrentWeatherAsyncTask(CurrentWeatherDao currentWeatherDao) {
            this.currentWeatherDao = currentWeatherDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            currentWeatherDao.deleteAll();
            return null;
        }
    }

    private class insertHourlyWeatherAsyncTask extends AsyncTask<List <HourlyWeather>, Void ,Void> {

        HourlyWeatherDao hourlyWeatherDao;
        private insertHourlyWeatherAsyncTask(HourlyWeatherDao hourlyWeatherDao) {
            this.hourlyWeatherDao = hourlyWeatherDao;
        }


        @Override
        protected Void doInBackground(List<HourlyWeather>... lists) {
            hourlyWeatherDao.addHourlyWeather(lists[0]);
            return null;
        }
    }

    private static class deleteHourlyWeatherAsyncTask extends AsyncTask<Void , Void ,Void > {
        HourlyWeatherDao hourlyWeatherDao ;

        private deleteHourlyWeatherAsyncTask(HourlyWeatherDao hourlyWeatherDao) {
            this.hourlyWeatherDao = hourlyWeatherDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {

            hourlyWeatherDao.deleteAll();
            return null;
        }
    }

    private static class insertDailyWeatherAsyncTask extends AsyncTask<List<DailyWeather> , Void , Void > {
        DailyWeatherDao dailyWeatherDao ;
        private insertDailyWeatherAsyncTask(DailyWeatherDao dailyWeatherDao) {
            this.dailyWeatherDao = dailyWeatherDao;
        }

        @Override
        protected Void doInBackground(List<DailyWeather>... lists) {
            dailyWeatherDao.addDailyWeather(lists[0]);
            return null;
        }
    }

    private class deleteDailyWeatherAsyncTask extends AsyncTask<Void ,Void ,Void>{
        DailyWeatherDao dailyWeatherDao ;
        public deleteDailyWeatherAsyncTask(DailyWeatherDao dailyWeatherDao) {
            this.dailyWeatherDao = dailyWeatherDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dailyWeatherDao.deleteAll();
            return null;
        }
    }




}
