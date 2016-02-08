package com.example.sandeep.moviesapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by sandeep on 8/2/16.
 */
public class MovieDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_detail);
        ImageView imageView = (ImageView)findViewById(R.id.movie_poster);
        TextView movietitle = (TextView)findViewById(R.id.movie_title);
        TextView movierelease = (TextView)findViewById(R.id.movie_date);
        TextView movieplot = (TextView)findViewById(R.id.movie_plot);
        RatingBar movierb = (RatingBar)findViewById(R.id.movie_rating);

       Intent intent= getIntent();
        Bundle b = intent.getExtras();
        if(b!=null)
        {
            String image =(String) b.get("image");
            String title =(String) b.get("title");
            String date = (String) b.get("release");
            String plot = (String) b.get("overview");
            double rating = (double) b.get("rating");
            rating =(float)rating/2;

            Picasso.with(getApplicationContext()).load(image).fit().into(imageView);
            movietitle.setText(title);
            movierelease.setText("Release: " + date);
            movieplot.setText("Rating" + rating);
            movierb.setRating((float) rating);
            movieplot.setText(plot);

        }
       // Picasso.with().load(movieModelList.get(position).getPoster_path()).fit().into(imageView);
     //   movietitle.setText(movieModelList.get(position).getTitle());
       // movierelease.setText("Release:" +movieModelList.get(position).getRelease_date());
       // movieplot.setText(movieModelList.get(position).get());
        //movierb.getNumStars(movieModelList.get(position).getVote_average()/2);

    }

}
