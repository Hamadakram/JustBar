package com.irozon.justbar;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import static com.irozon.justbar.Utils.dpToPixel;


public class BarItem extends RelativeLayout {

    private static final String default_unselected_color = "#E0E0E0";
    private static final String default_selected_color = "#E53935";
    private static final String default_unselected_icon_color = "#000000";
    private static final String default_selected_icon_color = "#FFFFFF";
    private static final int default_radius = (int) dpToPixel(25);

    private final Context context;
    private ImageView imageView;
    private boolean selected;

    private int selectedColor;
    private int unSelectedColor;

    private int selectedIconColor;
    private int unSelectedIconColor;

    private int diameter;

    @Override
    public boolean isSelected() {
        return selected;
    }

    @Override
    public void setSelected(boolean selected) {
        this.selected = selected;

        if (selected)
            makeSelected();
        else
            makeUnSelected();
    }

    public BarItem(Context context) {
        super(context);

        this.context = context;
        init(null);
    }

    public BarItem(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.context = context;
        init(attrs);
    }

    public BarItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        this.context = context;
        init(attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        // Get Radius
        getLayoutParams().height = diameter;
        getLayoutParams().width = diameter;
    }

    private void init(AttributeSet attrs) {

        // Get Radius
        diameter = getRadius(attrs) * 2;

        // Get Selected Status
        selected = getSelectedStatus(attrs);

        // Get icon from attributes
        Drawable icon = getIcon(attrs);

        // Get selected/unselected color
        unSelectedColor = getUnSelectedColor(attrs);
        selectedColor = getSelectedColor(attrs);

        // Get selected/unselected color for icon
        unSelectedIconColor = getUnSelectedIconColor(attrs);
        selectedIconColor = getSelectedIconColor(attrs);

        // Add background to item
        setBackgroundResource(R.drawable.round_background);

        // Add imageView
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT); // A position in layout.

        imageView = new ImageView(context);
        imageView.setLayoutParams(layoutParams);

        if (icon != null)
            imageView.setImageDrawable(icon);

        addView(imageView);

        if (selected)
            makeSelected();

        setInitialColors();
    }

    /**
     * Set initial color of the BarItem according to the attributes
     */
    private void setInitialColors() {
        if (selected) {
            getBackground().setColorFilter(selectedColor, PorterDuff.Mode.SRC_IN);
            imageView.setColorFilter(selectedIconColor);
        } else {
            getBackground().setColorFilter(unSelectedColor, PorterDuff.Mode.SRC_IN);
            imageView.setColorFilter(unSelectedIconColor);
        }

    }

    /**
     * Get initial state of the BarItem (selected/unselected)
     *
     * @param attrs AttributeSet
     * @return selected or unselected
     */
    private boolean getSelectedStatus(AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.BarItem, 0, 0);
        try {
            return ta.getBoolean(R.styleable.BarItem_selected, false);
        } catch (Exception e) {
            return false;
        } finally {
            ta.recycle();
        }
    }

    /**
     * Get radius from the attributes
     *
     * @param attrs AttributeSet
     * @return radius of the BarItem
     */
    private int getRadius(AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.BarItem, 0, 0);
        try {
            return (int) ta.getDimension(R.styleable.BarItem_radius, default_radius);
        } catch (Exception e) {
            return default_radius;
        } finally {
            ta.recycle();
        }
    }

    /**
     * Get unselected color for BarItem from the attribute
     *
     * @param attrs AttributeSet
     * @return Color for unselected state for BarItem
     */
    private int getUnSelectedColor(AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.BarItem, 0, 0);
        try {
            return ta.getColor(R.styleable.BarItem_unSelectedColor, Color.parseColor(default_unselected_color));
        } catch (Exception e) {
            return Color.parseColor(default_unselected_color);
        } finally {
            ta.recycle();
        }
    }

    /**
     * Get selected color for BarItem from the attribute
     *
     * @param attrs AttributeSet
     * @return Color for selected state for BarItem
     */
    private int getSelectedColor(AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.BarItem, 0, 0);
        try {
            return ta.getColor(R.styleable.BarItem_selectedColor, Color.parseColor(default_selected_color));
        } catch (Exception e) {
            return Color.parseColor(default_selected_color);
        } finally {
            ta.recycle();
        }
    }

    /**
     * Get unselected color for icon from the attribute
     *
     * @param attrs AttributeSet
     * @return Color for unselected state for icon
     */
    private int getUnSelectedIconColor(AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.BarItem, 0, 0);
        try {
            return ta.getColor(R.styleable.BarItem_unSelectedIconColor, Color.parseColor(default_unselected_icon_color));
        } catch (Exception e) {
            return Color.parseColor(default_unselected_icon_color);
        } finally {
            ta.recycle();
        }
    }

    /**
     * Get Selected color for icon from the attribute
     *
     * @param attrs AttributeSet
     * @return Color for selected state for icon
     */
    private int getSelectedIconColor(AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.BarItem, 0, 0);
        try {
            return ta.getColor(R.styleable.BarItem_selectedIconColor, Color.parseColor(default_selected_icon_color));
        } catch (Exception e) {
            return Color.parseColor(default_selected_icon_color);
        } finally {
            ta.recycle();
        }
    }

    /**
     * Get Icon from the attributes
     *
     * @param attrs AttributeSet
     * @return Icon from the attributes provided
     */
    private Drawable getIcon(AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.BarItem, 0, 0);
        try {
            return ta.getDrawable(R.styleable.BarItem_icon);
        } catch (Exception e) {
            return null;
        } finally {
            ta.recycle();
        }
    }


    /**
     * Make BarItem unselected
     */
    private void makeSelected() {
        ResizeWidthAnimation anim = new ResizeWidthAnimation(this, (diameter + (diameter * 40) / 100));
        anim.setDuration(250);
        anim.setInterpolator(new BounceInterpolator(1, 1));

        startAnimation(anim);


        animateColor(this, unSelectedColor, selectedColor);

        animateColor(imageView, unSelectedIconColor, selectedIconColor);
    }

    /**
     * Make BarItem unselected
     */
    private void makeUnSelected() {
        ResizeWidthAnimation reverse = new ResizeWidthAnimation(this, diameter);
        reverse.setDuration(250);
        reverse.setInterpolator(new BounceInterpolator(1, 1));

        startAnimation(reverse);

        animateColor(this, selectedColor, unSelectedColor);

        animateColor(imageView, selectedIconColor, unSelectedIconColor);
    }

    /**
     * Animate Color on the view
     *
     * @param view      The view that's color going to change
     * @param fromColor Start color
     * @param toColor   End color
     */
    public void animateColor(final View view, int fromColor, int toColor) {
        ValueAnimator valueAnimator;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            valueAnimator = ValueAnimator.ofArgb(fromColor, toColor);
        } else {
            valueAnimator = ValueAnimator.ofObject(new ArgbEvaluator(), fromColor, toColor);
        }
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                if (view instanceof ImageView) {
                    ((ImageView) view).setColorFilter((Integer) valueAnimator.getAnimatedValue());
                } else {
                    getBackground().setColorFilter((Integer) valueAnimator.getAnimatedValue(), PorterDuff.Mode.SRC_IN);
                }
            }
        });

        valueAnimator.setDuration(300);
        valueAnimator.start();
    }
}
