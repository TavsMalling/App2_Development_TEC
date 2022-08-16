package com.example.app2_development_tec;

import androidx.appcompat.app.AppCompatActivity;


import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyGraphics myGraphics = new MyGraphics(this);

        FrameLayout frmGraphicsLayout = findViewById(R.id.frmGraphicsLayout);

        frmGraphicsLayout.addView(myGraphics, 0);

        Thread graphicsThread = new Thread(myGraphics);
        graphicsThread.start();
    }

    private class MyGraphics extends View implements Runnable
    {
        Paint paint = new Paint();
        int viewWidth;

        int xPos;
        int yPos = 60;
        int radius = 50;

        int xFinger;
        int yFinger;
        boolean moving = false;

        public MyGraphics(MainActivity activity)
        {
            super(activity);

            Display display = activity.getWindowManager().getDefaultDisplay();

            Point size = new Point();

            display.getSize(size);

            xPos = size.x/2;

        }

        @Override
        public void run() {
            while (!moving)
            {
                postInvalidate();
                SystemClock.sleep(100);
            }
        }

        @Override
        protected void onSizeChanged(int w, int h, int oldw, int oldh) {
            super.onSizeChanged(w, h, oldw, oldh);
            viewWidth = w;
            xPos = w/2;
        }

        @Override
        protected void onDraw(Canvas canvas) {

            paint.setColor(0xFF32CD32);

            canvas.drawCircle(xPos, yPos, radius, paint);
            paint.setColor(0xFF32CD32);

            paint.setTextSize(30);

            paint.setTextAlign(Paint.Align.CENTER);

            canvas.drawText("Written in graphics", viewWidth/2, 100, paint);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {

            int xNew = (int)event.getX();
            int yNew = (int)event.getY();

            if(event.getAction() == MotionEvent.ACTION_DOWN)
            {

                if(xNew < (xPos + radius) && xNew > (xPos - radius) && yNew < (yPos + radius) && yNew > (yPos - radius))
                {
                    moving = true;
                    xFinger = xNew;
                    yFinger = yNew;
                }
            }
            else if (event.getAction() == MotionEvent.ACTION_MOVE)
            {
                if (moving) {


                    xPos += xNew - xFinger;
                    yPos += yNew - yFinger;

                    xFinger = xNew;
                    yFinger = yNew;

                    invalidate();
                }
            }
            else if (event.getAction() == MotionEvent.ACTION_UP)
            {
                moving = false;
            }

            return true;
        }


    }
}