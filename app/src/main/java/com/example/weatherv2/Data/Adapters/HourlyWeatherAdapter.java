package com.example.weatherv2.Data.Adapters;

import android.content.Context;
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

import com.example.weatherv2.Data.DataBase.HourlyWeather;

public class HourlyWeatherAdapter extends RecyclerView.Adapter<HourlyWeatherAdapter.myViewHolder> {

    List<HourlyWeather> weatherList = new ArrayList<>();
    Context context;



    public HourlyWeatherAdapter(Context context , List<HourlyWeather> hourlyWeathers )
    {
        this.context = context;
        weatherList = hourlyWeathers;

    }

    public void setAdapter( List<HourlyWeather> hourlyWeathers)
    {
        weatherList = hourlyWeathers;
    }


    public static class myViewHolder extends RecyclerView.ViewHolder
    {

        TextView time , temp , windSpeed ;
        ImageView imageView ;
        View view;
        public myViewHolder(@NonNull View itemView ) {
            super(itemView);
            view = itemView;
            time = view.findViewById(R.id.hour);
            temp = view.findViewById(R.id.hour_temp);
            windSpeed = view.findViewById(R.id.hour_wind_speed);
            imageView = view.findViewById(R.id.hour_status_image);
        }
    }



    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.hourly_list_item ,
                parent ,false);

        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        HourlyWeather  weather = weatherList.get(position);

        holder.time.setText(weather.getHour());
        holder.temp.setText(weather.getTemp());
        holder.windSpeed.setText(weather.getWindSpeed());
        Picasso.get().load(weather.getWeatherIcon()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        if (weatherList==null)
            return 0;
        return weatherList.size();
    }


}
