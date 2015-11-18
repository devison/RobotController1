package info.evison.dan.robotcontroller1.view;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import info.evison.dan.robotcontroller1.*;
import info.evison.dan.robotcontroller1.model.NavigationCalc;

public class NavigationFieldView extends LinearLayout implements SensorEventListener {

    private static final String TAG = NavigationFieldView.class.getSimpleName();

    // Working area so new objects don't have to be allocated on heap during event handling:
    protected final float[] _rRotationMatrix = new float[16];
    protected final float[] _orientationVector = new float[3];
    protected final NavigationCalc.NavigationInput _navigationInput = new NavigationCalc.NavigationInput();
    protected final NavigationCalc.NavigationOutput _navigationOutput = new NavigationCalc.NavigationOutput();

    protected SensorManager _sensorManager;
    protected Sensor _sensor;
    protected TextView _azimuthTextView;
    protected TextView _pitchTextView;
    protected TextView _rollTextView;
    protected TextView _magnitudeTextView;
    protected TextView _angleTextView;
    protected TextView _headingTextView;
    protected CompassPointer _compassPointer;

    public NavigationFieldView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.navigation_field_view, this);

        _sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        _sensor = _sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);

        _azimuthTextView = (TextView) findViewById(R.id.azimuthTextView);
        _pitchTextView = (TextView) findViewById(R.id.pitchTextView);
        _rollTextView = (TextView) findViewById(R.id.rollTextView);
        _magnitudeTextView = (TextView) findViewById(R.id.magnitudeTextView);
        _angleTextView = (TextView) findViewById(R.id.deviceAngleTextView);
        _headingTextView = (TextView) findViewById(R.id.combinedAzimuthTextView);
        _compassPointer = (CompassPointer) findViewById(R.id.compassPointerView);

        // TODO: should on onResume ????
        _sensorManager.registerListener(this, _sensor, SensorManager.SENSOR_DELAY_UI);

        // TODO: should on onPause ????
        //        mSensorManager.unregisterListener(this);

    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    public void onSensorChanged(SensorEvent event) {

        if (event.sensor.getType() == Sensor.TYPE_ROTATION_VECTOR) {

            fillNavigationInput(event, _navigationInput);

            NavigationCalc.calc(_navigationInput, _navigationOutput);

            _azimuthTextView.setText(String.format("%.1f", _navigationInput.azimuthDegrees()));
            _pitchTextView.setText(String.format("%.1f", _navigationInput.pitchDegrees()));
            _rollTextView.setText(String.format("%.1f", _navigationInput.rollDegrees()));
            _magnitudeTextView.setText(String.format("%.1f", _navigationOutput.magnitude * 100));
            _angleTextView.setText(String.format("%.1f", _navigationOutput.deviceAngleDegrees()));
            _headingTextView.setText(String.format("%.1f", _navigationOutput.combinedAzimuthDegrees()));

            _compassPointer.setNavigationOutput(_navigationOutput);
        }
    }

    protected void fillNavigationInput(SensorEvent event, NavigationCalc.NavigationInput navigationInput) {

        SensorManager.getRotationMatrixFromVector(_rRotationMatrix, event.values);
        SensorManager.getOrientation(_rRotationMatrix, _orientationVector);

        navigationInput.azimuth = _orientationVector[0];
        navigationInput.pitch = _orientationVector[1];
        navigationInput.roll = _orientationVector[2];
    }
}