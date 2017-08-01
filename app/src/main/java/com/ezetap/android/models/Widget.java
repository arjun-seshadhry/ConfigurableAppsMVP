package com.ezetap.android.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by arjun on 27/7/17.
 */

public class Widget {

    @SerializedName("id")
    String id;
    @SerializedName("type")
    String type;
    @SerializedName("ui-meta")
    UiMeta uiMeta;
    @SerializedName("targets")
    List<Target> targets;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public UiMeta getUiMeta() {
        return (uiMeta);
    }

    public void setUiMeta(UiMeta uiMeta) {
        this.uiMeta = uiMeta;
    }

    public List<Target> getTargets() {
        return targets;
    }

    public void setTargets(List<Target> targets) {
        this.targets = targets;
    }
}
