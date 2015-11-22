package info.evison.dan.robotcontroller1.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import info.evison.dan.robotcontroller1.R;

public class CollapsibleView extends FrameLayout {

    private static final String TAG = CollapsibleView.class.getSimpleName();

    protected View _layout;
    protected View _header;
    protected FrameLayout _content;
    protected int _contentHeight = Integer.MIN_VALUE;
    protected ImageView _eExpandImageView;
    protected TextView _headingTextView;

    public CollapsibleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        _layout = inflate(context, R.layout.collapsible_view, null);
        _header = _layout.findViewById(R.id.collapsible_header);
        _content = (FrameLayout) _layout.findViewById(R.id.collapsible_content);
        _header.setOnClickListener(getHeaderOnClickListener());
        _eExpandImageView = (ImageView) _layout.findViewById(R.id.expand_image);
        _headingTextView = (TextView) _header.findViewById(R.id.heading_text);

        final TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.CollapsibleView,
                0, 0);

        try {
            setHeadingText(a.getString(R.styleable.CollapsibleView_heading_text));
        } finally {
            a.recycle();
        }
    }

    public String getHeadingText() {
        return _headingTextView.getText().toString();
    }

    public void setHeadingText(final String headingText) {
        _headingTextView.setText(headingText == null ? "" : headingText);
    }

    @Override
    protected void onFinishInflate() {

        super.onFinishInflate();

        int numChildren = getChildCount();
        if (numChildren != 1)
            throw new RuntimeException("CollapsibleView requires one child");

        View content = getChildAt(0);
        this.removeAllViews();

        _content.addView(content);

        this.addView(_layout);
        _layout.requestLayout();
        invalidate();
    }

    private View.OnClickListener getHeaderOnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (_contentHeight == Integer.MIN_VALUE)
                    _contentHeight = _content.getMeasuredHeight();

                int current, target;

                AnimatorListenerAdapter onEnd = null;

                if (_content.getVisibility() == View.VISIBLE) {
                    _contentHeight = _content.getMeasuredHeight();  // Safer to do it again?
                    current = 0;
                    target = -_contentHeight;
                    Log.v(TAG, "1. cardHeight=" + _contentHeight + ", mContentHeight=" + _contentHeight + ", current=" + current + ", target=" + target);

                    onEnd = new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animator) {
                            Log.v(TAG, "Details are GONE!");
                            _content.setVisibility(View.GONE);
                        }
                    };

                    _eExpandImageView.setImageResource(R.drawable.ic_expand_more);
                } else {
                    current = -_contentHeight;
                    target = 0;
                    Log.v(TAG, "2. cardHeight=" + _contentHeight + ", mContentHeight=" + _contentHeight + ", current=" + current + ", target=" + target);

                    _content.setVisibility(View.VISIBLE);
                    _eExpandImageView.setImageResource(R.drawable.ic_expand_less);
                }
                Log.v(TAG, "3. current=" + current + ", target=" + target);
                ValueAnimator anim = ValueAnimator.ofInt(current, target);
                anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator a) {
                        int val = (Integer) a.getAnimatedValue();
                        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) _content.getLayoutParams();
                        layoutParams.topMargin = val;
                        _content.setLayoutParams(layoutParams);
                    }
                });
                anim.setDuration(300);

                if (onEnd != null)
                    anim.addListener(onEnd);

                anim.start();
            }
        };
    }
}
