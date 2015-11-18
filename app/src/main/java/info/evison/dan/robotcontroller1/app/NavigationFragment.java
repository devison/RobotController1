package info.evison.dan.robotcontroller1.app;

import android.app.Fragment;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import info.evison.dan.robotcontroller1.R;
import info.evison.dan.robotcontroller1.model.NavigationCalc;
import info.evison.dan.robotcontroller1.view.CompassPointer;

public class NavigationFragment extends Fragment implements SensorEventListener {

    private static final String TAG = NavigationFragment.class.getSimpleName();

    // Working area so new objects don't have to be allocated on heap during event handling:
    private final float[] mRotationMatrix = new float[16];
    private final float[] mOrientationVector = new float[3];
    private final NavigationCalc.NavigationInput navigationInput = new NavigationCalc.NavigationInput();
    private final NavigationCalc.NavigationOutput navigationOutput = new NavigationCalc.NavigationOutput();

    private SensorManager mSensorManager;
    private Sensor mSensor;
    private TextView azimuthTextView;
    private TextView pitchTextView;
    private TextView rollTextView;
    private TextView magnitudeTextView;
    private TextView angleTextView;
    private TextView headingTextView;
    private CompassPointer compassPointer;

    public NavigationFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.navigation_fragment, container, false);

        super.onCreate(savedInstanceState);

        mSensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);

        azimuthTextView = (TextView) view.findViewById(R.id.azimuthTextView);
        pitchTextView = (TextView) view.findViewById(R.id.pitchTextView);
        rollTextView = (TextView) view.findViewById(R.id.rollTextView);
        magnitudeTextView = (TextView) view.findViewById(R.id.magnitudeTextView);
        angleTextView = (TextView) view.findViewById(R.id.deviceAngleTextView);
        headingTextView = (TextView) view.findViewById(R.id.combinedAzimuthTextView);
        compassPointer = (CompassPointer) view.findViewById(R.id.compassPointerView);

        return view;
    }

    public void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_UI);
    }

    public void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    public void onSensorChanged(SensorEvent event) {

        if (event.sensor.getType() == Sensor.TYPE_ROTATION_VECTOR) {

            fillNavigationInput(event, navigationInput);

            NavigationCalc.calc(navigationInput, navigationOutput);

            azimuthTextView.setText(String.format("%.1f", navigationInput.azimuthDegrees()));
            pitchTextView.setText(String.format("%.1f", navigationInput.pitchDegrees()));
            rollTextView.setText(String.format("%.1f", navigationInput.rollDegrees()));
            magnitudeTextView.setText(String.format("%.1f", navigationOutput.magnitude * 100));
            angleTextView.setText(String.format("%.1f", navigationOutput.deviceAngleDegrees()));
            headingTextView.setText(String.format("%.1f", navigationOutput.combinedAzimuthDegrees()));

            compassPointer.setNavigationOutput(navigationOutput);
        }
    }

    private void fillNavigationInput(SensorEvent event, NavigationCalc.NavigationInput navigationInput) {

        SensorManager.getRotationMatrixFromVector(mRotationMatrix, event.values);
        SensorManager.getOrientation(mRotationMatrix, mOrientationVector);

        navigationInput.azimuth = mOrientationVector[0];
        navigationInput.pitch = mOrientationVector[1];
        navigationInput.roll = mOrientationVector[2];
    }
}