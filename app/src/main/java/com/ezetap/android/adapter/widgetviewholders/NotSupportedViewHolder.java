package com.ezetap.android.adapter.widgetviewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ezetap.android.R;

/**
 * Created by arjun on 27/7/17.
 */

public class NotSupportedViewHolder extends RecyclerView.ViewHolder {
    public TextView textView;
    public NotSupportedViewHolder(View itemView) {
        super(itemView);
        textView = (TextView) itemView.findViewById(R.id.displayTextView);
    }
}
