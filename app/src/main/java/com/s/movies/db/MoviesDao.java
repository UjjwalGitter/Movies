package com.s.movies.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.s.movies.model.Movies;

import java.util.List;

@Dao
public interface MoviesDao {

    @Insert
    void saveMovies(Movies movies);

    @Query("SELECT * FROM Movies")
    List<Movies> getMovies();

}
