package info.evison.dan.robotcontroller1.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

import info.evison.dan.robotcontroller1.R;
import info.evison.dan.robotcontroller1.databinding.ChoiceFieldViewBinding;
import info.evison.dan.robotcontroller1.model.ChoiceFieldModel;

public class ChoiceFieldView extends LinearLayout implements View.OnClickListener {

    private static final String TAG = ChoiceFieldView.class.getSimpleName();

    protected ChoiceFieldViewBinding _binding;

    public ChoiceFieldView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
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

    private void init(Context context) {
        _binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.choice_field_view, this, true);
        _binding.topLayout.setOnClickListener(this);
    }

    void bind(ChoiceFieldModel model) {
        _binding.setModel(model);

        // Note: the choices are not dynamic - they won't change from the view or the model
        CharSequence[] choices = model.choices;
        if (choices == null) choices = new CharSequence[0];
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<>(getContext(), R.layout.simple_spinner_item, choices);
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        _binding.spinner.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        _binding.spinner.performClick();
    }
}
