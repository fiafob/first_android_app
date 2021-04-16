package com.example.projectkal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
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


        tetris = new Tetris(this, nextPieceView, gameBoard);
        RelativeLayout.LayoutParams params =  new RelativeLayout.LayoutParams(480, 900);
        tetris.setLayoutParams(params);

    }
}