package info.evison.dan.robotcontroller1.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import info.evison.dan.robotcontroller1.R;
import info.evison.dan.robotcontroller1.databinding.FieldGroupViewBinding;
import info.evison.dan.robotcontroller1.model.FieldGroupModel;
import info.evison.dan.robotcontroller1.model.FieldModel;

public class FieldGroupView extends LinearLayout {

    private static final String TAG = FieldGroupView.class.getSimpleName();

    protected FieldGroupViewBinding _binding;

//    protected CardView _cardView;
//    protected CollapsibleView _collapsibleView;
    protected LinearLayout _innerLayout;

    // This constructor is called when inflating from xml.
    // It relies on onFinishInflate() to finish the job.
    public FieldGroupView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, null, true);
//        setAttrs(context, attrs);
    }

    // This constructor is called from code.
    // Typically, setChildren will be called too.
    public FieldGroupView(Context context) {
        super(context);
        init(context, null, false);
    }

    // This constructor is called from code, when another ViewGroup will be managing layout.
    // Typically, setChildren will be called too.
    public FieldGroupView(Context context, ViewGroup viewGroup) {
        super(context);
        init(context, viewGroup, false);
    }

//    protected void setAttrs(Context context, AttributeSet attrs) {
//        final TypedArray a = context.getTheme().obtainStyledAttributes(
//                attrs,
//                R.styleable.FieldGroupView,
//                0, 0);
//
//        try {
//            setGroupName(a.getString(R.styleable.FieldGroupView_heading_text));
//        } finally {
//            a.recycle();
//        }
//    }

    // This viewGroup is used to influence the layout, but isn't attached. Can be null.

    protected void init(Context context, ViewGroup viewGroup, boolean fromXML) {
        _binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.field_group_view, viewGroup, false);

//        _cardView = (CardView) LayoutInflater.from(context).inflate(R.layout.field_group_view, viewGroup, false);
//        _collapsibleView = (CollapsibleView) _cardView.findViewById(R.id.field_card_view_collapsible_view);
       _innerLayout = (LinearLayout) _binding.cardView.findViewById(R.id.field_card_view_layout);

        if (!fromXML) // otherwise done in onFinishInflate
            this.addView(_binding.cardView);
    }

    public void bind(FieldGroupModel groupModel) {

        Log.v(TAG, "Here I am in FieldCardView.bind: " + this);
        _binding.setModel(groupModel);

//        setGroupName(groupModel.getGroupName());

        List<FieldModel> fieldModels = groupModel.getFieldModels();
        List<View> fieldViews = new ArrayList<View>(fieldModels.size());
        for (FieldModel model : fieldModels)
            fieldViews.add(FieldViewFactory.createView(getContext(), model));

        setFieldViews(fieldViews);
    }

    // This function is called automatically after inflating from xml.
    // The xml may define other children which initially are attached to this,
    // but need to be moved into the right place inside the cardview that we
    // prepared in the constructor.
    @Override
    protected void onFinishInflate() {

        super.onFinishInflate();

        Log.v(TAG, "Here I am in FieldCardView.onFinishInflate: " + this);

        final int n = getChildCount();
        final List<View> childrenViews = new ArrayList<View>(getChildCount());

        for (int i = 0; i < n; ++i)
            childrenViews.add(getChildAt(i));

        this.removeAllViews();
        this.addView(_binding.cardView);

        setFieldViews(childrenViews);
    }

//    public void setGroupName(String headingText) {
//        _collapsibleView.setHeadingText(headingText == null ? "" : headingText);
//    }

    void setFieldViews(List<View> childrenViews) {

        _innerLayout.removeAllViews();

        for (int i=0; i < childrenViews.size(); ++i)
            _innerLayout.addView(childrenViews.get(i));

        _binding.cardView.requestLayout();
        invalidate();
   }
}
