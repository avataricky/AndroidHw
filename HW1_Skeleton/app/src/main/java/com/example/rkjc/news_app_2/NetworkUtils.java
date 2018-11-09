package com.example.rkjc.news_app_2;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {
    private static final  String base_url  = "https://newsapi.org/v1/articles?source=the-next-web&sortBy=latest";
    private static final String query_parameter = "apiKey";
    private static final String key = "fd837e9a8e454d3ea5be98042c32b792";





    public static URL buildURL() {
        Uri builtUri = Uri.parse(base_url).buildUpon()
                .appendQueryParameter("sortBy", "latest")
                .appendQueryParameter(query_parameter, key)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }







    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection =(HttpURLConnection) url.openConnection();
        try{
            InputStream in = urlConnection.getInputStream();
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");
            boolean hasInput = scanner.hasNext();

            if(hasInput){
                return scanner.next();

            }else{

                return null;
            }

        }finally{
            urlConnection.disconnect();
        }


    }


}
