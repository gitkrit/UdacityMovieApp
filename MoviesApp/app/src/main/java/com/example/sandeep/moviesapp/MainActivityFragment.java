package com.example.sandeep.moviesapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.sandeep.moviesapp.Models.MovieModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * A fragment containing the list view of Android versions.
 */
public class MainActivityFragment extends Fragment {

    private MovieAdapter movieAdapter;
    private GridView gridView;
    private String url;

    public MainActivityFragment() {

    }


    public void getURL(String s)
    {
        this.url=s;
        new JSONTask().execute(url);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        /*Http Connection*/
         url="http://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=46a246a1cf3d49a43ebdf812fa43f183";
         new JSONTask().execute(url);
        /*http connection finish */

        // Get a reference to the ListView, and attach this adapter to it.
         gridView = (GridView) rootView.findViewById(R.id.movies_grid);
        return rootView;
    }



public class JSONTask extends AsyncTask<String, String, List<MovieModel>>
    {

        @Override
        protected List<MovieModel> doInBackground(String... params) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection)url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();

                String line = "";
                while((line = reader.readLine()) != null)
                {
                    buffer.append(line);
                }
                String finalJson = buffer.toString();

                JSONObject parentObject = new JSONObject(finalJson);
                JSONArray parentArray = parentObject.getJSONArray("results");

                List<MovieModel> movieModelList = new ArrayList<>();

                for(int i=0; i<parentArray.length() ;i++)
                {
                    JSONObject finalObject = parentArray.getJSONObject(i);
                    MovieModel movieModel = new MovieModel();
                    movieModel.setPoster_path(finalObject.getString("poster_path"));
                    movieModel.setTitle(finalObject.getString("title"));
                    movieModel.setRelease_date(finalObject.getString("release_date"));
                    movieModel.setVote_average(finalObject.getDouble("vote_average"));
                    movieModel.setOverview(finalObject.getString("overview"));
                    movieModelList.add(movieModel);
                }

                return movieModelList;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if(connection != null) {
                    connection.disconnect();
                }
                try {
                    if(reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;

        }

        @Override
        protected void onPostExecute(List<MovieModel> s) {
            super.onPostExecute(s);

            movieAdapter = new MovieAdapter(getActivity(), s);
            gridView.setAdapter(movieAdapter);

        }

    }

}

    