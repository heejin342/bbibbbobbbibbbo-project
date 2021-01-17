package com.example.finalapplicaiton;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class MyGraphicView extends View {


    MainActivity main;
    double x;
    double y;
    public MyGraphicView(Context context) {
        super(context);
    }

    public MyGraphicView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyGraphicView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);
        Paint paint=new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(0);
        paint.setStyle(Paint.Style.FILL);
        Rect rect1=new Rect((int)x,(int)y,(int)x+100,(int)y+100);
        canvas.drawRect(rect1,paint);

    }
}
