package com.ezetap.android.service;

import android.content.Context;

import com.ezetap.android.R;
import com.ezetap.android.models.Config;
import com.google.gson.Gson;

import java.io.IOException;

/**
 * Created by arjun on 27/7/17.
 */

public class ConfigService extends EzetapBaseService {
    public static final int CONFIG_FILE_RES_ID = R.raw.configversion1;
    static Config config;

    public static Config getConfig(Context context) {
        if (config ==null) {
            Gson gson = new Gson();
            try {
                config = gson.fromJson(readInfoFromFile(context, CONFIG_FILE_RES_ID), Config.class);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        return config;
    }
}
