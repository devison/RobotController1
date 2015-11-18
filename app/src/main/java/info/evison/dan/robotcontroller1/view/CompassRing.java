package info.evison.dan.robotcontroller1.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import info.evison.dan.robotcontroller1.util.ColorUtil;

public class CompassRing extends View {

    private static final String TAG = CompassRing.class.getSimpleName();

    final static float PRIMARY_LIGHTEN = 0.6f;
    final static float THICKNESS = 46f;
    int primaryColor;

    protected Paint _paint;

    public CompassRing(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        primaryColor = Color.parseColor("#607D8B");
        _paint = new Paint();
        _paint.setColor(ColorUtil.lightenColor(primaryColor, PRIMARY_LIGHTEN));
        _paint.setStyle(Paint.Style.STROKE);
        _paint.setStrokeWidth(THICKNESS);
    }

    // Called when component is to be drawn
    @Override
    public void onDraw(Canvas canvas) { //
        int height = this.getHeight();
        int width = this.getWidth();
        float radius = Math.min(height / 2.0f, width / 2.0f) - (THICKNESS / 2f) - 0.5f;

        canvas.drawCircle(width / 2.0f, height / 2.0f, radius, _paint);

        super.onDraw(canvas); //
    }
}
