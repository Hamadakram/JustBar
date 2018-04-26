package com.irozon.justbar;

import android.content.res.Resources;
import android.util.DisplayMetrics;

class Utils {
    static float dpToPixel(float dp) {
        Resources resources = Resources.getSystem();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return px;
    }
}
