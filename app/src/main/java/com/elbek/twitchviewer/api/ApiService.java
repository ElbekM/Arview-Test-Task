package com.elbek.twitchviewer.api;

import com.elbek.twitchviewer.model.TopGamesResponse;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;

public interface ApiService {

    @GET("games/top")
    Call<TopGamesResponse> topGamesResponse();
}
