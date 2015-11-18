package info.evison.dan.robotcontroller1.util;

/**
 * Created by Dan on 10/11/2015.
 */
public class MathUtil {

    // Converts angle in radians to range (-2*PI, 2*PI]
    public static double normalizeRadians(final double angleRadians) {
        double angle = angleRadians;
        while (angle <= -Math.PI) angle += Math.PI * 2.0;
        while (angle > Math.PI) angle -= Math.PI * 2.0;
        return angle;
    }

    // Converts angle in radians to range (-2*PI, 2*PI]
    public static float normalizeRadians(final float angleRadians) {
        float angle = angleRadians;
        while (angle <= -Math.PI) angle += Math.PI * 2.0f;
        while (angle > Math.PI) angle -= Math.PI * 2.0f;
        return angle;
    }

    // Converts angle in radians to range (-180.0, 180.0]
    public static double normalizeDegrees(final double angleDegrees) {
        double angle = angleDegrees;
        while (angle <= -180.0) angle += 360.0;
        while (angle > 180.0) angle -= 360.0;
        return angle;
    }

    // Converts angle in radians to range (-180.0, 180.0]
    public static float normalizeDegrees(final float angleDegrees) {
        float angle = angleDegrees;
        while (angle <= -180f) angle += 360f;
        while (angle > 180f) angle -= 360f;
        return angle;
    }
}
