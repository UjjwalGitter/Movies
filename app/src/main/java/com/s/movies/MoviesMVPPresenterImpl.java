package com.s.movies;

import android.content.Context;

import com.rowdy.common_methods.utils.Utils;
import com.s.movies.api.Client;
import com.s.movies.api.Service;
import com.s.movies.db.GetMovies;
import com.s.movies.db.SaveMovies;
import com.s.movies.model.Movies;
import com.s.movies.model.Response;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class MoviesMVPPresenterImpl implements MoviesMVPPresenter, GetMovies.getMovies {
    private Context mContext;
    private MoviesMVPView moviesMVPView;
    private Service service;
    List<Movies> moviesList;

    public MoviesMVPPresenterImpl(Context mContext, MoviesMVPView moviesMVPView) {
        this.mContext = mContext;
        this.moviesMVPView = moviesMVPView;
        service = Client.getRetrofit().create(Service.class);
    }

    @Override
    public void getMoviesList() {
        if (Utils.isNetworkConnected(mContext)) {
            loadOffLineData();
            service.getImageList().enqueue(new Callback<Response>() {
                @Override
                public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                    List<Movies> movies;
                    if (response.body() != null) {
                        movies = response.body().getMoviesArrayList();
                        if (movies != null) {
                            for (int i = 0; i < movies.size(); i++) {
                                Movies movies1 = new Movies();
                                movies1.setAdult(movies.get(i).getAdult());
                                movies1.setBackdrop_path(movies.get(i).getBackdrop_path());
                                movies1.setId(movies.get(i).getId());
                                movies1.setOriginal_language(movies.get(i).getOriginal_language());
                                movies1.setOriginal_title(movies.get(i).getOriginal_title());
                                movies1.setOverview(movies.get(i).getOverview());
                                movies1.setPopularity(movies.get(i).getPopularity());
                                movies1.setPoster_path(movies.get(i).getPoster_path());
                                movies1.setRelease_date(movies.get(i).getRelease_date());
                                movies1.setTitle(movies.get(i).getTitle());
                                movies1.setVideo(movies.get(i).isVideo());
                                movies1.setVote_average(movies.get(i).getVote_average());
                                movies1.setVote_count(movies.get(i).getVote_count());

                                int isPresent = 0;
                                if (moviesList != null) {
                                    for (int j = 0; j < moviesList.size(); j++) {
                                        if (moviesList.get(j).getId() == movies.get(i).getId()) {
                                            isPresent = 1;
                                        }
                                    }
                                }

                                if (isPresent == 0) {
                                    SaveMovies saveMovies = new SaveMovies(mContext, movies1);
                                    saveMovies.execute();
                                }
                            }
                        }
                    }
                    loadOffLineData();
                }

                @Override
                public void onFailure(Call<Response> call, Throwable t) {
                    loadOffLineData();
                }
            });
        } else {
            loadOffLineData();

        }
    }

    private void loadOffLineData() {
        GetMovies getMovies = new GetMovies(mContext, "", MoviesMVPPresenterImpl.this);
        getMovies.execute();
    }

    @Override
    public void setMovies(List<Movies> movies) {
        this.moviesList = movies;
        moviesMVPView.setMoviesList(movies);
        List<Movies> videoList = new ArrayList<>();

        for (int i = 0; i < movies.size(); i++) {
            if (movies.get(i).getBackdrop_path() != null) {
                videoList.add(movies.get(i));
            }
        }

        moviesMVPView.setVideoList(videoList);

        List<Movies> popularityList = new ArrayList<>();

        for (int j = 0; j < movies.size(); j++) {
            float popularity = Float.parseFloat(movies.get(j).getPopularity());
            if (5.0 <= popularity) {
                popularityList.add(movies.get(j));
            }
        }

        //moviesMVPView.setTopRatedList(popularityList);


    }
}
