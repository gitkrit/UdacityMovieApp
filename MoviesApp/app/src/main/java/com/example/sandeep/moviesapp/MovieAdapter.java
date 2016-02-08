package com.example.sandeep.moviesapp;


import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sandeep.moviesapp.Models.MovieModel;
import com.squareup.picasso.Picasso;

import java.util.List;

import static android.support.v4.app.ActivityCompat.startActivity;
import static android.support.v4.app.FragmentManager.*;


public class MovieAdapter extends ArrayAdapter {
    private static final String LOG_TAG = MovieAdapter.class.getSimpleName();

    private List<MovieModel> movieModelList;
    //  private int resource;
    private LayoutInflater inflater;

    public MovieAdapter(Activity context, List<MovieModel> objects) {
        super(context,0,objects);
        movieModelList = objects;
        //  this.resource = resource;
        //  inflater = (LayoutInflater)getSystemService()

    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {


        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.movie_item, parent, false);
        }

        ImageView iconView = (ImageView) convertView.findViewById(R.id.movie_image);
        if(iconView == null)
        {
            iconView = new ImageView(getContext());
        }
        Picasso.with(getContext()).load(movieModelList.get(position).getPoster_path()).fit().into(iconView);
        iconView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MovieDetail.class);
                intent.putExtra("image",movieModelList.get(position).getPoster_path());
                 intent.putExtra("title",movieModelList.get(position).getTitle());
                intent.putExtra("release", movieModelList.get(position).getRelease_date());
                intent.putExtra("rating",movieModelList.get(position).getVote_average());
                intent.putExtra("overview", movieModelList.get(position).getOverview());
                v.getContext().startActivity(intent);
            }
        });


        // TextView versionNameView = (TextView) convertView.findViewById(R.id.flavor_text);
        //  versionNameView.setText(movieModelList.get(position).getTitle());

        return convertView;
    }
}
