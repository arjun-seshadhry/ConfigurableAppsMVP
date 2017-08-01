package com.ezetap.android.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by arjun on 27/7/17.
 */

public class Target {
        @SerializedName("target")
        String target;
        @SerializedName("key")
        String key;

        public String getTarget() {
                return target;
        }

        public void setTarget(String target) {
                this.target = target;
        }

        public String getKey() {
                return key;
        }

        public void setKey(String key) {
                this.key = key;
        }
}
