package com.example.projectkal;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class BoardView extends View implements View.OnClickListener {
    private ImageButton leftArrow, rightArrow, rotateArrow, dropArrow;
    private TextView linesText, topText, scoreText, levelText;
    private MainActivity mainActivity;
    private Field field;
    private Timer timer = new Timer();
    private Random random = new Random();
    private ArrayList<Pieces> pieceList;
    private Score score;
    private Storage storage;
    private final int point = 10;
    private int timerPeriod = 250;
    private int level = 0;
    private boolean pause;
    int lvl = 0;

    public BoardView(Context context, Field field, Storage storage) {
        super(context);
        this.storage = storage;
        Storage.init( context );
        this.mainActivity = (MainActivity) context;
        this.field = field;
        pause = mainActivity.isPause();
        pieceList = field.getPiecesArrayList();
        score = new Score( context, storage );

        levelText = mainActivity.getLevelText();
        topText = mainActivity.getTopText();
        scoreText = mainActivity.getScoreText();
        linesText = mainActivity.getLinesText();

        linesText.setText(mainActivity.getString(R.string.lines, "0"));
        topText.setText(mainActivity.getString(R.string.top,
                String.valueOf(Storage.load("topScore"))));
        scoreText.setText(mainActivity.getString(R.string.score, "0"));
        levelText.setText(mainActivity.getString(R.string.level,
                String.valueOf(Storage.load("savedLevel"))));

        rotateArrow = mainActivity.getRotateArrow();
        rightArrow = mainActivity.getRightArrow();
        leftArrow = mainActivity.getLeftArrow();
        dropArrow = mainActivity.getDropArrow();

        rotateArrow.setOnClickListener(this);
        rightArrow.setOnClickListener(this);
        leftArrow.setOnClickListener(this);
        dropArrow.setOnClickListener(this);
        gameLoop();
    }

    public void gameLoop() {

        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                mainActivity.runOnUiThread(new TimerTask() {

                    @Override
                    public void run() {

                        if( !gameOver() && !mainActivity.isPause() ) {

                            field.moveDown(field.getCurrentPiece());

                            if ( !field.pieceCanMoveDown(field.getCurrentPiece()) ) {
                                int deletedRows = field.clearRows();
                                field.clearRows();
                                pieceList.remove(field.getCurrentPiece());
                                pieceList.add(new Pieces(random.nextInt(7) + 1));


                                if (deletedRows> 0) {
                                    lvl += deletedRows;
                                    score.setCurrentPoints(score.getCurrentPoints() + deletedRows * point);
                                    int p = score.getCurrentPoints();
                                    score.setLevel();

                                    linesText.setText(mainActivity.getString(R.string.lines,
                                            String.valueOf(lvl)));

                                    scoreText.setText("SCORE:" +"\n"+ p);
                                    levelText.setText("LEVEL" +" "+ score.getLevel());

                                    if (score.getLevel() > score.loadHighscore()) {
                                        score.writeHighscore();
                                        topText.setText("TOP:" +"\n"+ score.getLevel());
                                    }
                                }

                                if(score.getLevel()>level) {
                                    level++;
                                    timerPeriod = timerPeriod - (score.getLevel()*20);
                                    timer.cancel();
                                    timer = new Timer();
                                    gameLoop();
                                }
                            }
                            invalidate();
                        }
                    }
                });
            }
        }, 0, timerPeriod);
    }

    public boolean gameOver() {

        if( field.checkGameOver(field.getCurrentPiece()) ) {
            timer.cancel();
            lvl = 0;
            pieceList.clear();
            field.clearField();
            mainActivity.setPause(true);
            showGameOverScreen();
            return true;
        }
        return false;
    }

    public void resetGame() {
        timer.cancel();
        pieceList.clear();
        field.clearField();
        lvl = 0;
        mainActivity.setPause(true);
        invalidate();
        Intent intent = new Intent(this.getContext(), MainActivity.class);
        getContext().startActivity(intent);
    }

    public void showGameOverScreen() {
        Intent intent = new Intent(this.getContext(), GameOverScreen.class);
        getContext().startActivity(intent);
    }


    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);
        Paint p = new Paint();

        for (int x = 0; x < field.getHEIGHT(); x++) {
            for (int y = 0; y < field.getWIDHT(); y++) {

                int color  = field.codeToColor(x,y);
                p.setColor(color);
                canvas.drawRect(y*45, x*45, y*45+45, x*45+45,p);
            }
        }
    }


    @Override
    public void onClick(View v) {
        if(!mainActivity.isPause()) {

            switch(v.getId()) {
                case R.id.rightArrow:
                    field.moveRight(field.getCurrentPiece());
                    invalidate();
                    break;
                case R.id.dropArrow:
                    field.fastDrop(field.getCurrentPiece());
                    invalidate();
                    break;
                case R.id.leftArrow:
                    field.moveLeft(field.getCurrentPiece());
                    invalidate();
                    break;
                case R.id.rotateArrow:
                    field.rotatePiece(field.getCurrentPiece());
                    invalidate();
                    break;
            }
        }
    }

    public Timer getTimer() {
        return this.timer;
    }

}

