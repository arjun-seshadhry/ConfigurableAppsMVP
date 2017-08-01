package com.ezetap.android.service;

import android.content.Context;
import android.support.annotation.RawRes;

import com.ezetap.android.R;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by arjun on 27/7/17.
 */

public class EzetapBaseService {
    public static String readInfoFromFile(Context context, @RawRes int rawResId) throws IOException{
        InputStream file = context.getResources().openRawResource(rawResId);
        byte[] data = new byte[file.available()];
        file.read(data);
        file.close();
        return new String(data);
    }

    public interface OnDataFetchedListener {
        void onDataFetchedSucess(String o);
        void onDataFetchFailure(String error);
    }
}
