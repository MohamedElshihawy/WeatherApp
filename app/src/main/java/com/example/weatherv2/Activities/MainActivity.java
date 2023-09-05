package com.example.weatherv2.Activities;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import com.example.weatherv2.Data.Adapters.DailyWeatherAdapter;
import com.example.weatherv2.Data.Adapters.HourlyWeatherAdapter;
import com.example.weatherv2.R;
import com.squareup.picasso.Picasso;

import java.text.MessageFormat;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.example.weatherv2.Data.DataBase.CurrentWeather;
import com.example.weatherv2.Data.DataBase.DailyWeather;
import com.example.weatherv2.Data.DataBase.HourlyWeather;
import com.example.weatherv2.Data.DataBase.ViewModel;
import com.example.weatherv2.Sync.WeatherSyncUtils;
import com.example.weatherv2.WeatherUtilities.PeriodicWorker;
import com.example.weatherv2.WeatherUtilities.WeatherNotification;

public class MainActivity extends AppCompatActivity {

    public final static String API_KEY = "&appid=5d7205b155fc566c640b11a3cbd02355";
    public final static String TEMP_TYPE_QUERY = "&units=metric";
    public final static String CURRENT_AND_DAILY_TEMP = "https://api.openweathermap.org/data/2.5/onecall?";
    private static final String TAG = "MainActivity";
    public static ViewModel viewModel;
    public static CurrentWeather currentWeatherForecast;
    public static String imageUrl;
    public static long DaysElapsed;
    public static boolean wrongCityName = false;
    public Data data;
    String mCityName;
    List<HourlyWeather> hourlyWeatherForecast;
    List<DailyWeather> dailyWeatherForecast;
    ImageView weatherIcon;
    TextView currentCondition, currentDate, currentMinTemp, currentMaxTemp;
    EditText getCityName;
    ImageButton location, search;
    ImageView emptyView;
    RecyclerView hourlyRecyclerView, dailyRecyclerView;
    HourlyWeatherAdapter hourlyWeatherAdapter;
    DailyWeatherAdapter dailyWeatherAdapter;
    LinearLayoutManager verticalLayout, horizontalLayout;


    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (WeatherNotification.currentTime != 0) {
            outState.putLong("elapsedTime", WeatherNotification.currentTime);
        }
        if (mCityName != null) {
            outState.putString("CityName", mCityName);
        }
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);


            DaysElapsed = savedInstanceState.getLong("elapsedTime");
            mCityName = savedInstanceState.getString("CityName");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        identifyViews(this);

        if (mCityName != null) {
            WeatherSyncUtils.initialize(this, mCityName);
        }

        // populate current weather if not null
        //populateCurrentWeather();

        search.setOnClickListener(view -> {
            mCityName = getCityName.getText().toString().trim();
            startTasksInBackground(MainActivity.this, mCityName);
            if (wrongCityName) {
                getCityName.setError("Please Enter a Valid City Name");
                hourlyRecyclerView.setVisibility(View.GONE);
                dailyRecyclerView.setVisibility(View.GONE);
                emptyView.setVisibility(View.VISIBLE);
            }
            else
            {
                hourlyRecyclerView.setVisibility(View.VISIBLE);
                dailyRecyclerView.setVisibility(View.VISIBLE);
                emptyView.setVisibility(View.GONE);
            }

        });

        data = new Data.Builder()
                .putString("CityName", mCityName)
                .build();
        schedulePeriodicWork(data);


        viewModel = new ViewModelProvider(this).get(ViewModel.class);
        viewModel.getCurrentWeather().observe(this, currentWeather ->
        {
            currentWeatherForecast = currentWeather;
            if(currentWeatherForecast!=null)
            {
                populateCurrentWeather();
            }
        });

        viewModel.getListOfHourlyWeatherLiveData().observe(this, hourlyWeathers -> {
            hourlyWeatherForecast = hourlyWeathers;
            hourlyWeatherAdapter.setAdapter(hourlyWeathers);
            Log.i("TAG", "onChanged: " + "Yeap changed hourly yyyyyyyyyy");

        });

        viewModel.getListOfDailyWeatherLiveData().observe(this, dailyWeathers -> {
            dailyWeatherForecast = dailyWeathers;
            dailyWeatherAdapter.setAdapter(dailyWeathers);
            Log.i("TAG", "onChanged: " + "Yeap changed daily llllllllllllllll");
        });


    }

    public void startTasksInBackground(Context context, String cityName) {
        WeatherSyncUtils.initialize(context, cityName);
    }


    public void schedulePeriodicWork(Data data) {
        WorkManager workManager = WorkManager.getInstance();

        Constraints constraints = new Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED)
                .setRequiresCharging(false)
                .build();

        PeriodicWorkRequest periodicWorker = new PeriodicWorkRequest.Builder(PeriodicWorker.class
                , 1, TimeUnit.HOURS, 30, TimeUnit.MINUTES)
                .setConstraints(constraints)
                .setInputData(data)
                .build();
        workManager.enqueue(periodicWorker);
    }

    public void identifyViews(Context context) {
        weatherIcon = findViewById(R.id.currentDayWeatherIcon);
        currentDate = findViewById(R.id.currentDayDate);
        currentCondition = findViewById(R.id.currentDayWeatherCondition);
        currentMinTemp = findViewById(R.id.currentDayMinTemp);
        currentMaxTemp = findViewById(R.id.currentDayMaxTemp);
        getCityName = findViewById(R.id.inputCityName);
        search = findViewById(R.id.searchIcon);
        hourlyRecyclerView = findViewById(R.id.hourly_recyclerView);
        dailyRecyclerView = findViewById(R.id.daily_recyclerView);
        hourlyWeatherAdapter = new HourlyWeatherAdapter(context ,hourlyWeatherForecast);
        dailyWeatherAdapter = new DailyWeatherAdapter(dailyWeatherForecast);
        verticalLayout = new LinearLayoutManager(this);
        horizontalLayout = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        hourlyRecyclerView.setAdapter(hourlyWeatherAdapter);
        hourlyRecyclerView.setLayoutManager(horizontalLayout);
        dailyRecyclerView.setAdapter(dailyWeatherAdapter);
        dailyRecyclerView.setLayoutManager(verticalLayout);
        emptyView = findViewById(R.id.no_data_found);
    }

    public void populateCurrentWeather() {
        if (currentWeatherForecast != null) {
            Picasso.get().load(currentWeatherForecast.getWeatherIcon()).into(weatherIcon);

            currentDate.setText(MessageFormat.format("Today , {0}", currentWeatherForecast.getUnixTime()));
            currentCondition.setText(currentWeatherForecast.getWeatherDescription());
            currentMinTemp.setText(currentWeatherForecast.getTemp());
            currentMaxTemp.setVisibility(View.GONE);

        } else {
            Log.i(TAG, "Errrrrror here : currentforecast is empty ");
        }

    }


}