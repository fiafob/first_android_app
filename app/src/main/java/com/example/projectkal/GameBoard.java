package com.example.projectkal;

import android.graphics.Color;
import android.graphics.Point;

import java.util.ArrayList;
import java.util.Random;

public class GameBoard {
    private final int boardHeight = 20;
    private final int boardWidth = 10;
    private final Random random = new Random();
    private final int figures = 7;
    private int gameBoard[][]=new int[boardHeight][boardWidth];
    private ArrayList<Figure> figureList = new ArrayList<Figure>();

    public GameBoard(){
        figureList.add(new Figure(random.nextInt(figures) + 1));
        figureList.add(new Figure(random.nextInt(figures) + 1));
    }

    public int codeToColor(int x, int y){
        if (gameBoard[x][y] == 0) return Color.parseColor("#BEBEBE");
        else return Color.parseColor("#000000");
    }
    public void clearGameBoard(){
        gameBoard = new int[boardHeight][boardWidth];
    }
    public ArrayList<Figure> getPieceList(){
        return figureList;
    }
    public Figure currentFigure(){
        return figureList.get(figureList.size() - 2);
    }
    public Figure nextFigure(){
        return figureList.get(figureList.size() - 1);
    }
    private void placeFigure( Figure figure){
        gameBoard[figure.x1][figure.y1] = figure.code;
        gameBoard[figure.x2][figure.y2] = figure.code;
        gameBoard[figure.x3][figure.y3] = figure.code;
        gameBoard[figure.x4][figure.y4] = figure.code;
    }
    private void deleteFigure( Figure figure ){
        gameBoard[figure.x1][figure.y1] = 0;
        gameBoard[figure.x2][figure.y2] = 0;
        gameBoard[figure.x3][figure.y3] = 0;
        gameBoard[figure.x4][figure.y4] = 0;
    }

}
