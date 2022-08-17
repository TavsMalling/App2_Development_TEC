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
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyGraphics myGraphics = new MyGraphics(this);

        LinearLayout linGraphicsLayout = findViewById(R.id.linGraphicsLayout);

        linGraphicsLayout.addView(myGraphics, 0);

        Thread graphicsThread = new Thread(myGraphics);
        graphicsThread.start();
    }

    private class MyGraphics extends View implements Runnable
    {
        Paint paint = new Paint();
        int viewWidth;
        int viewHeight;

        int xPos;
        int yPos = 60;
        int radius = 50;

        int xFinger;
        int yFinger;
        boolean moving = false;

        double speedX = 0;
        double speedY = 0.1;
        double gravity = 0.5;
        double gravitySpeed = 0;


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
                simulateGravity();
                postInvalidate();
                SystemClock.sleep(15);
            }
        }

        @Override
        protected void onSizeChanged(int w, int h, int oldw, int oldh) {
            super.onSizeChanged(w, h, oldw, oldh);
            viewWidth = w;
            viewHeight = h;
            xPos = w/2;
        }

        @Override
        protected void onDraw(Canvas canvas) {

            paint.setColor(0xFFCCCD00);

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


            if(event.getPointerCount() == 2)
            {
                int x1 = (int)event.getX(0);
                int y1 = (int)event.getY(0);
                int x2 = (int)event.getX(1);
                int y2 = (int)event.getX(1);

                radius = (int)Math.sqrt(Math.pow(x1-x2, 2) + Math.pow(y1-y2,2));
                invalidate();
                return true;
            }
            else if(event.getAction() == MotionEvent.ACTION_DOWN)
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

                    hitBoundariesVertical();
                    hitBoundariesHorizontal();

                    invalidate();
                }
            }
            else if (event.getAction() == MotionEvent.ACTION_UP)
            {

                moving = false;
            }

            return true;
        }

        public void simulateGravity()
        {
            gravitySpeed += gravity;
            xPos += speedX;
            yPos += speedY + gravitySpeed;

            hitBoundariesVertical();
            hitBoundariesHorizontal();
        }

        public void hitBoundariesVertical()
        {
            int top = radius;
            int bottom = viewHeight - radius;

            if (yPos < top )
            {
                yPos = top;
            }

            if (yPos > bottom )
            {
                yPos = bottom;
            }
        }

        public void hitBoundariesHorizontal()
        {
            int left = radius;
            int right = viewWidth - radius;

            if (xPos < left)
            {
                xPos = left;
            }

            if (xPos > right)
            {
                xPos = right;
            }
        }
    }
}