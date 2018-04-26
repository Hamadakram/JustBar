package com.irozon.justbar;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

class ResizeWidthAnimation extends Animation {
    private float mWidth;
    private int mStartWidth;
    private View mView;

    ResizeWidthAnimation(View view, float width) {
        mView = view;
        mWidth = width;
        mStartWidth = view.getWidth();
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {

        mView.getLayoutParams().width = mStartWidth + (int) ((mWidth - mStartWidth) * interpolatedTime);
        mView.requestLayout();
    }

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
    }

    @Override
    public boolean willChangeBounds() {
        return true;
    }
}
