package com.example.projectkal;

import android.graphics.Color;
import android.graphics.Point;
import android.view.Window;

import java.util.ArrayList;

import java.util.Random;

public class Field {
    private final int piecesNum = 7;
    private final int WIDHT = 10;
    private final int HEIGHT = 20;
    private final Random random = new Random();
    private int field[][] = new int[HEIGHT][WIDHT];
    private ArrayList<Pieces> piecesArrayList = new ArrayList<Pieces>();

    Field(){
        piecesArrayList.add(new Pieces( random.nextInt(piecesNum) + 1 ));
        piecesArrayList.add(new Pieces( random.nextInt(piecesNum) + 1 ));
    }

    public int codeToColor(int x, int y) {

        if(field[x][y]==0) return Color.parseColor("#000000");  // White
        if(field[x][y]==1) return Color.parseColor("#BEBEBE"); ;  // jpiece gray
        return -1;
    }

    private void plantPiece( Pieces piece ){
        field[piece.x1][piece.y1] = 1;
        field[piece.x2][piece.y2] = 1;
        field[piece.x3][piece.y3] = 1;
        field[piece.x4][piece.y4] = 1;
    }
    private void removePiece( Pieces piece ){
        field[piece.x1][piece.y1] = 0;
        field[piece.x2][piece.y2] = 0;
        field[piece.x3][piece.y3] = 0;
        field[piece.x4][piece.y4] = 0;
    }

    public void clearField(){
        for (int i = 0; i < HEIGHT; i ++){
            for (int j = 0; j < WIDHT; j ++)
                field[i][j] = 0;
        }
    }

    public boolean pieceCanMove( Pieces piece, int x, int y ){
        int count = 0;

        Point p1 = new Point(piece.x1 + x, piece.y1 + y);
        Point p2 = new Point(piece.x2 + x, piece.y2 + y);
        Point p3 = new Point(piece.x3 + x, piece.y3 + y);
        Point p4 = new Point(piece.x4 + x, piece.y4 + y);
        ArrayList<Point> pointArray = new ArrayList<Point>();
        pointArray.add(p1);
        pointArray.add(p2);
        pointArray.add(p3);
        pointArray.add(p4);

        for ( Point p: pointArray ){
            if ((p.x < HEIGHT && p.x >= 0) && (p.y >= 0 && p.y < WIDHT)
                    && (field[p.x][p.y] == 0)) count++;
            else if (p.equals(piece.x1, piece.y1) ||
                     p.equals(piece.x2, piece.y2) ||
                     p.equals(piece.x3, piece.y3) ||
                     p.equals(piece.x4, piece.y4)) count++;
        }
        return (count == 4);
    }
    public boolean pieceCanRotate( Pieces piece ){
        int count = 0;
        Pieces pcCopy = new Pieces(piece);
        pcCopy.turnPiece();
        ArrayList<Point> pointArray = new ArrayList<Point>();
        pointArray.add(new Point(pcCopy.x1, pcCopy.y1));
        pointArray.add(new Point(pcCopy.x2, pcCopy.y2));
        pointArray.add(new Point(pcCopy.x3, pcCopy.y3));
        pointArray.add(new Point(pcCopy.x4, pcCopy.y4));

        for ( Point p: pointArray ){
            if ((p.x < HEIGHT && p.x >= 0) && (p.y >= 0 && p.y < WIDHT)
                    && field[p.x][p.y] == 0) count++;
            else if (p.equals(piece.x1, piece.y1) ||
                     p.equals(piece.x2, piece.y2) ||
                     p.equals(piece.x3, piece.y3) ||
                     p.equals(piece.x4, piece.y4)) count++;
        }
        return (count == 4);
    }
    public void rotatePiece(Pieces piece){
        if (pieceCanRotate(piece) && piece.colorCode != 1){
            removePiece(piece);
            piece.turnPiece();
            plantPiece(piece);
        }
        plantPiece(piece);
    }

    public boolean pieceCanMoveLeft( Pieces piece ){ return pieceCanMove(piece, 0, -1); }
    public boolean pieceCanMoveDown( Pieces piece ){ return pieceCanMove(piece, 1, 0); }
    public boolean pieceCanMoveRight( Pieces piece ){ return pieceCanMove(piece, 0, 1); }
    public void fastDrop(Pieces currentPiece) {
        removePiece(currentPiece);

        while(pieceCanMoveDown(currentPiece)==true) {
            moveDown(currentPiece);
        }
        plantPiece(currentPiece);
    }

    private void movePiece( Pieces piece, int x, int y ){
        removePiece(piece);
        piece.move(x, y);
        plantPiece(piece);
    }

    public void moveRight( Pieces piece ){
        if (pieceCanMoveRight(piece)) movePiece(piece, 0, 1);
    }
    public void moveLeft( Pieces piece ){
        if (pieceCanMoveLeft(piece)) movePiece(piece, 0, -1);
    }
    public void moveDown( Pieces piece ){
        if (pieceCanMoveDown(piece)) movePiece(piece, 1, 0);
    }
    public void deleteRow(int index){
        for (int i = 0; i < WIDHT; i ++){
            field[index][i] = 0;
        }
    }
    public int clearRows(){
        int deletedRows = 0;
        int deletedRowIndex = 0;
        int min = 999;

        for ( int i = 0; i < HEIGHT; i++){
            for ( int j = WIDHT - 1; j >= 0; j-- ){

                if ( field[i][j] == 0 ) break;
                if ( j == 0 ){
                    deletedRowIndex = i;
                    if ( deletedRowIndex < min ) min = deletedRowIndex;
                    deletedRows++;
                    deleteRow(deletedRowIndex);
                }
            }
        }

        if ( deletedRows >= 1 ){
            int[][] fieldCopy = new int[min][WIDHT];

            for ( int i = 0; i < min; i++){
                for ( int j = 0; j < WIDHT; j++){
                    fieldCopy[i][j] = field[i][j];
                }
            }

            for ( int i = 0; i < min; i++ ){
                for ( int j = 0; j < WIDHT; j++ ){
                    field[i + deletedRows][j] = fieldCopy[i][j];
                }
            }
        }
        return deletedRows;
    }

    public boolean checkGameOver( Pieces piece ){
        return ( !pieceCanMoveDown(piece) &&
                Math.min(Math.min(piece.x3, piece.x4), Math.min(piece.x1, piece.x2)) <= 1 );
    }


    public ArrayList<Pieces> getPiecesArrayList(){ return piecesArrayList; }
    public Pieces getCurrentPiece() { return piecesArrayList.get(piecesArrayList.size() - 2); }
    public Pieces getNextPiece() { return piecesArrayList.get(piecesArrayList.size() - 1); }
    public int getHEIGHT() {return HEIGHT;}
    public int getWIDHT() {return WIDHT;}
}
