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
 * Created by Aoife Brown
 * This is the Painter class, it is used to control the games graphics
 */

public class Painter {

    private Canvas canvas;
    private Paint paint;
    private Rect srcRect;
    private Rect dstRect;
    private RectF dstRectF;

    /**
     * This constructs a painter object
     * @param canvas - the canvas
     */
    public Painter(Canvas canvas) {
        this.canvas = canvas;
        paint = new Paint();
        srcRect = new Rect();
        dstRect = new Rect();
        dstRectF = new RectF();

    }


    /**
     * This method sets the color
     * @param color - the color being set
     */
    public void setColor(int color) {
        paint.setColor(color);
    }

    /**
     * This method sets the font
     * @param typeface - the font to be set
     * @param textSize - the size of the font
     */
    public void setFont(Typeface typeface, float textSize) {
        paint.setTypeface(typeface);
        paint.setTextSize(textSize);
    }

    /**
     * This method draws a string to the screen
     * @param str - the string to be drawn
     * @param x - the X coordinate of the string
     * @param y - the Y coordinate of the string
     * @param color - the color of the string
     */
    public void drawString(String str, int x, int y, int color) {
        paint.setColor(color);
        canvas.drawText(str,x,y,paint);
    }

    /**
     * This method draws an image to the screen
     * @param bitmap - the image to be drawn
     * @param x - the X coordinate of the image
     * @param y - the Y coordinate of the image
     */
    public void drawImage(Bitmap bitmap, int x, int y) {
        canvas.drawBitmap(bitmap,x,y,paint);
    }

    /**
     * This method draws an image to the screen
     * @param bitmap - the image to be drawn
     * @param x - the x coordinate of the image
     * @param y - the y coordinate of the image
     * @param width - the width of the image
     * @param height - the height of the image
     */

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
