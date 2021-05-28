package com.example.projectkal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private Button gameButton, restartButton;
    private ImageButton leftArrow, rightArrow, rotateArrow, dropArrow;
    private TextView linesText, topText, scoreText, levelText;
    private BoardView boardSurface;

    private Storage storage;
    private Field field = new Field();

    private boolean pause = true;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //setContentView(R.layout.activity_main);
        setContentView(R.layout.theme3);

///////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////

        loadXml( this );

    }

    public void loadXml( Context context ){
        gameButton = (Button) findViewById( R.id.gameButton );
        restartButton = (Button) findViewById( R.id.restartButton );

        leftArrow = (ImageButton) findViewById( R.id.leftArrow );
        rightArrow = (ImageButton) findViewById( R.id.rightArrow );
        rotateArrow = (ImageButton) findViewById( R.id.rotateArrow );
        dropArrow = (ImageButton) findViewById( R.id.dropArrow );

        linesText = (TextView) findViewById( R.id.lines );
        topText = (TextView) findViewById ( R.id.top );
        scoreText = (TextView) findViewById( R.id.score );
        levelText = (TextView) findViewById( R.id.level );

        boardSurface = ( BoardView ) new BoardView( context,  field, storage );
        RelativeLayout.LayoutParams mainParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        mainParams.addRule(RelativeLayout.BELOW, R.id.lines);
        boardSurface.setLayoutParams(mainParams);
        boardSurface.setBackgroundColor(Color.WHITE);
        RelativeLayout mainLayout = ( RelativeLayout ) findViewById(R.id.mainLayout);
        mainLayout.addView(boardSurface);



        Storage.init( context );



        gameButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if ( gameButton.getText().equals("START")){
                    gameButton.setText("PAUSE");
                    pause = false;
                }
                else{
                    gameButton.setText("START");
                    pause = true;
                }
            }
        });
        restartButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {boardSurface.resetGame();}
        });
    }

    @Override
    public void onPause(){
        super.onPause();
        pause = true;

    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        finish();
    }
    @Override
    public void onRestart() {
        super.onRestart();
        pause = false;
    }

    public Button getRestartButton() { return restartButton; }
    public ImageButton getLeftArrow() { return leftArrow; }
    public ImageButton getRightArrow() { return rightArrow; }
    public ImageButton getRotateArrow() { return rotateArrow; }
    public ImageButton getDropArrow() { return dropArrow; }
    public TextView getTopText() { return topText; }
    public TextView getScoreText() { return scoreText; }
    public TextView getLevelText() { return levelText; }
    public boolean isPause() { return pause; }
    public void setPause( boolean pause ) { this.pause = pause; }
    public TextView getLinesText() { return linesText; }
}