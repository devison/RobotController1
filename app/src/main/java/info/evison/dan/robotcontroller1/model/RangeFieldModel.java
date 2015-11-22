package info.evison.dan.robotcontroller1.model;

import android.databinding.Bindable;

public class RangeFieldModel implements FieldModel {

    public BindableString name;
    public BindableInt value;
    public BindableInt max;

    public RangeFieldModel(String name, int value, int max) {
        this.name = new BindableString(name);
        this.value = new BindableInt(value);
        this.max = new BindableInt(max);
    }
}
