package com.irozon.justbar;

import android.animation.LayoutTransition;
import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

import com.irozon.justbar.interfaces.OnBarItemClickListener;

public class JustBar extends LinearLayout implements View.OnClickListener {
    private final Context context;
    private boolean initialSetup;
    private OnBarItemClickListener onBarItemClickListener;

    public JustBar(Context context) {
        super(context);

        this.context = context;
        init();
    }

    public JustBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        this.context = context;
        init();
    }

    public JustBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        this.context = context;
        init();
    }

    private void init() {
        LayoutTransition lt = new LayoutTransition();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            lt.disableTransitionType(LayoutTransition.DISAPPEARING);
        }
        setLayoutTransition(lt);


        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        if (!initialSetup) {
            // Click listeners for items
            for (int i = 0; i < getChildCount(); i++) {
                getChildAt(i).setTag(i);
                getChildAt(i).setOnClickListener(this);
            }
            // Add spaces between all the items
            addEmptySpacesBetweenEveryItem();

            initialSetup = true;
        }
    }

    private void addEmptySpacesBetweenEveryItem() {
        // Get Child count
        int childs = getChildCount();

        if (childs == 0) return;

        for (int i = 0; i <= childs * 2; i = i + 2) {
            addView(new EmptySpace(context), i);
        }
        invalidate();
    }

    @Override
    public void onClick(View view) {

        // Get clicked position from tag
        int position = Integer.parseInt(view.getTag().toString());

        if (onBarItemClickListener != null) {
            onBarItemClickListener.onBarItemClick((BarItem) view, position);
        }

        // Unselect previous item
        View selectedView = getSelected();
        if (selectedView != null) {
            selectedView.setSelected(false);
        }

        // Set new item
        view.setSelected(true);
    }

    /**
     * On BarItem click listener
     * @param onBarItemClickListener
     */
    public void setOnBarItemClickListener(OnBarItemClickListener onBarItemClickListener) {
        this.onBarItemClickListener = onBarItemClickListener;
    }

    /**
     * Get item from the bar by its position
     * @param position Position of the BarItem
     * @return  BarItem
     */
    public BarItem getItemAt(int position) {
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            if (child instanceof BarItem) {
                if (Integer.parseInt(String.valueOf(child.getTag())) == position) {
                    return (BarItem) child;
                }
            }
        }
        return null;
    }

    /**
     * Get selected item from the bar
     * @return BarItem - Selected item
     */
    public BarItem getSelected() {
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            if (child instanceof BarItem) {
                if (child.isSelected()) {
                    return (BarItem) child;
                }
            }
        }
        return null;
    }

    /**
     * Set the item to be selected by its position
     * @param position
     */
    public void setSelected(int position) {
        BarItem shouldSelected = getItemAt(position);
        if (shouldSelected == null) return;
        shouldSelected.performClick();
    }
}
