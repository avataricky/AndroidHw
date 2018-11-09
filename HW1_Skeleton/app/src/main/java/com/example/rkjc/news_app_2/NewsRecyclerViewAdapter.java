package com.example.rkjc.news_app_2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class NewsRecyclerViewAdapter  extends RecyclerView.Adapter<NewsRecyclerViewAdapter.NewsViewHolder> {
    private ArrayList<NewsItem> NewsData;// Here is all of the data that you get for the news
    private Context context;
    public NewsRecyclerViewAdapter(){// Empty Constructor



    }

    public void setData(ArrayList<NewsItem> news){
    NewsData = news;
    }



    public class NewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{ //
        public final TextView mNewsTitle;
        public final TextView mNewsDescription;
        public final TextView mNewsDate;
        public String url;
        public NewsViewHolder(View view){
            super(view);


            mNewsTitle = (TextView) view.findViewById(R.id.title);
            mNewsDescription = (TextView) view.findViewById(R.id.description);
            mNewsDate = (TextView) view.findViewById(R.id.date);
            view.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            Uri uri = Uri.parse(url);
            System.out.println(url);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);

            if(intent.resolveActivity(context.getPackageManager())!= null){
                context.startActivity(intent);
            }

        }

    }



    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.news_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        return new NewsViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder newsAdapterViewHolder, int i) {// This is what Gets binded to the
        newsAdapterViewHolder.url = NewsData.get(i).url;
        String newsTitle = NewsData.get(i).title;
        String newsDescription = NewsData.get(i).description;
        String publishedAt = NewsData.get(i).publishedAt;
        newsAdapterViewHolder.mNewsTitle.setText("Title: "+newsTitle);
        newsAdapterViewHolder.mNewsDescription.setText("Description: "+newsDescription);
        newsAdapterViewHolder.mNewsDate.setText("Date: "+publishedAt);
       }

    @Override
    public int getItemCount() { // This just returns how long the list of items is
        if(null == NewsData) return 0;
        return NewsData.size();
    }





}
