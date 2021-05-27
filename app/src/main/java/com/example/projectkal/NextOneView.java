package com.example.projectkal;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

public class NextOneView extends SurfaceView implements SurfaceHolder.Callback {
    public NextOneView(Context context) {
        super(context);

        getHolder().addCallback( this );

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder){
        Canvas canvas = getHolder().lockCanvas();
        draw(canvas);
        getHolder().unlockCanvasAndPost(canvas);
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

    }
}
