package com.example.projectkal;

public class Figure {
    private int x1, y1;
    private int x2, y2;
    private int x3, y3;
    private int x4, y4;


    public Figure(int figure) {
        switch (figure) {
            // (x1, y1) is pivot point
            case 1: // O
                x1 = 4; y1 = 0;
                x2 = 5; y2 = 0;
                x3 = 4; y3 = 1;
                x4 = 5; y4 = 1;

                break;

            case 2:    // z
                x1 = 5; y1 = 1;
                x2 = 4; y2 = 0;
                x3 = 5; y3 = 0;
                x4 = 6; y4 = 1;


                break;

            case 3: // I
                x1 = 5; y1 = 0;
                x2 = 3; y2 = 0;
                x3 = 4; y3 = 0;
                x4 = 6; y4 = 0;

                break;

            case 4: // T
                x1 = 5; y1 = 1;
                x2 = 4; y2 = 1;
                x3 = 5; y2 = 0;
                x4 = 6; y4 = 1;

                break;

            case 5: // S
                x1 = 4; y1 = 1;
                x2 = 4; y2 = 0;
                x3 = 5; y3 = 0;
                x4 = 3; y4 = 1;

                break;

            case 6:  // J
                x1 = 5; y1 = 1;
                x2 = 4; y2 = 0;
                x3 = 4; y3 = 1;
                x4 = 6; y4 = 1;



                break;

            case 7:  //  L
                x1 = 5; y1 = 1;
                x2 = 6; y2 = 0;
                x3 = 6; y3 = 1;
                x4 = 4; y4 = 1;

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
}

