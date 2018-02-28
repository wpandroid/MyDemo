package com.example.stopgap.mydemo.gallary;

import android.content.Context;
import android.content.SharedPreferences;


import com.example.stopgap.mydemo.R;
import com.example.stopgap.mydemo.application.MyApplication;

import java.util.Arrays;
import java.util.List;

/**
 * Created by yarolegovich on 07.03.2017.
 */

public class Shop {

    private static final String STORAGE = "shop";

    public static Shop get() {
        return new Shop();
    }

    private SharedPreferences storage;

    private Shop() {
        storage = MyApplication.getInstance().getSharedPreferences(STORAGE, Context.MODE_PRIVATE);
    }

    public List<Item> getData() {
        return Arrays.asList(
                new Item(1, "Everyday Candle", "$12.00 USD", R.drawable.asn_team),
                new Item(2, "Small Porcelain Bowl", "$50.00 USD", R.drawable.mc_team),
                new Item(3, "Favourite Board", "$265.00 USD", R.drawable.ml_team));
    }

    public boolean isRated(int itemId) {
        return storage.getBoolean(String.valueOf(itemId), false);
    }

    public void setRated(int itemId, boolean isRated) {
        storage.edit().putBoolean(String.valueOf(itemId), isRated).apply();
    }
}
