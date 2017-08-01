package com.ezetap.android.adapter.widgetviewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.ezetap.android.R;

import org.json.JSONObject;

/**
 * Created by arjun on 27/7/17.
 */

public class ButtonViewHolder extends RecyclerView.ViewHolder {
    public Button button;
    public ButtonViewHolder(View itemView) {
        super(itemView);
        button = (Button) itemView.findViewById(R.id.button);
    }
}
