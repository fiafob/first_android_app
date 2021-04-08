package com.example.projectkal;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.view.View;
import android.widget.Toast;

import static android.content.Context.SENSOR_SERVICE;
import static androidx.core.content.ContextCompat.getSystemService;

public class MyAxel extends View {
    private SensorManager sensorManager;
    Paint paint = new Paint();


    public MyAxel (Context context){
        super(context);
//        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor defaultGyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);


    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);



        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(30);

    }
}
