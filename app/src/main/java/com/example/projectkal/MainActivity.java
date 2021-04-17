package com.example.projectkal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button startBtn, pauseBtn, resetBtn;
    private ImageButton rotate, toRight, toDown, toLeft;
    private TextView points, currentLevel;

    private NextPieceView nextPieceView;
    private GameBoard gameBoard;
    private Tetris tetris;
    private boolean pause = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        // Make this activity, full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // Hide the Title bar of this activity screen
//        getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        //setContentView(new MyDraw(this));
//        setContentView(new MyAxel(this));
        setContentView(R.layout.activity_main);


        startBtn = (Button) findViewById(R.id.buttonstart);
        pauseBtn = (Button) findViewById(R.id.buttonpause);
        resetBtn = (Button) findViewById(R.id.buttonreset);

        toRight = (ImageButton) findViewById(R.id.rightButton);
        toLeft = (ImageButton) findViewById(R.id.leftButton);
        toDown = (ImageButton) findViewById(R.id.downButton);
        rotate = (ImageButton) findViewById(R.id.rotateButton);

        points = (TextView) findViewById(R.id.textViewPoints);
        currentLevel = (TextView) findViewById(R.id.levelText);

        nextPieceView= new NextPieceView(this, gameBoard);

        tetris = new Tetris(this,nextPieceView, gameBoard);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(480, 900);
        tetris.setLayoutParams(params);
        RelativeLayout relativeTetris = (RelativeLayout) findViewById(R.id.relativelayout);
        tetris.setBackgroundColor(Color.YELLOW);
        relativeTetris.addView(tetris);
        startBtn.setOnClickListener(new View.OnClickListener() {

            int tmp=0;
            @Override
            public void onClick(View v) {

                tmp++;

                if(startBtn.getText().equals("Start")) {
                    startBtn.setText("Pause");
                    pause = false;

                }

                else if(startBtn.getText().equals("Pause")) {
                    startBtn.setText("Start");
                    pause = true;
                }

            }
        });

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tetris.resetGame();
            }
        });

    }

    @Override
    public void onRestart() {
        super.onRestart();
        pause = false;
    }

    @Override
    public void onPause() {
        super.onPause();
        pause = true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        finish();
    }

    public Button getResetButton() { return resetBtn;}
    public ImageButton getRightButton() { return toRight;}
    public ImageButton getDownButton() { return toDown;}
    public ImageButton getLeftButton() { return toLeft;}
    public ImageButton getRotateButton() { return rotate; }

    public boolean getPause() {  return pause;}
    public void setPause(boolean pause) { this.pause=pause;}

    public TextView getPointTextView() { return points; }
    public TextView getCurrentLevelTextView() { return currentLevel;}
}