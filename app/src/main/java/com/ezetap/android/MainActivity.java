package com.ezetap.android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ezetap.android.adapter.WidgetsRecyclerViewAdapter;
import com.ezetap.android.constants.EzetapConstants;
import com.ezetap.android.models.Config;
import com.ezetap.android.models.Screen;
import com.ezetap.android.service.ConfigService;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {
    RecyclerView widgetRecyclerView;
    ImageView imageLogo;
    TextView heading;
    Config config;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Screen screen = getScreenInfo();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        widgetRecyclerView = (RecyclerView) findViewById(R.id.widget_recycler_view);
        widgetRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        widgetRecyclerView.setAdapter(new WidgetsRecyclerViewAdapter(this, screen.getWidgets()));
        imageLogo = (ImageView) findViewById(R.id.logo);

        Glide.with(this)
                .load(config.getLogoUrl())
                .into(imageLogo);

    }

    public Screen getScreenInfo() {
        config = ConfigService.getConfig(this);
        if (config.getAppTheme().equals("THEME_1"))
            setTheme(R.style.AppTheme1);
        else if (config.getAppTheme().equals("THEME_2"))
            setTheme(R.style.AppTheme2);

        //  if the the screen is navigated from another screen
        if (getIntent().hasExtra(EzetapConstants.SCREEN_ID)) {
            String screenId = getIntent().getStringExtra(EzetapConstants.SCREEN_ID);

            //Find the screen with its id
            for (int i = 0; i < config.getScreens().size(); i++) {
                if (config.getScreens().get(i).getId().equals(screenId)) {
                    return config.getScreens().get(i);
                }
            }
        } else {
            //else load the main screen
            for (int i = 0; i < config.getScreens().size(); i++) {
                if (config.getScreens().get(i).isMain()) {
                    return config.getScreens().get(i);
                }
            }
        }
        return null;
    }
}
