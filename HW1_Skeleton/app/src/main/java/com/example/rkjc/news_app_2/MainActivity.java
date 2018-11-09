package com.example.rkjc.news_app_2;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private NewsRecyclerViewAdapter mNewsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.news_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mNewsAdapter = new NewsRecyclerViewAdapter();
        mRecyclerView.setAdapter(mNewsAdapter);
        NewsQueryTask task = new NewsQueryTask(); // We make a new Async task
        task.execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){// Here we seem to be Overiding the original way that the menu is displayed by something else
        getMenuInflater().inflate(R.menu.main_menu,menu); // Here we are saying that we want to use thte menu we provided instead for menu
        return true; // nww displays menu
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){// we want to replace what an item does when it is clicked on

        int clicked = item.getItemId(); // this is the id of whatever was clicked

        if(clicked == R.id.action_search){// we know when check to see if the one that was clicked was the submit button and if it was then we do whatever inside the if


            NewsQueryTask task = new NewsQueryTask(); // We make a new Async task
            task.execute();
            return true;
        }

        return super.onOptionsItemSelected(item);

    }


    public class NewsQueryTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids){          // return json result string
            URL newsSearch = NetworkUtils.buildURL();
            //System.out.println(newsSearch);
            String results = null;
            try{
                results= NetworkUtils.getResponseFromHttpUrl(newsSearch); // Saves the results that you get from the search bar and saves
            }catch(IOException e){
                e.printStackTrace();

            }

            return results; // returns whatever you get from the request
        }

        @Override // I believe this art is right
        protected void onPostExecute(String results){ // run this once you get the results
            if(results != null && !results.equals("")){ // Making sure that the results are not empty
                JsonUtils jsonUtils = new JsonUtils();
                ArrayList<NewsItem> newsData = jsonUtils.parseNews(results);
                mNewsAdapter.setData(newsData);
                mNewsAdapter.notifyDataSetChanged();// here we tell them that it got changed
            }


        }


    }




}
