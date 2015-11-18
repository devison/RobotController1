package info.evison.dan.robotcontroller1.view;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class MarginItemDecoration extends RecyclerView.ItemDecoration {

    private final int _top;
    private final int _bottom;
    private final int _left;
    private final int _right;

    public MarginItemDecoration(int margin) {
       _left = _right = _top = _bottom = margin;
    }

    public MarginItemDecoration(int topBottom, int leftRight) {
        _top = _bottom = topBottom;
        _left = _right = leftRight;
    }

    public MarginItemDecoration(int top, int bottom, int left, int right) {
        _top = top;
        _bottom = bottom;
        _left = left;
        _right = right;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        outRect.top = _top;
        outRect.bottom = _bottom;
        outRect.left = _left;
        outRect.right = _right;
    }
}