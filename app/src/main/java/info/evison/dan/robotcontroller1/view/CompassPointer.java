package info.evison.dan.robotcontroller1.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.VectorDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

import info.evison.dan.robotcontroller1.model.NavigationCalc;
import info.evison.dan.robotcontroller1.R;
import info.evison.dan.robotcontroller1.util.ColorUtil;
import info.evison.dan.robotcontroller1.util.MathUtil;

public class CompassPointer extends ImageView {

    private static final String TAG = CompassPointer.class.getSimpleName();

    final static float MIN_MAGNITUDE_MOVE = 0.06f;

    final static float MIN_MAGNITUDE_ACCENT = 0.19f;
    final static float MAX_MAGNITUDE = 0.6f;

    final static float PRIMARY_LIGHTEN_MIN = 0.6f; // paler
    final static float PRIMARY_LIGHTEN_MAX = 0.8f; // much paler

    final static float ACCENT_LIGHTEN_MIN = 0.0f;  // full strength color
    final static float ACCENT_LIGHTEN_MAX = 0.7f;  // much paler

    final static float DEVICE_AZIMUTH_LOW_PASS_FACTOR = 0.3f;
    final static float DEVICE_ANGLE_LOW_PASS_FACTOR = 0.3f;
    final static float MAGNITUDE_LOW_PASS_FACTOR = 0.5f;

    final NavigationCalc.NavigationOutput navigationOutput = new NavigationCalc.NavigationOutput();

    int accentColor;
    int primaryColor;

    protected VectorDrawable _vectorDrawable;

    public CompassPointer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.setImageResource(R.drawable.compass_pointer);
        _vectorDrawable = (VectorDrawable) this.getDrawable();
//        final TypedValue typedValue = new TypedValue();
//        context.getTheme().resolveAttribute(R.color.colorAccent, typedValue, true);
//        accentColor = typedValue.data;
        accentColor = Color.parseColor("#FF5252");
        primaryColor = Color.parseColor("#607D8B");
    }

    // Called when component is to be drawn
    @Override
    public void onDraw(Canvas canvas) { //
        int height = this.getHeight();
        int width = this.getWidth();

        final int scaledColor = getScaledColor();

        _vectorDrawable.setTint(scaledColor);

        // Need 90 degree anti-clockwise turn since in landscape, Magnetic North is left.  TODO: rethink?
        final float rotateAngle = MathUtil.normalizeDegrees(navigationOutput.deviceAngleDegrees() - 90f);

        canvas.rotate(rotateAngle, width / 2.0f, height / 2.0f); //
        super.onDraw(canvas); //
    }

    private int getScaledColor() {
        float scale;
        int baseColor;

        if (navigationOutput.magnitude < MIN_MAGNITUDE_MOVE) {
            scale = PRIMARY_LIGHTEN_MAX;
            baseColor = primaryColor;
        } else if (navigationOutput.magnitude < MIN_MAGNITUDE_ACCENT) {
            scale = PRIMARY_LIGHTEN_MIN;
            baseColor = primaryColor;
        } else if (navigationOutput.magnitude > MAX_MAGNITUDE) {
            scale = 0.0f;
            baseColor = accentColor;
        } else {
            final float factor = Math.min(1.0f, Math.max(0.0f, (navigationOutput.magnitude - MIN_MAGNITUDE_ACCENT) / (MAX_MAGNITUDE - MIN_MAGNITUDE_ACCENT)));
            scale = ACCENT_LIGHTEN_MAX + factor * (ACCENT_LIGHTEN_MIN - ACCENT_LIGHTEN_MAX);
            baseColor = accentColor;
        }

        final int scaledColor = ColorUtil.lightenColor(baseColor, scale);

        //Log.v(TAG, "baseColor:" + Integer.toHexString(baseColor) + ", scale=" + scale + ", scaledColor=" + Integer.toHexString(scaledColor));

        return scaledColor;
    }

    public void setNavigationOutput(final NavigationCalc.NavigationOutput navigationOutput) {

        final float deviceAzimuthOld = this.navigationOutput.deviceAzimuth;

        final float deviceAzimuthChange = MathUtil.normalizeRadians(navigationOutput.deviceAzimuth - deviceAzimuthOld);
        if (Math.abs(deviceAzimuthChange) > Math.PI / 2f)  // more than 90 degrees change, jump, rather than rotate around
            this.navigationOutput.deviceAzimuth = navigationOutput.deviceAzimuth;
        else
            this.navigationOutput.deviceAzimuth = MathUtil.normalizeRadians(deviceAzimuthOld + DEVICE_AZIMUTH_LOW_PASS_FACTOR * deviceAzimuthChange);

        final float deviceAzimuthActualChange = MathUtil.normalizeRadians(this.navigationOutput.deviceAzimuth - deviceAzimuthOld);

        // Require a slight tilt to change angle - when almost flat, keep angle:
        if (navigationOutput.magnitude >= MIN_MAGNITUDE_MOVE) {

            final float deviceAngleChange = MathUtil.normalizeRadians(navigationOutput.deviceAngle - this.navigationOutput.deviceAngle);
            if (Math.abs(deviceAngleChange) > Math.PI / 2f)  // more than 90 degrees change, jump, rather than rotate around
                this.navigationOutput.deviceAngle = navigationOutput.deviceAngle;
            else
                this.navigationOutput.deviceAngle = MathUtil.normalizeRadians(this.navigationOutput.deviceAngle + DEVICE_ANGLE_LOW_PASS_FACTOR * deviceAngleChange);
//            final float deviceAngleChange = MathUtil.normalizeRadians(navigationOutput.deviceAngle - this.navigationOutput.deviceAngle);
//            if (Math.abs(deviceAngleChange) > Math.PI / 2f)  // more than 90 degrees change, jump, rather than rotate around
//                this.navigationOutput.deviceAngle = navigationOutput.deviceAngle;
//            else
//                this.navigationOutput.deviceAngle = MathUtil.normalizeRadians(this.navigationOutput.deviceAngle + DEVICE_ANGLE_LOW_PASS_FACTOR * deviceAngleChange);
        } else {
            // Keep this.navigationOutput.combinedAzimuth constant.
            this.navigationOutput.deviceAngle = MathUtil.normalizeRadians(this.navigationOutput.deviceAngle - deviceAzimuthActualChange);
        }

        final float magnitudeChange = navigationOutput.magnitude - this.navigationOutput.magnitude;
        this.navigationOutput.magnitude += MAGNITUDE_LOW_PASS_FACTOR * magnitudeChange;
        this.invalidate(); // request to be redrawn
    }
}
