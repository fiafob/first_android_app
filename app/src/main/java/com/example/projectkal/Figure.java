package com.example.projectkal;

public class Figure {
    public int x1, y1;
    public int x2, y2;
    public int x3, y3;
    public int x4, y4;
    public int code;
    private Figure figure;

    // need for checking is it possible to rotate
    public Figure( Figure figure ){
        this.figure = figure;
        this.x1 = figure.x1; this.y1 = figure.y1;
        this.x2 = figure.x2; this.y2 = figure.y2;
        this.x3 = figure.x3; this.y3 = figure.y3;
        this.x4 = figure.x4; this.y4 = figure.y4;
    }


    public Figure(int figure) {
        switch (figure) {
            // (x1, y1) is pivot point
            case 1: // O
                x1 = 4; y1 = 0;
                x2 = 5; y2 = 0;
                x3 = 4; y3 = 1;
                x4 = 5; y4 = 1;
                code = 1;

                break;

            case 2:    // z
                x1 = 5; y1 = 1;
                x2 = 4; y2 = 0;
                x3 = 5; y3 = 0;
                x4 = 6; y4 = 1;
                code = 2;

                break;

            case 3: // I
                x1 = 5; y1 = 0;
                x2 = 3; y2 = 0;
                x3 = 4; y3 = 0;
                x4 = 6; y4 = 0;
                code = 3;

                break;

            case 4: // T
                x1 = 5; y1 = 1;
                x2 = 4; y2 = 1;
                x3 = 5; y2 = 0;
                x4 = 6; y4 = 1;
                code = 4;

                break;

            case 5: // S
                x1 = 4; y1 = 1;
                x2 = 4; y2 = 0;
                x3 = 5; y3 = 0;
                x4 = 3; y4 = 1;
                code = 5;

                break;

            case 6:  // J
                x1 = 5; y1 = 1;
                x2 = 4; y2 = 0;
                x3 = 4; y3 = 1;
                x4 = 6; y4 = 1;
                code = 6;


                break;

            case 7:  //  L
                x1 = 5; y1 = 1;
                x2 = 6; y2 = 0;
                x3 = 6; y3 = 1;
                x4 = 4; y4 = 1;
                code = 7;

                break;
        }
    }

    // formula: xNew = xPlot + yPlot - yOld
    //          yNew = xOld + yOld - xOld
// https://ru.stackoverflow.com/questions/998300/Как-повернуть-фигуру-тетриса
    public void rotate(){

        int deltaY = yRotation(x2);
        x2 = xRotation(y2);
        y2 = deltaY;

        deltaY = yRotation(x3);
        x3 = xRotation(y3);
        y3 = deltaY;

        deltaY = yRotation(x4);
        x4 = xRotation(y4);
        y4 = deltaY;
    }
    public int xRotation( int y ){
        return x1 + y1 - y;
    }
    public int yRotation( int x ){
        return x + y1 - x1;
    }


    public void move( int x, int y ){
        x1 += x; x2 += x; x3 += x; x4 += x;
        y1 += y; y2 += y; y3 += y; y4 += y;
    }
    public int getMinCoordinate(int x1, int x2, int x3, int x4){
        return Math.min(Math.min(x1, x2), Math.min(x3, x4));
    }
}

