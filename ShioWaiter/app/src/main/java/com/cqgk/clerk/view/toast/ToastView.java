package com.cqgk.clerk.view.toast;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cqgk.clerk.R;


/**
 * Created by duke on 16/3/29.
 */
public class ToastView extends LinearLayout {
    public ToastView(Context context,
                     String content) {
        super(context);
        initView(content);
    }

    public ToastView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView("");
    }

    private void initView(String content) {
        LayoutInflater mInflater = LayoutInflater.from(getContext());
        View view = mInflater.inflate(R.layout.toastview, null);
        TextView text = (TextView) view.findViewById(R.id.text);
        text.setText(content);
        addView(view);

    }
}


