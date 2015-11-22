package info.evison.dan.robotcontroller1.model;

import android.databinding.BaseObservable;

public class BindableDouble extends BaseObservable {
    double value;

    public BindableDouble(double value) {
        this.value = value;
    }

    public double get() {
        return value;
    }

    public void set(double value) {
        if (this.value != value) {
            this.value = value;
            notifyChange();
        }
    }
}
