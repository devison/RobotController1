package info.evison.dan.robotcontroller1.util;

import android.graphics.Color;

public class ColorUtil {

    public static int darkenColor(int color, float factor) {
        int a = Color.alpha(color);
        int r = Color.red(color);
        int g = Color.green(color);
        int b = Color.blue(color);

        return Color.argb(a,
                Math.max((int) (r * factor), 0),
                Math.max((int) (g * factor), 0),
                Math.max((int) (b * factor), 0));
    }

    private static int lightenPart(int part, float factor) {
        return Math.min(255, part + (int) ((255 - part) * factor));
    }

    public static int lightenColor(int color, float factor) {
        return Color.argb(Color.alpha(color),
                lightenPart(Color.red(color), factor),
                lightenPart(Color.green(color), factor),
                lightenPart(Color.blue(color), factor));
    }
}
