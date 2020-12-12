package com.s.movies;

import com.s.movies.model.Movies;

import java.util.List;

public interface MoviesMVPView {
    void setMoviesList(List<Movies> moviesList);

    void setVideoList(List<Movies> moviesList);

    void setTopRatedList(List<Movies> movies);
}
