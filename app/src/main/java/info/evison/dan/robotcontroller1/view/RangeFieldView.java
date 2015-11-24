package info.evison.dan.robotcontroller1.view;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.Observable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import info.evison.dan.robotcontroller1.R;
import info.evison.dan.robotcontroller1.databinding.RangeFieldViewBinding;
import info.evison.dan.robotcontroller1.model.RangeFieldModel;

public class RangeFieldView extends LinearLayout {

    private static final String TAG = RangeFieldView.class.getSimpleName();

    protected RangeFieldViewBinding _binding;

    protected AnimatorSet _fadeAnimator;

    public RangeFieldView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
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
        _binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.range_field_view, this, true);
        _fadeAnimator = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.animator.fade_alpha_slowly2);
        _fadeAnimator.setTarget(_binding.rightIndicator);
    }

    public void bind(RangeFieldModel model) {
        _binding.setModel(model);

        model.value.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                Log.v(TAG, "in onPropertyChanged handler");
                _fadeAnimator.cancel();
                _fadeAnimator.start();
            }
        });
    }
}
