package com.s.movies.db;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.s.movies.model.Movies;


@Database(entities = {Movies.class}, version = 1)
public abstract class DataBaseHelper extends RoomDatabase {
    public abstract MoviesDao moviesDao();
}
