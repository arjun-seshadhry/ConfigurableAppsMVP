package com.ezetap.android.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by arjun on 27/7/17.
 */

public class Config {
    @SerializedName("screens")
    List<Screen> screens;

    @SerializedName("app-theme")
    String appTheme;

    @SerializedName("logo-url")
    String logoUrl;

    public List<Screen> getScreens() {
        return screens;
    }

    public void setScreens(List<Screen> screens) {
        this.screens = screens;
    }

    public String getAppTheme() {
        return appTheme;
    }

    public void setAppTheme(String appTheme) {
        this.appTheme = appTheme;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }
}
