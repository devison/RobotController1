package info.evison.dan.robotcontroller1.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import info.evison.dan.robotcontroller1.R;
import info.evison.dan.robotcontroller1.model.ChoiceFieldModel;

public class ChoiceFieldView extends LinearLayout implements View.OnClickListener {

    private static final String TAG = ChoiceFieldView.class.getSimpleName();

    protected TextView _nameView;
    protected Spinner _spinner;

    public ChoiceFieldView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.ChoiceFieldView,
                0, 0);

        try {
            String name = a.getString(R.styleable.ChoiceFieldView_name_text);
            setName(name);

            CharSequence[] entries = a.getTextArray(R.styleable.ChoiceFieldView_android_entries);
            setChoices(entries);

        } finally {
            a.recycle();
        }
    }

    public ChoiceFieldView(Context context) {
        super(context);
        init(context);
    }

    public ChoiceFieldView(Context context, ChoiceFieldModel model) {
        super(context);
        init(context);
        bind(model);
    }

    void bind(ChoiceFieldModel model) {
        setName(model.name);
        setChoices(model.choices);
    }

    private void setName(String name) {
        _nameView.setText(name == null ? "" : name);
    }

    public void setChoices(CharSequence[] entries) {
        if (entries == null) entries = new CharSequence[0];
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(getContext(), R.layout.simple_spinner_item, entries);
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        _spinner.setAdapter(adapter);
    }

    private void init(Context context) {
        inflate(context, R.layout.choice_field_view, this);
        _nameView = (TextView) findViewById(R.id.choice_selector_name);
        _spinner = (Spinner) findViewById(R.id.choice_selector_spinner);
        LinearLayout layout = (LinearLayout) findViewById(R.id.choice_selector_layout);
        layout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        _spinner.performClick();
    }
}
