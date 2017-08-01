package com.ezetap.android.adapter.widgetviewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ezetap.android.R;

/**
 * Created by arjun on 27/7/17.
 */

public class LabelInputViewHolder extends RecyclerView.ViewHolder {
    public TextView label;
    public EditText input;
    public LabelInputViewHolder(View itemView) {
        super(itemView);
        label = (TextView) itemView.findViewById(R.id.label);
        input = (EditText) itemView.findViewById(R.id.input);
    }
}
