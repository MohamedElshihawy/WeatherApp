package com.example.weatherv2.WeatherUtilities;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.weatherv2.Sync.WeatherSync;

public class PeriodicWorker extends Worker {

    Data inputData = getInputData();

    public PeriodicWorker(@NonNull  Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }



    @NonNull

    @Override
    public Result doWork() {
        try {
            String CityName = inputData.getString("CityName");
            WeatherSync.SunshineSyncTask(CityName);
            return Result.success();
        }
        catch (Throwable throwable)
        {
            throwable.printStackTrace();
            return Result.failure();
        }

    }
}
