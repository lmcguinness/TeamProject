package com.example.societyslam.societyslam.Util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.provider.Settings;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Aoife Brown on 21/11/2016.
 */

public class Painter {

    private Canvas canvas;
    private Paint paint;
    private Rect srcRect;
    private Rect dstRect;
    private RectF dstRectF;

    public Painter(Canvas canvas) {
        this.canvas = canvas;
        paint = new Paint();
        srcRect = new Rect();
        dstRect = new Rect();
        dstRectF = new RectF();
    }

    public void setColor(int color) {
        paint.setColor(color);
    }

    public void setFont(Typeface typeface, float textSize) {
        paint.setTypeface(typeface);
        paint.setTextSize(textSize);
    }

    public void drawString(String str, int x, int y) {
        canvas.drawText(str,x,y,paint);
    }

    public void fillRect(int x, int y, int width, int height) {
        dstRect.set(x,y,x+width, y+height);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(dstRect, paint);
    }

    public void drawImage(Bitmap bitmap, int x, int y) {
        canvas.drawBitmap(bitmap,x,y,paint);
    }


    public void drawImage(Bitmap bitmap, int x, int y, int width, int height) {
        try {
            srcRect.set(0, 0, (bitmap != null ? bitmap.getWidth() : 205), (bitmap != null ? bitmap.getHeight() : 268));
            dstRect.set(x, y, x + width, y + height);
            canvas.drawBitmap(bitmap, srcRect, dstRect, paint);
        } catch (Exception ex) {
            System.out.println(bitmap);
        }
    }

}
