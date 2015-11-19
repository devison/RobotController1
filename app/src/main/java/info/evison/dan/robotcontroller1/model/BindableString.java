package info.evison.dan.robotcontroller1.model;

import android.databinding.BaseObservable;

import java.util.Objects;

/**
 * For some reason, ObservableField<String> didn't work, though it's identical!
 */

public class BindableString extends BaseObservable {
    String value;

    public BindableString(String value) {
        this.value = value;
    }

    public String get() {
        return value != null ? value : "";
    }

    public void set(String value) {
        if (!Objects.equals(this.value, value)) {
            this.value = value;
            notifyChange();
        }
    }

    public boolean isEmpty() {
        return value == null || value.isEmpty();
    }
}