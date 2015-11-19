package info.evison.dan.robotcontroller1.model;

/**
 */

import android.databinding.BaseObservable;


public class BindableInt extends BaseObservable {
    int value;

    public BindableInt(int value) {
        this.value = value;
    }

    public int get() {
        return value;
    }

    public void set(int value) {
        if (this.value != value) {
            this.value = value;
            notifyChange();
        }
    }
}
