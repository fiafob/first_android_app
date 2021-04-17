package com.example.projectkal;

import android.graphics.Color;
import android.graphics.Point;

import java.util.ArrayList;
import java.util.Collections;
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

    private boolean canFigureMove(Figure figure, int x, int y){
        int checks = 0;

        Point p1New = new Point(figure.x1 + x, figure.y1 + y);
        Point p2New = new Point(figure.x2 + x, figure.y2 + y);
        Point p3New = new Point(figure.x3 + x, figure.y3 + y);
        Point p4New = new Point(figure.x4 + x, figure.y4 + y);

        ArrayList<Point> coords = new ArrayList<Point>();
        coords.add(p1New); coords.add(p2New); coords.add(p3New); coords.add(p4New);

        for ( Point point: coords ) {
            if (point.x < boardHeight && point.y > boardWidth &&
                gameBoard[point.x][point.y] == 0) ++checks;
            else if (point.equals(figure.x1, figure.y1) ||
                    point.equals(figure.x2, figure.y2) ||
                    point.equals(figure.x3, figure.y3) ||
                    point.equals(figure.x4, figure.y4)) ++checks;

        }
        return (checks == 4);
    }

    private boolean canFigureRotate(Figure figure){
        int checks = 0;

        Figure figureDelta = new Figure(figure);
        figureDelta.rotate();

        ArrayList<Point> coords = new ArrayList<Point>();
        coords.add(new Point(figureDelta.x1, figureDelta.y1));
        coords.add(new Point(figureDelta.x2, figureDelta.y2));
        coords.add(new Point(figureDelta.x3, figureDelta.y3));
        coords.add(new Point(figureDelta.x4, figureDelta.y4));

        for ( Point point: coords ){
            if (point.x < boardHeight && point.y < boardWidth &&
                gameBoard[point.x][point.y] == 0) ++checks;
            else if (point.equals(figure.x1, figure.y1) ||
                    point.equals(figure.x2, figure.y2) ||
                    point.equals(figure.x3, figure.y3) ||
                    point.equals(figure.x4, figure.y4)) ++checks;
        }
        return (checks == 4);
    }

    private boolean canMoveLeft(Figure figure){
        return (canFigureMove(figure, 0, -1));
    }
    private boolean canMoveRight(Figure figure){
        return (canFigureMove(figure, 0, 1));
    }
    public boolean canMoveDown(Figure figure){
        return (canFigureMove(figure, 1, 0));
    }

    private void moveFigure(Figure figure, int x, int y){
        deleteFigure(figure);
        figure.move(x, y);
        placeFigure(figure);
    }
    public void moveRight(Figure figure){
        if (canMoveRight(figure)) moveFigure(figure, 0, 1);
    }
    public void moveLeft(Figure figure){
        if (canMoveLeft(figure)) moveFigure(figure, 0, -1);
    }
    public void moveDown(Figure figure){
        if (canMoveDown(figure)) moveFigure(figure, 1, 0);
    }

    public void rotateFigure(Figure figure){
        // no need to rotate Os
        if (canFigureRotate(figure) && figure.code != 1){
            deleteFigure(figure);
            figure.rotate();
            placeFigure(figure);
        }
//        placeFigure(figure);
    }
    public void fastDrop(Figure figure){
        deleteFigure(figure);

        while (canMoveDown(figure)) moveDown(figure);
//        placeFigure(figure);
    }

    public int clearRows(){
        int deletedRowIndex;
        int deletedRows = 0;
        ArrayList<Integer> arrayList = new ArrayList<Integer>();

        for (int i = 0; i < boardHeight; i++) {
            for (int j = boardWidth - 1; j >= 0; j--) {

                if (gameBoard[i][j]==0) { // Row not full
                    break;
                }
                if (j == 0) {
                    deletedRowIndex = i;
                    arrayList.add(deletedRowIndex);
                    deletedRows++;
                    deleteRow(deletedRowIndex);
                }
            }
        }

        if (deletedRows >= 1) {
            int highestRow = Collections.min(arrayList); // highest Row which can be cleared
            int[][] gameBoardCopy = new int[highestRow][boardWidth];

            for (int i = 0; i < gameBoardCopy.length; i++) {
                for (int j = 0; j < gameBoardCopy[1].length; j++) {
                    gameBoardCopy[i][j] = gameBoard[i][j];
                }
            }

            for (int i = 0; i < gameBoardCopy.length; i++) {
                for (int j = 0; j < gameBoardCopy[1].length; j++) {
                    gameBoard[i+deletedRows][j] = gameBoardCopy[i][j];
                }
            }
        }
        return deletedRows;
    }

    public void deleteRow(int row){
        for (int i = 0; i < boardWidth;i++){
            gameBoard[row][i] = 0;
        }
    }
    public boolean checkGameOver(Figure figure){
        if (!canMoveDown(figure) && figure.getMinCoordinate(
            figure.x1, figure.x2, figure.x3, figure.x4) <= 1) return true;
        return false;
    }
    public int getBoardHeight(){
        return this.boardHeight;
    }
    public int getBoardWidth(){
        return this.boardWidth;
    }
}
