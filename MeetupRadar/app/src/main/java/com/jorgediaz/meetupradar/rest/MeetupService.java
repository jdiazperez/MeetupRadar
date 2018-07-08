package com.jorgediaz.meetupradar.rest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MeetupService {

    @GET("/2/open_events?&key=197732412647d7d267a393546fc68&sign=true&photo-host=public")
        //Call<ResultEventos> getEventos(@Query("lat") double lat, @Query("lon") double lon);
    Call<ResultEventos> getEventos(@Query("category") String category, @Query("lat") double lat, @Query("lon") double lon, @Query("radius") double radius);
}