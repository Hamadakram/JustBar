package com.irozon.justbar;

import android.view.animation.Interpolator;

import static java.lang.Math.PI;
import static java.lang.Math.abs;
import static java.lang.Math.cos;

class BounceInterpolator implements Interpolator {
    private int mBounces;
    private double mEnergy;

    public BounceInterpolator(){
        this(3);
    }

    BounceInterpolator(int bounces){
        this(bounces, 0.3f);
    }


    BounceInterpolator(int bounces, double energyFactor){
        mBounces = bounces;
        mEnergy = energyFactor + 0.5;
    }

    @Override public float getInterpolation(float x) {
        return (float) (1d + (-abs(cos(x * 10 * mBounces/PI)) * getCurveAdjustment(x)));
    }

    private double getCurveAdjustment(double  x){
        return -(2 * (1 - x) * x * mEnergy + x * x) + 1;
    }
}