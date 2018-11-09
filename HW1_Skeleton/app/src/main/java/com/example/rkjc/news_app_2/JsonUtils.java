package com.example.rkjc.news_app_2;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class JsonUtils {



    public static ArrayList<NewsItem> parseNews(String jsonString) {

        ArrayList<NewsItem> newsData;
        try {
            JSONObject newsJson = new JSONObject(jsonString);

            JSONArray newsArray = newsJson.getJSONArray("articles");

            newsData = new ArrayList<NewsItem>(); // We want to make a bunch of these thingies of the certain amount of the items in the Json


            for (int i = 0; i < newsArray.length(); i++) {
                String title;
                String description;
                String date;
                String author;
                String url;
                String urlToImage;
                JSONObject dayNews = newsArray.getJSONObject(i);


                author = dayNews.getString("author");
                title = dayNews.getString("title");
                description = dayNews.getString("description");
                url = dayNews.getString("url");
                urlToImage = dayNews.getString("urlToImage");
                date = dayNews.getString("publishedAt");

                NewsItem holdItem = new NewsItem(author, title, description, url, urlToImage, date);

                newsData.add(holdItem);
            }
            return newsData;
        }catch (JSONException e){


        }
    return null;
    }


}


