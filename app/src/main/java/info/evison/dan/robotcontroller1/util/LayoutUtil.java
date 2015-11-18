package info.evison.dan.robotcontroller1.util;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

public class LayoutUtil {

    // http://stackoverflow.com/a/9563438/5376044
    public static float convertDpToPixel(float dp, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return px;
    }

    public static int convertDpToPixel(int dp, Context context) {
        float px = convertDpToPixel((float) dp, context);
        return Math.round(px);
    }
}
