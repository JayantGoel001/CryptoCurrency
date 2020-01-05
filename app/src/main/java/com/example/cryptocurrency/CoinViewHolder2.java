package com.example.cryptocurrency;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class CoinViewHolder2 extends RecyclerView.ViewHolder {
    public ImageView coin_icon;
    public TextView coin_symbol,coin_name,coin_price,one_hour_change,twenty_hour_change,seven_day_change;
    public CoinViewHolder2(View view) {
        super(view);
        coin_symbol = view.findViewById (R.id.coinSymbol);
        coin_icon = view.findViewById(R.id.coinImage);
        coin_name = view.findViewById(R.id.coinName);
        coin_price = view.findViewById(R.id.priceUSDText);
        one_hour_change = view.findViewById(R.id.onHourText);
        twenty_hour_change = view.findViewById(R.id.twen4HourText);
        seven_day_change = view.findViewById(R.id.seven7Text);
    }
}
