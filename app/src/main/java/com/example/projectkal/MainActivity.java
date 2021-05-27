package com.example.projectkal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    private Button gameButton, restartButton;
    private ImageButton leftArrow, rightArrow, rotateArrow, dropArrow;
    private TextView linesText, topText, scoreText, levelText;
    private BoardView boardSurface;
    private NextOneView nextOneSurface;
    private boolean pause = true;
    private Storage storage;


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

        loadStrings( this );

    }

    public void loadStrings( Context context ){
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

        boardSurface = ( BoardView ) new BoardView( context );
        RelativeLayout.LayoutParams mainParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        mainParams.addRule(RelativeLayout.BELOW, R.id.lines);
        boardSurface.setLayoutParams(mainParams);
        RelativeLayout mainLayout = ( RelativeLayout ) findViewById(R.id.mainLayout);
        mainLayout.addView(boardSurface);

        nextOneSurface = ( NextOneView ) new NextOneView( context );
        RelativeLayout.LayoutParams nextOneParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, 100);
        nextOneParams.addRule(RelativeLayout.BELOW, R.id.next);
        nextOneSurface.setLayoutParams(nextOneParams);
        RelativeLayout nextLayout = ( RelativeLayout ) findViewById(R.id.nextLayout);
        nextLayout.addView(nextOneSurface);

        Storage.init( context );

        linesText.setText(getString(R.string.lines, "0"));
        topText.setText(getString(R.string.top,
                String.valueOf(storage.load("topScore"))));
        scoreText.setText(getString(R.string.score, "0"));
        levelText.setText(getString(R.string.level,
                String.valueOf(storage.load("savedLevel"))));
    }


}