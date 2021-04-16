package com.example.projectkal;

import android.content.Context;
import android.view.View;
import android.widget.ImageButton;

import java.util.Timer;

public class Tetris extends View implements View.OnClickListener{
    private GameBoard gameBoard;
    private MainActivity mainActivity;
    private ImageButton toRight, toLeft, toDown, rotate;
    private Timer timer;


    public Tetris(Context context, NextPieceView nextPieceView, GameBoard gameBoard){
        super(context);
    }

    @Override
    public void onClick(View v) {

    }
}
