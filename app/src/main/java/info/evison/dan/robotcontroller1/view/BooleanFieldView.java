package info.evison.dan.robotcontroller1.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.Switch;

import info.evison.dan.robotcontroller1.R;
import info.evison.dan.robotcontroller1.model.BooleanFieldModel;

public class BooleanFieldView extends LinearLayout {

    private static final String TAG = BooleanFieldView.class.getSimpleName();

    protected Switch _switch;

    public BooleanFieldView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.BooleanFieldView,
                0, 0);

        try {
            setName(a.getString(R.styleable.BooleanFieldView_name_text));
        } finally {
            a.recycle();
        }
    }

    public BooleanFieldView(Context context) {
        super(context);
        init(context);
    }

    public BooleanFieldView(Context context, BooleanFieldModel model) {
        super(context);
        init(context);
        bind(model);
    }

    public void setName(String name) {
        _switch.setText(name == null ? "" : name);
    }

    public void setValue(boolean value) {
        _switch.setChecked(value);
    }

    public void bind(BooleanFieldModel model) {
        setName(model.name);
        setValue(model.value);
    }

    private void init(Context context) {
        inflate(context, R.layout.boolean_field_view, this);
        _switch = (Switch) findViewById(R.id.boolean_selector_switch);
    }
}
