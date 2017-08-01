package com.ezetap.android.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by arjun on 27/7/17.
 */

public class UiMeta {
    /**
     * Button ui meta
     * **/
    @SerializedName("text")
    String text;

    @SerializedName("labels")
    List<String> labels;


    /**
     * label-input ui meta
     * **/
    @SerializedName("label-text")
    String labelText;

    @SerializedName("input-hint")
    String inputHint;

    /**
     * label pair ui meta
     * **/

    @SerializedName("text-left")
    String textLeft;

    @SerializedName("text-right")
    String textRight;



    /**
     *  ui meta common to both label pair and label-input
     * **/


    @SerializedName("input-constraints")
    InputConstraints inputConstraints;

    public class InputConstraints {
        // input types{ DATE, EMAIL, NUMBER, CUSTOM_FORMULA, CUSTOM_REGEX};

        @SerializedName("minLen")
        int minLen;
        @SerializedName("maxLen")
        int maxLen;
        @SerializedName("input-type")
        String inputType;
        @SerializedName("custom-constraint")
        String customConstraint;

    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public String getLabelText() {
        return labelText;
    }

    public void setLabelText(String labelText) {
        this.labelText = labelText;
    }

    public String getInputHint() {
        return inputHint;
    }

    public void setInputHint(String inputHint) {
        this.inputHint = inputHint;
    }

    public String getTextLeft() {
        return textLeft;
    }

    public void setTextLeft(String textLeft) {
        this.textLeft = textLeft;
    }

    public String getTextRight() {
        return textRight;
    }

    public void setTextRight(String textRight) {
        this.textRight = textRight;
    }

    public InputConstraints getInputConstraints() {
        return inputConstraints;
    }

    public void setInputConstraints(InputConstraints inputConstraints) {
        this.inputConstraints = inputConstraints;
    }
}
