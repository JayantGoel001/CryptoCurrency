package com.example.cryptocurrency;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cryptocurrency2.LoadMore;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CoinAdapter2 extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    LoadMore iLoadMore;
    boolean isLoading;
    int visibleThreashold=5;
    int lastVisibleItem;
    int totalItemCount;
    Activity activity;
    List<Model> items;
    public CoinAdapter2(RecyclerView recyclerView , Activity activity, List<Model> items)
    {
        this.activity=activity;
        this.items=items;
        final LinearLayoutManager linearLayoutManager=(LinearLayoutManager)recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount=linearLayoutManager.getItemCount();
                lastVisibleItem= linearLayoutManager.findLastVisibleItemPosition();
                if(!isLoading && totalItemCount<=(lastVisibleItem+visibleThreashold))
                {
                    if(iLoadMore!=null)
                    {
                        iLoadMore.onLoadMore();
                    }
                    isLoading=true;
                }
            }
        });

    }
    public void  setiLoadMore(LoadMore iLoadMore2 ) {
        iLoadMore = iLoadMore2;
    }
        @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(activity).inflate(R.layout.coin_layout,parent,false);
            return new CoinViewHolder2(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Model item=items.get(position);
        CoinViewHolder2 holderItem=(CoinViewHolder2)holder;
        holderItem.coin_name.setText(item.getName());
        holderItem.coin_symbol.setText(item.getSymbol());
        holderItem.coin_price.setText("Price :"+item.getPrice_usd());
        holderItem.one_hour_change.setText("1 Hour Change : "+ item.getPercent_change_1h()+"%");
        holderItem.twenty_hour_change.setText("24 Hours Change : "+ item.getPercent_change_24h()+"%");
        holderItem.seven_day_change.setText("7 days Change : "+ item.getPercent_change_7d()+"%");

        Picasso.get().load(new StringBuilder("https://res.cloudinary.com/dxi90ksom/image/upload/").append(item.getSymbol().toLowerCase()).append(".png").toString()).into(holderItem.coin_icon);
        if (item.percent_change_1h.contains("-"))
        {
            holderItem.one_hour_change.setTextColor(Color.parseColor("#FF0000"));
        }
        else if(!item.percent_change_1h.contains("-"))
        {
            holderItem.one_hour_change.setTextColor(Color.parseColor("#32CD32"));
        }

        if (item.percent_change_24h.contains("-")) {
            holderItem.twenty_hour_change.setTextColor(Color.parseColor("#FF0000"));
        }
        else if(!item.percent_change_24h.contains("-"))
        {
            holderItem.twenty_hour_change.setTextColor(Color.parseColor("#32CD32"));
        }

        if (item.percent_change_7d.contains("-")) {
            holderItem.seven_day_change.setTextColor(Color.parseColor("#FF0000"));
        }
        else if(!item.percent_change_7d.contains("-"))
        {
            holderItem.seven_day_change.setTextColor(Color.parseColor("#32CD32"));
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
    public void setLoaded(){
        isLoading=false;
    }
    public void  updateData(List<Model> coinModels)
    {
        this.items=coinModels;
        notifyDataSetChanged();
    }
}


