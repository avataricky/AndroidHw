package com.example.rkjc.news_app_2;


import org.junit.Test;

import java.net.URL;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class NetworkUnitTest {
    public static final String TAG = "NetworkUnitTest";

    @Test
    public void networkCallReturnsUsableJSON() throws Exception {
        URL url = new URL("https://newsapi.org/v1/articles?source=the-next-web&sortBy=latest&apiKey=fd837e9a8e454d3ea5be98042c32b792");
        String result = NetworkUtils.getResponseFromHttpUrl(url);
        System.out.println(result);
        assert(result.contains("\"status\":\"ok\""));
    }
}