package com.example.weatherv2.WeatherUtilities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class GetLocation {

    public final static String GET_LON_LAT_FROM_API = "https://api.openweathermap.org/geo/1.0/direct?q=";


    public static String getLatitudeLongitudeFromApiResponse(String Response)
    {
        String answer = null ;
        try {
            JSONArray array = new JSONArray(Response);
            JSONObject object = array.getJSONObject(0);
            answer ="lat="+object.getString("lat");
            answer =answer.concat("&lon="+object.getString("lon"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return answer;
    }


    public static String queryLatitudeLongitudeFromApi(String url)
    {
        String Response = null;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        try {
            okhttp3.Response response = client.newCall(request).execute();

            if (response.isSuccessful())
            {
                Response = response.body().string();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Response;
    }
}
