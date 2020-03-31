package com.example.classroomtracking;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;


public class TraceDrawer {
    public TraceDrawer(ImageView aView, MainActivity mainActivity) {
        imageView = aView;
        this.mainActivity = mainActivity;
        bitmap = Bitmap.createBitmap(1000, 1000, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
        canvas.drawColor(Color.WHITE);
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(4);
        paint.setAntiAlias(true);
        canvas.drawCircle(60,60,50,paint);
        canvas.drawCircle(940,60,50,paint);
    }

    public void drawNew(final Coordinate aCoordinate) {
        mainActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (lastCoordinate != null) {
                    canvas.drawLine((float) lastCoordinate.col * 10, (float) lastCoordinate.row * 10,
                            (float) aCoordinate.col * 10, (float) aCoordinate.row * 10, paint);
                    imageView.setImageBitmap(bitmap);
                }
                lastCoordinate = new Coordinate(aCoordinate);
            }
        });

    }
    public void clear(){
        bitmap = Bitmap.createBitmap(1000, 1000, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
        canvas.drawColor(Color.WHITE);
        imageView.setImageBitmap(bitmap);
        canvas.drawCircle(60,60,50,paint);
        canvas.drawCircle(940,60,50,paint);
    }
    private ImageView imageView;
    private Coordinate lastCoordinate;
    private Bitmap bitmap;
    private Canvas canvas;
    private Paint paint;
    private MainActivity mainActivity;
}
