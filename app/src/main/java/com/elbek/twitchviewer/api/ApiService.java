package com.elbek.twitchviewer.api;

import com.elbek.twitchviewer.model.TopGamesResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {

    @GET("games/top")
    Observable<TopGamesResponse> getTopGamesResponse();
}
