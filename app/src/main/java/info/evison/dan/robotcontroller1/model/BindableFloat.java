package info.evison.dan.robotcontroller1.model;

import android.databinding.BaseObservable;

public class BindableFloat extends BaseObservable {
    float value;

    public BindableFloat(float value) {
        this.value = value;
    }

    public float get() {
        return value;
    }

    public void set(float value) {
        if (this.value != value) {
            this.value = value;
            notifyChange();
        }
    }
}
