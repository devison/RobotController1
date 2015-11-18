package info.evison.dan.robotcontroller1.model;

import info.evison.dan.robotcontroller1.util.MathUtil;

// All angles in radians
public class NavigationCalc {

    private static float R_TO_D = 180f / (float) Math.PI;

    // In radians, functions for degrees
    public static class NavigationInput {
        public float azimuth = 0f;
        public float pitch = 0f;
        public float roll = 0f;

        public float azimuthDegrees() {
            return azimuth * R_TO_D;
        }

        public float pitchDegrees() {
            return pitch * R_TO_D;
        }

        public float rollDegrees() {
            return roll * R_TO_D;
        }
    }

    // In radians, functions for degrees
    public static class NavigationOutput {
        public float magnitude = 0f;
        public float deviceAzimuth = 0f;
        public float deviceAngle = 0f;

        public float combinedAzimuth() {
            return MathUtil.normalizeRadians(deviceAzimuth + deviceAngle);
        }

        public float deviceAzimuthDegrees() {
            return deviceAzimuth * R_TO_D;
        }

        public float deviceAngleDegrees() {
            return deviceAngle * R_TO_D;
        }

        public float combinedAzimuthDegrees() {
            return combinedAzimuth() * R_TO_D;
        }
    }

    private final static float EPSILON = (float) Math.toRadians(0.1);
    private final static float RIGHT_ANGLE = (float) Math.toRadians(90.0);
    private final static float HALF_TURN = (float) Math.toRadians(180.0);

    public static void calc(final NavigationInput input, final NavigationOutput output) {

        final double sp = Math.sin(input.pitch);
        final double cp = Math.cos(input.pitch);
        final double sr = Math.sin(input.roll);

        final double y = sp;
        final double x = sr * cp;

        output.deviceAzimuth = input.azimuth;
        output.magnitude = (float) Math.sqrt(x * x + y * y);

        if (input.pitch > (RIGHT_ANGLE - EPSILON))   // fully leaning to left
            output.deviceAngle = 0f;
        else if (input.pitch < -(RIGHT_ANGLE - EPSILON))  // fully leaning to right
            output.deviceAngle = HALF_TURN;
        else if (Math.abs(input.roll) < EPSILON)
            if (Math.abs(input.pitch) < EPSILON)   // dead centre, assume facing forward
                output.deviceAngle = RIGHT_ANGLE;
            else  // no roll, just pitch
                output.deviceAngle = input.pitch < 0.0f ? HALF_TURN : 0f;
        else // Note: cp and sr will be non-zero, by prior tests
            output.deviceAngle = RIGHT_ANGLE - (float) Math.atan2(sp, cp * sr);
    }
}
