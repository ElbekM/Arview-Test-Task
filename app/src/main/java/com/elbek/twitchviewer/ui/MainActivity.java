package com.elbek.twitchviewer.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ProgressBar;

import com.elbek.twitchviewer.R;
import com.elbek.twitchviewer.api.ApiClient;
import com.elbek.twitchviewer.api.ApiService;
import com.elbek.twitchviewer.model.GameOverview;
import com.elbek.twitchviewer.model.TopGamesResponse;
import com.elbek.twitchviewer.ui.adapter.RecyclerAdapter;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private LinearLayoutManager linearLayoutManager;
    private RecyclerView recyclerView;
    private RecyclerAdapter adapter;
    private ApiService apiService;

    private ProgressBar progressBar;

    Boolean isScrolling = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.main_progress);

        linearLayoutManager = new LinearLayoutManager(this);
        //linearLayoutManager.setInitialPrefetchItemCount(5);

        recyclerView = findViewById(R.id.recycler_list);
        //recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        apiService = ApiClient.getClient().create(ApiService.class);
        performPagination();
    }

    private void performPagination() {
        progressBar.setVisibility(View.VISIBLE);
        Call<TopGamesResponse> call = apiService.topGamesResponse();

        call.enqueue(new Callback<TopGamesResponse>() {
            @Override
            public void onResponse(Call<TopGamesResponse> call, Response<TopGamesResponse> response) {
                if (response.isSuccessful()) {
                    //System.out.println(new Gson().toJson(response.body()));
                    List<GameOverview> streams = response.body().getTop();
                    setRecyclerAdapter(streams);
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onFailure(Call<TopGamesResponse> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }

    public void setRecyclerAdapter(List<GameOverview> streams) {
        adapter = new RecyclerAdapter(streams, MainActivity.this);
        recyclerView.setAdapter(adapter);
        System.out.println("SUKKKKKKKKAAAAAAAAAAAAAAAAAAAAAAAAAAAAaa");
    }

    private void onScrollListener() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int currentItems = linearLayoutManager.getChildCount();
                int totalItems = linearLayoutManager.getItemCount();
                int scrollOutItems = linearLayoutManager.findFirstVisibleItemPosition();

                if(isScrolling && (currentItems + scrollOutItems == totalItems)) {
                    isScrolling = false;
                    //performPagination();
                }
            }
        });
    }
}
