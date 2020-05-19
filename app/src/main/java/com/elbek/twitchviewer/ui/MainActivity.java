package com.elbek.twitchviewer.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.elbek.twitchviewer.R;
import com.elbek.twitchviewer.api.ApiClient;
import com.elbek.twitchviewer.api.ApiService;
import com.elbek.twitchviewer.database.AppDatabase;
import com.elbek.twitchviewer.database.TwitchStreamDao;
import com.elbek.twitchviewer.model.TwitchStream;
import com.elbek.twitchviewer.model.TopGamesResponse;
import com.elbek.twitchviewer.ui.adapter.TwitchStreamAdapter;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private LinearLayoutManager linearLayoutManager;
    private RecyclerView recyclerView;
    private TwitchStreamAdapter adapter;

    private TwitchStreamDao db;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.main_progress);

        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setInitialPrefetchItemCount(5);

        recyclerView = findViewById(R.id.recycler_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new TwitchStreamAdapter(MainActivity.this);
        recyclerView.setAdapter(adapter);

        // Room Database init
        AppDatabase appDatabase = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "twitch-database")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();

        db = appDatabase.getTwitchStreamDao();

        getStreamData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.review:
                showReviewDialog();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void getStreamData() {
        progressBar.setVisibility(View.VISIBLE);

        ApiService apiService = ApiClient.getApiClient().create(ApiService.class);
        Observable<TopGamesResponse> call = apiService.getTopGamesResponse();

        call.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TopGamesResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(TopGamesResponse topGamesResponse) {
                        List<TwitchStream> streams = topGamesResponse.getTopGames();
                        setData(streams);
                        cacheToDatabase(streams);
                    }

                    @Override
                    public void onError(Throwable e) {
                        setData(db.getAllArticles());
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(MainActivity.this, "Loaded from database", Toast.LENGTH_SHORT)
                                .show();
                        //Log(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        progressBar.setVisibility(View.GONE);
                    }
                });
    }

    private void setData(List<TwitchStream> streams) {
        adapter.clear();
        adapter.addAll(streams);
    }

    private void cacheToDatabase(List<TwitchStream> streams) {
        if (!db.getAllArticles().isEmpty())
            db.deleteAll();
        db.insertAll(streams);
    }

    private void showReviewDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setView(R.layout.rate_dialog)
                .setPositiveButton("Send", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Rate logic
                        Toast.makeText(MainActivity.this, "Thanks for the feedback", Toast.LENGTH_SHORT)
                                .show();
                    }
                })
                .setNegativeButton("Rate later", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "Okay(", Toast.LENGTH_SHORT)
                                .show();
                    }
                });

        builder.create().show();
    }
}
