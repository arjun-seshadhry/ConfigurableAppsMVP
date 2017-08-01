package com.ezetap.android.adapter.widgetviewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ezetap.android.R;

import org.json.JSONObject;

/**
 * Created by arjun on 27/7/17.
 */

public class LabelPairViewHolder extends RecyclerView.ViewHolder {
    public TextView leftTextView, rightTextView;
    public LabelPairViewHolder(View itemView) {
        super(itemView);
        leftTextView = (TextView) itemView.findViewById(R.id.leftTextView);
        rightTextView = (TextView) itemView.findViewById(R.id.rightTextView);
    }
}
