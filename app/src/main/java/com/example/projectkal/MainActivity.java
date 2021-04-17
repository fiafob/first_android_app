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
    private Button buttonStart;
    private Button buttonReset;
    private ImageButton rotateButton;
    private ImageButton rightButton;
    private ImageButton downButton;
    private ImageButton leftButton;

    private TextView pointTextView;
    private TextView highscoreLevelTextView;
    private TextView currentLevelTextView;
    private Tetris tetris;
    private NextPieceView nextPieceView;
    private boolean pause = true;
    private GameBoard gameBoard = new GameBoard();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // Make this activity, full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // Hide the Title bar of this activity screen
//        getWindow().requestFeature(Window.FEATURE_NO_TITLE);


        setContentView(R.layout.activity_main);


        setContentView(R.layout.activity_main);



        buttonStart = (Button) findViewById(R.id.buttonstart);
        buttonReset = (Button) findViewById(R.id.buttonreset);
        rotateButton = (ImageButton) findViewById(R.id.rotateButton);
        rightButton = (ImageButton) findViewById(R.id.rightButton);
        downButton = (ImageButton) findViewById(R.id.downButton);
        leftButton = (ImageButton) findViewById(R.id.leftButton);
        pointTextView = (TextView) findViewById(R.id.textViewPoints);
        highscoreLevelTextView= (TextView) findViewById(R.id.textViewHighscore);
        currentLevelTextView = (TextView) findViewById(R.id.levelText);

        nextPieceView = new NextPieceView(this, gameBoard);
        RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(300,300);
        nextPieceView.setLayoutParams(params1);
        RelativeLayout relativeSteinAnzeige = (RelativeLayout) findViewById(R.id.relativelayout1);
        nextPieceView.setBackgroundColor(Color.YELLOW);
        relativeSteinAnzeige.addView(nextPieceView);

        tetris = new Tetris(this,nextPieceView, gameBoard);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(480, 900);
        tetris.setLayoutParams(params);
        RelativeLayout relativeTetris = (RelativeLayout) findViewById(R.id.relativelayout);
        tetris.setBackgroundColor(Color.YELLOW);
        relativeTetris.addView(tetris);
        buttonStart.setOnClickListener(new View.OnClickListener() {

            int tmp=0;
            @Override
            public void onClick(View v) {

                tmp++;

                if(buttonStart.getText().equals("Start")) {
                    buttonStart.setText("Pause");
                    pause = false;


                }

                else if(buttonStart.getText().equals("Pause")) {
                    buttonStart.setText("Start");
                    pause = true;

                }

            }
        });

        buttonReset.setOnClickListener(new View.OnClickListener() {
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

    public Button getResetButton() { return buttonReset;}
    public ImageButton getRightButton() { return rightButton;}
    public ImageButton getDownButton() { return downButton;}
    public ImageButton getLeftButton() { return leftButton;}
    public ImageButton getRotateButton() { return rotateButton; }

    public boolean getPause() {  return pause;}
    public void setPause(boolean pause) { this.pause=pause;}
    public TextView getHighscoreLevelTextView() { return highscoreLevelTextView; }
    public TextView getPointTextView() { return pointTextView; }
    public TextView getCurrentLevelTextView() { return currentLevelTextView;}

}