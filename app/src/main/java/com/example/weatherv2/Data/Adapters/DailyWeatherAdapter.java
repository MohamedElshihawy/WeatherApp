package com.example.weatherv2.Data.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherv2.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import com.example.weatherv2.Data.DataBase.DailyWeather;

public class DailyWeatherAdapter extends RecyclerView.Adapter<DailyWeatherAdapter.MyViewHolder> {

    List<DailyWeather> dailyWeathers = new ArrayList<>();


    public void setAdapter(List<DailyWeather> weatherList)
    {
        dailyWeathers = weatherList;

    }
    public  DailyWeatherAdapter (List<DailyWeather> dailyWeathers)
    {
        this.dailyWeathers = dailyWeathers;
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        ImageView weatherIcon;
        TextView dayDate , dayCondition , dayMinTemp ,dayMaxTemp ;
        View view ;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView ;
            weatherIcon = view.findViewById(R.id.daily_weather_icon);
            dayDate = view.findViewById(R.id.daily_date);
            dayCondition = view.findViewById(R.id.daily_weather_condition);
            dayMinTemp = view.findViewById(R.id.daily_min_temp);
            dayMaxTemp = view.findViewById(R.id.daily_max_temp);
        }
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.daily_list_item , parent ,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DailyWeather weather = dailyWeathers.get(position);
        Picasso.get().load(weather.getWeatherIcon()).into(holder.weatherIcon);

        holder.dayDate.setText(weather.getDayName());
        holder.dayCondition.setText(weather.getWeatherCondition());
        holder.dayMinTemp.setText(weather.getMinTemp());
        holder.dayMaxTemp.setText(weather.getMaxTemp());

    }

    @Override
    public int getItemCount() {
        if(dailyWeathers==null)
            return 0;
        return dailyWeathers.size();
    }




}
