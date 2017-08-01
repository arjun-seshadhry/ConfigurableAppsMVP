package com.ezetap.android.service;

import android.content.Context;

import com.ezetap.android.R;

import java.io.IOException;

/**
 * Created by arjun on 28/7/17.
 */

public class FetchService extends EzetapBaseService {

    public static void fetchInfo(Context context, String name, OnDataFetchedListener onDataFetchedListener) {
        try {
            onDataFetchedListener.onDataFetchedSucess(readInfoFromFile(context, getResIdFromName(name)));
        } catch (IOException e) {
            e.printStackTrace();
            onDataFetchedListener.onDataFetchFailure("Data fetch failed");
        }
    }

    static int getResIdFromName(String name) {
        switch (name) {
            default: {
                return R.raw.fetchinfo;
            }

        }
    }
}
