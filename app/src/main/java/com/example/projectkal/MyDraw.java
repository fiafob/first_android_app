package com.example.projectkal;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.widget.Toast;


public class MyDraw extends View {
    Paint paint = new Paint();
    int blue = Color.BLUE;
    int myTransparentBlue = Color.argb(127, 0, 0, 255);
    int ballColor = getResources().getColor(R.color.ballColor); // res/values/colors
    final static int COLOR = Color.rgb(200, 170, 0);


    int board[][] = new int[15][15];
    int left = 5, top = 5;
    int cell_size = 0;




    // позволяет получить целочисленное значение из шестнадцатеричной формы
    int pColor = Color.parseColor("#FF0000FF");



    public MyDraw (Context context){
        super(context);

        Toast toast = Toast.makeText(context, String.valueOf(COLOR), Toast.LENGTH_LONG);
        toast.show();

    }
    protected void drawBoard(Canvas canvas){
        paint.setColor(COLOR);
        int k = 2;
        int count_y = top;

        for (int[] row : board){
            int count_x = left;
            for (int _ : row){
                for (int shadow_k = 1; shadow_k > -1; shadow_k--){
                    int color = COLOR + 800 * shadow_k;
                    canvas.drawLine(count_x + shadow_k * k, top + shadow_k * k,
                            count_x + k * shadow_k,cell_size * 14 + top, paint);
                    canvas.drawLine(left, count_y + k * shadow_k, cell_size * 14 + left,
                            count_y + k * shadow_k, paint);
                }
                count_x += cell_size;
            }
            count_y += cell_size;
        }
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);



        if (getHeight() < getWidth()) cell_size = getHeight() / 14;
        else cell_size = getWidth() / 14;

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);
        canvas.drawPaint(paint);

        // Включаем антиальясинг
        //paint.setAntiAlias(true);
        drawBoard(canvas);




    }
}
