package com.example.cryptocurrency;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.widget.Toast;

import com.example.cryptocurrency2.LoadMore;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Main2Activity extends AppCompatActivity {
    List<Model> items=new ArrayList<Model>();
    CoinAdapter2 adapter2;
    RecyclerView recyclerView;
    OkHttpClient client;
    Request request;
    SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        swipeRefreshLayout=findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                loadFirst10Coin(0);
            }
        });
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                items.clear();
                loadFirst10Coin(0);
                setUpAdapter();
            }
        });
        recyclerView=findViewById(R.id.coinList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        setUpAdapter();
    }

    private void loadFirst10Coin(int i) {

        client=new OkHttpClient();
        request=new Request.Builder().url(String.format("https://api.coinmarketcap.com/v1/ticker/?start=%d&limit=10",i)).build();
        swipeRefreshLayout.setRefreshing(true);
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

                Toast.makeText(Main2Activity.this,e.getMessage(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String body= Objects.requireNonNull(response.body()).toString();
                Gson gson=new Gson();
                final List<Model> newItems=gson.fromJson(body,new TypeToken<List<Model>>(){}.getType());
                runOnUiThread(() -> adapter2.updateData(newItems));
            }
        });
        if(swipeRefreshLayout.isRefreshing())
        {
            swipeRefreshLayout.setRefreshing(false);
        }
    }
    private void loadNext10Coin(int size) {
        client=new OkHttpClient();
        request=new Request.Builder().url(String.format("https://api.coinmarketcap.com/v1/ticker/?start=%d&limit=10",size)).build();
        swipeRefreshLayout.setRefreshing(true);
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

                Toast.makeText(Main2Activity.this,e.getMessage(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String body=response.body().string();
                Gson gson=new Gson();
                final List<Model> newItems=gson.fromJson(body,new TypeToken<List<Model>>(){}.getType());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        items.addAll(newItems);
                        adapter2.setLoaded();
                        adapter2.updateData(items);
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
        });
    }

    private void setUpAdapter() {
        adapter2=new CoinAdapter2(recyclerView,this,items);
        recyclerView.setAdapter(adapter2);
        adapter2.setiLoadMore(new LoadMore() {
            @Override
            public void onLoadMore() {
                if(items.size()<=1000)
                {
                    loadNext10Coin(items.size());
                }
                else
                {
                    Toast.makeText(Main2Activity.this,"Maximum items are 1000",Toast.LENGTH_LONG).show();
                }
            }
        }
        );
    }
}
