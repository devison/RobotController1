package info.evison.dan.robotcontroller1.model;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

//public class BooleanFieldModel implements FieldModel {
//
//    public ObservableField<String> name;
//    public ObservableBoolean value;
//
//    public BooleanFieldModel() {
//        this.name = new ObservableField<String>("");
//        this.value = new ObservableBoolean(false);
//    }
//
//    public BooleanFieldModel(String name, boolean value) {
//        this.name = new ObservableField<String>(name);
//        this.value = new ObservableBoolean(value);
//    }
//}
public class BooleanFieldModel implements FieldModel {

    public BindableString name;
    public BindableBoolean value;

    public BooleanFieldModel() {
        this.name = new BindableString("");
        this.value = new BindableBoolean(false);
    }

    public BooleanFieldModel(String name, boolean value) {
        this.name = new BindableString(name);
        this.value = new BindableBoolean(value);
    }
}
