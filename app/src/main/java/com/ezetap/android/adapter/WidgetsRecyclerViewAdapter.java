package com.ezetap.android.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ezetap.android.MainActivity;
import com.ezetap.android.R;
import com.ezetap.android.adapter.widgetviewholders.ButtonViewHolder;
import com.ezetap.android.adapter.widgetviewholders.LabelInputViewHolder;
import com.ezetap.android.adapter.widgetviewholders.LabelPairViewHolder;
import com.ezetap.android.adapter.widgetviewholders.NotSupportedViewHolder;
import com.ezetap.android.adapter.widgetviewholders.PaymentButtonViewHolder;
import com.ezetap.android.constants.EzetapConstants;
import com.ezetap.android.constants.WidgetConstants;
import com.ezetap.android.models.Target;
import com.ezetap.android.models.UiMeta;
import com.ezetap.android.models.Widget;
import com.ezetap.android.service.EzetapBaseService;
import com.ezetap.android.service.FetchService;
import com.ezetap.android.utils.EzetapUIContext;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ezetap.android.constants.WidgetConstants.WIDGET_LABEL_INPUT;

/**
 * Created by arjun on 27/7/17.
 **/

public class WidgetsRecyclerViewAdapter extends RecyclerView.Adapter {

    public static final int WIDGET_TYPE_NOT_SUPPORTED = 0;
    public static final int WIDGET_TYPE_BUTTON = 1;
    public static final int WIDGET_TYPE_PAYMENT_BUTTON = 2;
    public static final int WIDGET_TYPE_LABEL_PAIR = 3;
    public static final int WIDGET_TYPE_LABEL_INPUT = 4;

    List<Widget> widgets;
    LayoutInflater layoutInflater;
    Context context;
    Map<String, String> inputValues = new HashMap<>();

    public WidgetsRecyclerViewAdapter(Context context, List<Widget> widgets) {
        this.widgets = widgets;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case WIDGET_TYPE_BUTTON:
                return new ButtonViewHolder(inflateLayout(R.layout.widget_button, parent));
            case WIDGET_TYPE_LABEL_INPUT:
                return new LabelInputViewHolder(inflateLayout(R.layout.widget_label_input, parent));
            case WIDGET_TYPE_LABEL_PAIR:
                return new LabelPairViewHolder(inflateLayout(R.layout.widget_label_pair, parent));
            case WIDGET_TYPE_PAYMENT_BUTTON:
                return new PaymentButtonViewHolder(inflateLayout(R.layout.widget_payment_button, parent));
        }
        // handles unsupported view types
        return new NotSupportedViewHolder(inflateLayout(R.layout.widget_display_text, parent));
    }

    private View inflateLayout(@LayoutRes int layoutId, ViewGroup parent) {
        View itemView = layoutInflater
                .inflate(layoutId, parent, false);
        return (itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        holder.itemView.setTag(position);
        final Widget widget = widgets.get(position);
        switch (getItemViewType(position)) {
            case WIDGET_TYPE_BUTTON:
                ButtonViewHolder buttonViewHolder = (ButtonViewHolder) holder;
                buttonViewHolder.button.setText((widget.getUiMeta()).getText());
                if (widget.getTargets().get(0).getTarget().startsWith("SCREEN_")) {
                    //navigate to to the target screen
                    buttonViewHolder.button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            saveInputTargetsInUIContext();
                            Intent intent = new Intent(context, MainActivity.class);
                            String target = widget.getTargets().get(0).getTarget();
                            intent.putExtra(EzetapConstants.SCREEN_ID, target.substring("SCREEN_".length(), target.length()));
                            context.startActivity(intent);
                        }
                    });
                } else {
                    // call the API and navigate to the target screen
                    String[] target = widget.getTargets().get(0).getTarget().split(":");
                    final String targetApiName = target[0].substring("API_".length(), target[0].length());
                    final String targetScreenId = target[1].substring("SCREEN_".length(), target[1].length());
                    buttonViewHolder.button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            FetchService.fetchInfo(context, targetApiName, new EzetapBaseService.OnDataFetchedListener() {

                                @Override
                                public void onDataFetchedSucess(String o) {
                                    try {
                                        saveInputTargetsInUIContext();
                                        JSONObject object = new JSONObject(o);
                                        EzetapUIContext.getContext().get(targetApiName, object);
                                        Intent intent = new Intent(context, MainActivity.class);
                                        intent.putExtra(EzetapConstants.SCREEN_ID, targetScreenId);
                                        context.startActivity(intent);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }

                                @Override
                                public void onDataFetchFailure(String error) {
                                    Toast.makeText(context, "Unable to fetch data.", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                }
                break;

            case WIDGET_TYPE_LABEL_INPUT:
                LabelInputViewHolder labelInputViewHolder = (LabelInputViewHolder) holder;
                labelInputViewHolder.label.setText((widget.getUiMeta()).getLabelText());
                labelInputViewHolder.input.setHint((widget.getUiMeta()).getInputHint());
                labelInputViewHolder.input.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        inputValues.put(position+"", s.toString());
                    }
                });
                break;

            case WIDGET_TYPE_LABEL_PAIR:
                LabelPairViewHolder labelPairViewHolder = (LabelPairViewHolder) holder;
                labelPairViewHolder.rightTextView.setText(resolveStringValuesFromUiMeta(widget.getUiMeta().getTextRight()));
                labelPairViewHolder.leftTextView.setText((widget.getUiMeta()).getTextLeft());
                break;

            case WIDGET_TYPE_PAYMENT_BUTTON:
                PaymentButtonViewHolder paymentButtonViewHolder = (PaymentButtonViewHolder) holder;
                paymentButtonViewHolder.paymentButton.setText("Pay Button at" + position);
                break;

            // handles unsupported view types
            default:
                NotSupportedViewHolder notSupportedViewHolder = (NotSupportedViewHolder) holder;
        }
    }

    @Override
    public int getItemCount() {
        if (widgets == null)
            return 0;
        else
            return widgets.size();
    }

    @Override
    public int getItemViewType(int position) {
        switch (widgets.get(position).getType()) {
            case WidgetConstants.WIDGET_BUTTON:
                return WIDGET_TYPE_BUTTON;
            case WIDGET_LABEL_INPUT:
                return WIDGET_TYPE_LABEL_INPUT;
            case WidgetConstants.WIDGET_LABEL_PAIR:
                return WIDGET_TYPE_LABEL_PAIR;
            case WidgetConstants.WIDGET_PAYMENT_BUTTON:
                return WIDGET_TYPE_PAYMENT_BUTTON;
        }
        return WIDGET_TYPE_NOT_SUPPORTED;
    }

    private void saveInputTargetsInUIContext() {
        for (int i=0; i<widgets.size(); i++) {
            Widget widget = widgets.get(i);
            if (widget.getType().equals(WIDGET_LABEL_INPUT)) {
                if (widget.getTargets().size() > 0) {
                    for (Target target : widget.getTargets()) {
                        Log.d(target.getTarget() + "." + target.getKey(), inputValues.get(i+""));
                        EzetapUIContext.getContext().put(target.getTarget() + "." + target.getKey(), inputValues.get(i+""));
                    }
                }
            }
        }
    }

    private String resolveStringValuesFromUiMeta(String string) {
        if (string.startsWith("~") && string.endsWith("~")) {
            return EzetapUIContext
                    .getContext()
                    .get(string.substring(1, string.length() - 1)).toString();
        }
        return string;
    }
}
