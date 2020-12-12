package com.s.movies.db;

import android.content.Context;
import android.os.AsyncTask;

import com.s.movies.model.Movies;


public class SaveMovies extends AsyncTask<Void, Void, Void> {
    Context mContext;
    Movies movies;

    public SaveMovies(Context mContext, Movies movies) {
        this.mContext = mContext;
        this.movies = movies;
    }

    @Override
    protected Void doInBackground(Void... voids) {

        //adding to database
        DatabaseClient.getInstance(mContext).getAppDatabase()
                .moviesDao()
                .saveMovies(movies);

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }
}
