package com.ezetap.android.adapter.widgetviewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.ezetap.android.R;

/**
 * Created by arjun on 27/7/17.
 */

public class PaymentButtonViewHolder extends RecyclerView.ViewHolder {
    public Button paymentButton;
    public PaymentButtonViewHolder(View itemView) {
        super(itemView);
        paymentButton = (Button) itemView.findViewById(R.id.paymentButton);
    }
}
