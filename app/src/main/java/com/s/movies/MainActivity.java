package com.s.movies;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.s.movies.adapter.NewReleaseAdapter;
import com.s.movies.adapter.NewVideosAdapter;
import com.s.movies.model.Movies;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MoviesMVPView {
    MoviesMVPPresenterImpl presenter;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerViewVideos)
    RecyclerView recyclerViewVideos;
    @BindView(R.id.recyclerViewReleases)
    RecyclerView recyclerViewReleases;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.LayoutManager layoutManager2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenter = new MoviesMVPPresenterImpl(this, this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.getMoviesList();
    }

    @Override
    public void setMoviesList(List<Movies> moviesList) {
        NewVideosAdapter newVideosAdapter = new NewVideosAdapter(this, moviesList);
        layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);

        recyclerViewReleases.setLayoutManager(layoutManager);
        recyclerViewReleases.setAdapter(newVideosAdapter);
    }

    @Override
    public void setVideoList(List<Movies> moviesList) {
        NewReleaseAdapter newReleaseAdapter = new NewReleaseAdapter(this, moviesList);
        layoutManager2 = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        recyclerViewVideos.setLayoutManager(layoutManager2);
        recyclerViewVideos.setAdapter(newReleaseAdapter);
    }

}