package info.evison.dan.robotcontroller1.view;

import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.databinding.BindingConversion;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;

import info.evison.dan.robotcontroller1.R;
import info.evison.dan.robotcontroller1.model.BindableBoolean;
import info.evison.dan.robotcontroller1.model.BindableInt;
import info.evison.dan.robotcontroller1.model.BindableString;

public class BindingUtil extends BaseObservable {

    @BindingConversion
    public static String convertBindableToString(BindableString bindableString) {
        return bindableString.get();
    }

    @BindingConversion
    public static boolean convertBindableToBoolean(BindableBoolean bindableBoolean) {
        return bindableBoolean.get();
    }

    @BindingConversion
    public static int convertBindableToInt(BindableInt bindableInt) {
        return bindableInt.get();
    }

    @BindingAdapter("app:binding")
    public static void bindChecked(Switch view,
                                   final BindableBoolean bindableBoolean) {
        if (view.getTag(R.id.binded) == null) {
            view.setTag(R.id.binded, true);
            view.setOnCheckedChangeListener(new Switch.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    bindableBoolean.set(isChecked);
                }
            });
        }

        boolean newValue = bindableBoolean.get();
        if (!view.isChecked() == newValue)
            view.setChecked(newValue);
    }

    @BindingAdapter("app:binding")
    public static void bindSpinner(Spinner view,
                                   final BindableInt bindableInt) {
        if (view.getTag(R.id.binded) == null) {
            view.setTag(R.id.binded, true);
            view.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    bindableInt.set(position);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    bindableInt.set(0);  // TODO: what should this be set to?
                }
            });
        }

        int newValue = bindableInt.get();
        if (view.getSelectedItemPosition() != newValue)
            view.setSelection(newValue);
    }

//    @BindingAdapter("app:binding")
//    public static void bindSpinnerChoices(Spinner view,
//                                   final BindableStringArray bindableStringArray) {
//        String[] newValues = bindableStringArray.get();
//        ArrayAdapter<CharSequence> adapter = (ArrayAdapter<CharSequence>) view.getAdapter();
//        adapter.getItem()
//        if (view.getAdapter().getSelectedItemPosition() != newValue)
//            view.setSelection(newValue);
//    }

}
