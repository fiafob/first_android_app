package com.example.projectkal;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;


public class MyDraw extends View {
    Paint paint = new Paint();
    int blue = Color.BLUE;
    int myTransparentBlue = Color.argb(127, 0, 0, 255);
    int ballColor = getResources().getColor(R.color.ballColor); // res/values/colors

    // позволяет получить целочисленное значение из шестнадцатеричной формы
    int pColor = Color.parseColor("#FF0000FF");



    public MyDraw (Context context){
        super(context);

    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        canvas.drawPaint(paint);

        // Включаем антиальясинг
        paint.setAntiAlias(true);
        paint.setColor(Color.argb(127,0,0,255));
        // Полупрозрачный синий круг радиусом 100 пикселей в центре экрана
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, 100, paint);

        // Синий прямоугольник вверху экрана
        paint.setColor(Color.BLUE);
        canvas.drawRect(0, 0, getWidth(),200, paint);

        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(30);
        canvas.drawText("Samsung IT school", 50, 100, paint);

        // текст под углом
        float rotate_center_x = 200; //центр поворота холста по оси X
        float rotate_center_y = 200; // центр поворота холста по оси Y
        float rotate_angle = 45; //угол поворота

        canvas.rotate(-rotate_angle, rotate_center_x, rotate_center_y);


        paint.setColor(Color.BLACK);
        paint.setTextSize(40);

        canvas.drawText("Samsung IT school",0,450,paint);

        // возвращаем холст на прежний угол
        canvas.rotate(rotate_angle, rotate_center_x, rotate_center_y);

        // Выводим изображение логотипа Samsung на экран в правом нижнем углу экрана
        Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.gg);
        int xx = canvas.getWidth(), yy = canvas.getHeight();
        canvas.drawBitmap(image, xx - image.getWidth(), yy - image.getHeight(), paint);
    }
}
