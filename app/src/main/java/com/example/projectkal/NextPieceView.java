package com.example.projectkal;

import android.content.Context;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.view.View;
import java.util.ArrayList;
import java.util.Random;

/*
paint next Piece on Screen
 */

public class NextPieceView extends View {

    private GameBoard gameBoard;
    private ArrayList<Figure> pieceList;

    private Bitmap squarePiece = BitmapFactory.decodeResource(getResources(), R.drawable.square);
    private Bitmap tPiece = BitmapFactory.decodeResource(getResources(), R.drawable.tpiece);
    private Bitmap zPiece = BitmapFactory.decodeResource(getResources(), R.drawable.zpiece);
    private Bitmap sPiece = BitmapFactory.decodeResource(getResources(), R.drawable.spiece);
    private Bitmap jPiece = BitmapFactory.decodeResource(getResources(), R.drawable.jpiece);
    private Bitmap lPiece = BitmapFactory.decodeResource(getResources(), R.drawable.lpiece);
    private Bitmap iPiece = BitmapFactory.decodeResource(getResources(), R.drawable.ipiece);

    public NextPieceView(Context context, GameBoard gameBoard) {
        super(context);
        this.gameBoard = gameBoard;
        pieceList = gameBoard.getPieceList();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint p = new Paint();

        if (pieceList.size() > 0) {
            Figure s = gameBoard.getNextPiece();

            if (s.code == 1) {
                canvas.drawBitmap(squarePiece, 0, 0, p);
            }

            if (s.code == 2) {
                canvas.drawBitmap(zPiece, 0, 0, p);
            }

            if (s.code == 3) {
                canvas.drawBitmap(iPiece, 0, 0, p);
            }

            if (s.code == 4) {
                canvas.drawBitmap(tPiece, 0, 0, p);
            }

            if (s.code == 5) {
                canvas.drawBitmap(sPiece, 0, 0, p);
            }

            if (s.code == 6) {
                canvas.drawBitmap(jPiece, 0, 0, p);
            }

            if (s.code == 7) {
                canvas.drawBitmap(lPiece, 0, 0, p);
            }

        }
    }
}