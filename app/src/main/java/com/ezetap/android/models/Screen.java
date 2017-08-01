package com.ezetap.android.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by arjun on 27/7/17.
 */

public class Screen {
    @SerializedName("id")
    String id;

    @SerializedName("heading-text")
    String headingText;

    @SerializedName("is_main")
    boolean isMain;

    @SerializedName("widgets")
    List<Widget> widgets;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHeadingText() {
        return headingText;
    }

    public void setHeadingText(String headingText) {
        this.headingText = headingText;
    }

    public boolean isMain() {
        return isMain;
    }

    public void setMain(boolean main) {
        isMain = main;
    }

    public List<Widget> getWidgets() {
        return widgets;
    }

    public void setWidgets(List<Widget> widgets) {
        this.widgets = widgets;
    }
}
