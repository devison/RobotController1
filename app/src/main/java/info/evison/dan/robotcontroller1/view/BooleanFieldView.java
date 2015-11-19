package info.evison.dan.robotcontroller1.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableBoolean;
import android.databinding.ViewDataBinding;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;

import info.evison.dan.robotcontroller1.R;
import info.evison.dan.robotcontroller1.databinding.BooleanFieldViewBinding;
import info.evison.dan.robotcontroller1.model.BooleanFieldModel;

public class BooleanFieldView extends LinearLayout {

    private static final String TAG = BooleanFieldView.class.getSimpleName();

    protected BooleanFieldViewBinding _binding;

    public BooleanFieldView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
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

    private void init(Context context) {
        _binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.boolean_field_view, this, true);
    }

    public void bind(BooleanFieldModel model) {
        _binding.setModel(model);
    }
}
