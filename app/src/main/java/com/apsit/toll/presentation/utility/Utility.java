package com.apsit.toll.presentation.utility;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.DisplayMetrics;

import com.apsit.toll.R;
import com.apsit.toll.data.network.pojo.toll.Toll;

/**
 * Created by adityathanekar on 01/07/16.
 */

public class Utility {
    public static int getToolbarHeight(Context context) {
        final TypedArray styledAttributes = context.getTheme().obtainStyledAttributes(
                new int[]{R.attr.actionBarSize});
        int toolbarHeight = (int) styledAttributes.getDimension(0, 0);
        styledAttributes.recycle();

        return toolbarHeight;
    }

    public static float convertDpToPixel(float dp, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return px;
    }

    public static float convertPixelsToDp(float px, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dp = px / ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return dp;
    }

    public static String formatFloat(double d)
    {
        if(d == (long) d)
            return String.format("%d",(long)d);
        else
            return String.format("%s",d);
    }

    public static int getVehicleIcon(int type) {
        switch (type) {
            case Toll.SELECT_TYPE_TWO_AXLE:
                return R.drawable.two_axle;
            case Toll.SELECT_TYPE_TWO_AXLE_HEAVY:
                return R.drawable.two_axle_heavy;
            case Toll.SELECT_TYPE_LCV:
                return R.drawable.lcv;
            case Toll.SELECT_TYPE_UPTO_THREE_AXLE:
                return R.drawable.upto_three_axle;
            case Toll.SELECT_TYPE_FOUR_AXLE_MORE:
                return R.drawable.four_axle_more;
            default:
                return -1;
        }
    }
}
