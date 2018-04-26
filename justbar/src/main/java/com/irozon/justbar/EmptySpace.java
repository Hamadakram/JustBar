package com.irozon.justbar;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

public class EmptySpace extends View{
    public EmptySpace(Context context) {
        super(context);

        init();
    }

    public EmptySpace(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public EmptySpace(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, 0);
        layoutParams.weight = 1;

        setLayoutParams(layoutParams);

    }
}
