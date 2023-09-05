package com.example.weatherv2.WeatherUtilities;

import android.util.Log;
import java.io.IOException;
import java.util.Objects;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FetchFromNetwork {


    public static String HttpRequest(String url)
    {
        String Response = null ;
        OkHttpClient client = new OkHttpClient();

        Log.i("TAG", "HttpRequest: " + url );
        Request request = new Request.Builder()
                .url(url)
                .build()
                ;
        try {
            Response response = client.newCall(request).execute();

            if(response.isSuccessful())
            {
               Response = Objects.requireNonNull(response.body()).string();
            }
            else
                Log.i("OKHTTP Request", "HttpRequest : Failed no response received");

        } catch (IOException e) {
            Log.e("OKHTTP Request", "HttpRequest : Failed ", e);
        }
        return Response;
    }

}
