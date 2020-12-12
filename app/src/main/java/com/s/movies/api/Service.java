package com.s.movies.api;


import com.s.movies.model.Response;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Service {

    @GET("upcoming?api_key=9c0523bff54071c4fb4b716a950231b9&language=en-U&page=2&region=IN|US&with_release_type=2|3")
    Call<Response> getImageList();
}
