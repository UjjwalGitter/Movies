package com.s.movies.db;

import android.content.Context;
import android.os.AsyncTask;


import com.s.movies.model.Movies;

import java.util.ArrayList;
import java.util.List;

public class GetMovies extends AsyncTask<Void, Void, List<Movies>> {

    Context mContext;
    String id;
    getMovies getMovies;

    public GetMovies(Context mContext, String id, getMovies getMovies) {
        this.mContext = mContext;
        this.id = id;
        this.getMovies = getMovies;
    }

    public interface getMovies {
        void setMovies(List<Movies> movies);
    }


    @Override
    protected List<Movies> doInBackground(Void... voids) {
        List<Movies> movies = new ArrayList<>();

        movies = DatabaseClient
                .getInstance(mContext)
                .getAppDatabase()
                .moviesDao()
                .getMovies();
        return movies;
    }


    @Override
    protected void onPostExecute(List<Movies> movies) {
        super.onPostExecute(movies);
        getMovies.setMovies(movies);
    }
}
