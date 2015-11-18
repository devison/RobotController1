package info.evison.dan.robotcontroller1.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import info.evison.dan.robotcontroller1.R;
import info.evison.dan.robotcontroller1.model.RangeFieldModel;

public class RangeFieldView extends LinearLayout implements SeekBar.OnSeekBarChangeListener {

    private static final String TAG = RangeFieldView.class.getSimpleName();

    protected TextView _nameView, _valueView;
    protected SeekBar _seekBar;

    public RangeFieldView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.RangeFieldView,
                0, 0);

        try {
            setName(a.getString(R.styleable.RangeFieldView_name_text));
        } finally {
            a.recycle();
        }
    }

    public RangeFieldView(Context context) {
        super(context);
        init(context);
    }

    public RangeFieldView(Context context, RangeFieldModel model) {
        super(context);
        init(context);
        bind(model);
    }

    private void init(Context context) {
        inflate(context, R.layout.range_field_view, this);
        _nameView = (TextView) findViewById(R.id.range_selector_name);
        _valueView = (TextView) findViewById(R.id.range_selector_value);
        _seekBar = (SeekBar) findViewById(R.id.range_selector_seekbar);
        _seekBar.setOnSeekBarChangeListener(this);
    }

    public void bind(RangeFieldModel model) {
        setName(model.name);
        setValue(model.value);
        setMax(model.max);
    }

    public void setName(String name) {
        _nameView.setText(name == null ? "" : name);
    }

    public void setValue(int value) {
        if (_seekBar.getProgress() != value)
            _seekBar.setProgress(value);

        _valueView.setText(Integer.toString(value));
    }

    public void setMax(int max) {
        if (_seekBar.getMax() != max)
            _seekBar.setMax(max);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        setValue(progress);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
    }
}
