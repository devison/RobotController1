package info.evison.dan.robotcontroller1.model;

/**
 * For some reason, ObservableBoolean didn't work, though it's identical!
 */

import android.databinding.BaseObservable;


public class BindableBoolean extends BaseObservable {
    boolean value;

    public BindableBoolean(boolean value) {
        this.value = value;
    }

    public boolean get() {
        return value;
    }

    public void set(boolean value) {
        if (this.value != value) {
            this.value = value;
            notifyChange();
        }
    }
}
